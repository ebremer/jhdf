/*
 * This file is part of jHDF. A pure Java library for accessing HDF5 files.
 *
 * https://jhdf.io
 *
 * Copyright (c) 2025 James Mudd
 *
 * MIT License see 'LICENSE' file
 */
package io.jhdf;

import io.jhdf.storage.HdfBackingStorage;
import io.jhdf.storage.HdfFileChannel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SymbolTableEntryTest {
	private HdfBackingStorage hdfBackingStorage;

	@BeforeEach
	void setUp() throws IOException {
		Path testPath = TestUtils.getTestPath("test_file.hdf5");
		FileChannel fc = FileChannel.open(testPath, StandardOpenOption.READ);
		Superblock sb = Superblock.readSuperblock(fc, 0);
		hdfBackingStorage = new HdfFileChannel(fc, sb);
	}

	@AfterEach
	void after() {
		hdfBackingStorage.close();
	}

	@Test
	void testSymbolTableEntry() {
		SymbolTableEntry ste = new SymbolTableEntry(hdfBackingStorage, 56);
		assertThat(ste.getLinkNameOffset(), is(equalTo(0)));
		assertThat(ste.getObjectHeaderAddress(), is(equalTo(96L)));
		assertThat(ste.getCacheType(), is(equalTo(1)));
		assertThat(ste.getBTreeAddress(), is(equalTo(136L)));
		assertThat(ste.getNameHeapAddress(), is(equalTo(680L)));
		assertThat(ste.toString(), is(equalTo(
			"SymbolTableEntry [address=0x38, linkNameOffset=0, objectHeaderAddress=0x60, cacheType=1, bTreeAddress=0x88, nameHeapAddress=0x2a8, linkValueOffset=-1]")));
	}
}
