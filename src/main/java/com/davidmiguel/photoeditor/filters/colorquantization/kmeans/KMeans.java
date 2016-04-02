package com.davidmiguel.photoeditor.filters.colorquantization.kmeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * K-means algorithm. K-means is an iterative algorithm that keeps assigning
 * data points to clusters identified by special points called centroids, until
 * the cluster assignment stabilizes.
 */
public class KMeans {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	/** Number of clusters to create */
	int k;
	/** Data set with the points */
	DataSet ds;

	/**
	 * Create new instance of k-means algorithm.
	 * 
	 * @param ds
	 *            data set
	 */
	public KMeans(DataSet ds) {
		this.ds = ds;
	}

	/**
	 * Execute k-means algorithm with the data set settled.
	 * 
	 * @param k
	 *            number of clusters to build
	 * @return list of final clusters
	 */
	public List<Cluster> run(int k) {
		this.k = k;
		logger.info("> Running " + k + "-means...");

		List<Cluster> clusters = chooseCentroids();

		int nIter = 0; // Number of iterations
		while (!isFinished(clusters)) {
			logger.trace("Not finish");
			nIter++;
			cleanClusters(clusters);
			assignPoints(clusters);
			recalculateCentroids(clusters);
		}

		// Debug figures
		logger.debug("      " + nIter + " iterations executed");
		for (int i = 0; i < clusters.size(); i++) {
			logger.debug("      ·Cluster " + (i + 1) + ": "
					+ clusters.get(i).getPoints().size() + " points");
		}

		return clusters;
	}

	/**
	 * Choose centroids for the k clustes taken from the dataset.
	 * 
	 * @return k clusters each one with the centroid setted
	 */
	private List<Cluster> chooseCentroids() {
		Random r = new Random();
		List<Cluster> centroids = new ArrayList<Cluster>(k);
		List<Point> points = ds.getPoints();

		// Get k random centroids taken from the dataset
		for (int i = 0; i < k; i++) {
			Point centroid = points.get(r.nextInt(points.size()));
			Cluster c = new Cluster(centroid);
			centroids.add(c);
		}

		logger.debug("Initial centroids:");
		for (Cluster c : centroids) {
			logger.debug(c.toString());
		}

		return centroids;
	}

	/**
	 * Check if all the clusters are complete.
	 * 
	 * @param clusters
	 * @return true if all are complete, otherwise false
	 */
	private boolean isFinished(List<Cluster> clusters) {
		for (Cluster cluster : clusters) {
			if (!cluster.isFinished()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Remove all the points each cluster had.
	 * 
	 * @param clusters
	 */
	private void cleanClusters(List<Cluster> clusters) {
		for (Cluster c : clusters) {
			c.cleanPoints();
		}
		logger.debug("Clusters cleaned");
	}

	/**
	 * Assign each point of the data set to its closest cluster.
	 * 
	 * @param clusters
	 */
	private void assignPoints(List<Cluster> clusters) {
		// Get the closest cluster for each point
		for (Point p : ds.getPoints()) {
			Cluster closest = clusters.get(0); // Choose an initial cluster
			Double minimumDistance = Double.MAX_VALUE;
			for (Cluster c : clusters) {
				// Squared Euclidian distance between the point and the centroid
				Double distance = p.squaredEuclidianDistance(c.getCentroid());
				if (minimumDistance > distance) {
					minimumDistance = distance;
					closest = c;
				}
			}
			logger.debug(
					p.toString() + " --> " + closest.getCentroid().toString());
			// Add point to the closest cluster
			closest.addPoint(p);
		}

		logger.debug("Points assigned");
		for (int i = 0; i < clusters.size(); i++) {
			logger.debug("Cluster " + (i + 1) + ": "
					+ clusters.get(i).getPoints().size() + " points.");
		}
	}

	/**
	 * Recalculate the centroids of each cluster taking as a new centroid a
	 * point with the mean value of each feature.
	 * 
	 * @param clusters
	 */
	private void recalculateCentroids(List<Cluster> clusters) {
		logger.debug("New centroids:");

		for (Cluster c : clusters) {
			// If the cluster has no points, the centroid is the same
			if (c.isEmpty()) {
				c.setFinished(true);
				logger.debug("The same (empty)");
				continue;
			}

			// Calculate mean value of each feature
			double[] meanFeatures = new double[ds.nFeatures()];
			Arrays.fill(meanFeatures, 0F);
			for (Point p : c.getPoints()) {
				for (int f = 0; f < ds.nFeatures(); f++) {
					meanFeatures[f] += p.getValue(f);
				}
			}
			for (int f = 0; f < ds.nFeatures(); f++) {
				meanFeatures[f] /= (double) c.nPoints();
			}

			Point newCentroid = new Point(meanFeatures);

			// If the new centroid is the same as the previois centroid, we are
			// finished with this cluster. If not, the new centroid is setted
			if (newCentroid.equals(c.getCentroid())) {
				c.setFinished(true);
				logger.debug("The same");
			} else {
				c.setCentroid(newCentroid);
				logger.debug(newCentroid.toString());
			}
		}
	}
}