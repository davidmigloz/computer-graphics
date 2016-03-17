package com.davidmiguel.photoeditor.filters.dithering;

import com.davidmiguel.photoeditor.filters.Filter;

public abstract class DitheringFilter implements Filter {

	protected int k;

	public DitheringFilter(int k) {
		this.k = k;
	}
}