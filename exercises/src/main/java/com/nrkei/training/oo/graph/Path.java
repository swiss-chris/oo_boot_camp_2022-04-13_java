package com.nrkei.training.oo.graph;

public interface Path {
    Path NULL_OBJECT = new Path(){};

    default int hopCount(){
        return Integer.MAX_VALUE;
    }

    default double cost(){
        return Double.POSITIVE_INFINITY;
    }

    default boolean contains(Node node){
        return false;
    }

    default Path prepend(Link link){
        return this;
    }
}
