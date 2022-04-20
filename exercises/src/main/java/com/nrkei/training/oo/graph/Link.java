/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.List;
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

    Stream<Path> paths(List<Node> visitedNodes) {
        return target.paths(visitedNodes).map(p -> p.prepend(this));
    }
}
