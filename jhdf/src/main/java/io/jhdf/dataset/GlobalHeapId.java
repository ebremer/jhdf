/*
 * This file is part of jHDF. A pure Java library for accessing HDF5 files.
 *
 * https://jhdf.io
 *
 * Copyright (c) 2025 James Mudd
 *
 * MIT License see 'LICENSE' file
 */
package io.jhdf.dataset;

public class GlobalHeapId {

	private final long heapAddress;
	private final int index;

	public GlobalHeapId(long heapAddress, int index) {
		this.heapAddress = heapAddress;
		this.index = index;
	}

	public long getHeapAddress() {
		return heapAddress;
	}

	public int getIndex() {
		return index;
	}

}
