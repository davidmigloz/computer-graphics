package com.davidmiguel.photoeditor.filters.colorquantization.kmeans;

import java.util.List;

import com.davidmiguel.photoeditor.filters.Filter;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class KmeansColorQuantizationFilter implements Filter {

	private int k;

	public KmeansColorQuantizationFilter(int k) {
		this.k = k;
	}

	@Override
	public Image apply(Image input) {
		// Create dataset
		DataSet ds = new DataSet(input);
		
		// Run k-means
		KMeans kMeans = new KMeans(ds);
		List<Cluster> clusters = kMeans.run(k);

		// Reader for the original image
		PixelReader pixelReader = input.getPixelReader();
		int width = (int) input.getWidth();
		int height = (int) input.getHeight();

		// Create new image and get its writer
		WritableImage result = new WritableImage(width, height);
		PixelWriter pixelWriter = result.getPixelWriter();

		// Apply transformation
		Point centroid = null;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Actual color
				Color color = pixelReader.getColor(x, y);
				// Create point from pixel
				double[] rgb = { color.getRed(), color.getGreen(),
						color.getBlue() };
				Point p = new Point(rgb);
				// Check to which cluster belongs
				for (Cluster c : clusters) {
					if (c.contains(p)) {
						centroid = c.getCentroid();
						break;
					}
				}
				// New value
				Color newColor = new Color(centroid.getValue(0),
						centroid.getValue(1), centroid.getValue(2),
						color.getOpacity());
				// Write new pixel
				pixelWriter.setColor(x, y, newColor);
			}
		}
		return result;
	}
}