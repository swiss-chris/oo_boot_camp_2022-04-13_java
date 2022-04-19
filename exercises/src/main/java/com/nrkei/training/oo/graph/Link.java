/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

// Understands a connection from one Node to another
class Link {
    private final double cost;
    private final Node target;

    Link(double cost, Node target) {
        this.cost = cost;
        this.target = target;
    }

    static double cost(List<Link> links) {
        return links.stream().mapToDouble(link -> link.cost).sum();
    }

    Path path(Node destination, List<Node> visitedNodes, ToDoubleFunction<Path> strategy) {
        return target.path(destination, visitedNodes, strategy).prepend(this);
    }

    Stream<Path> paths(Node destination, List<Node> visitedNodes) {
        return target.paths(destination, visitedNodes).map(p -> p.prepend(this));
    }
}
