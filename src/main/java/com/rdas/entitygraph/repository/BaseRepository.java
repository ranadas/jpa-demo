package com.rdas.entitygraph.repository;

public interface BaseRepository<D, T> {

    D findWithGraph(T id, String graphName);
}
