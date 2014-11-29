package com.liusp.roommv.index;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;

public interface Indexer {
	public void setAnalyzer(Analyzer analyzer);

	public void createSearchIndexes() throws IOException;

	public void deleteAll() throws IOException;

	public void optimize() throws IOException;
}
