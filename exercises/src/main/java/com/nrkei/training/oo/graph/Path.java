/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Understands a particular route from one Node to another
public class Path {
    private final List<Link> links = new ArrayList<>();
    private final Node destination;

    Path(Node destination) {
        this.destination = destination;
    }

    static List<Path> filter(List<Path> paths, Node destination) {
        return paths.stream()
                .filter(p -> p.destination == destination)
                .collect(Collectors.toList());
    }

    Path prepend(Link link) {
        links.add(0, link);
        return this;
    }

    public double cost() {
        return Link.cost(links);
    }

    public int hopCount() {
        return links.size();
    }

}
