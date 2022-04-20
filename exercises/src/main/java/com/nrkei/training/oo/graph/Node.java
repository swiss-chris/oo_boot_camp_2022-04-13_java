/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.min;

// Understands its neighbors
public class Node {
    private final List<Link> links = new ArrayList<>();

    public boolean canReach(Node destination) {
        return !paths(destination).isEmpty();
    }

    public int hopCount(Node destination) {
        return path(destination, Path::hopCount).hopCount();
    }

    public double cost(Node destination) {
        return path(destination).cost();
    }

    public Path path(Node destination) {
        return path(destination, Path::cost);
    }

    public List<Path> paths(Node destination) {
        return paths(destination, noVisitedNodes()).collect(Collectors.toList());
    }

    public List<Path> paths() {
        return paths(noVisitedNodes()).collect(Collectors.toList());
    }

    Stream<Path> paths(List<Node> visitedNodes) {
        if (visitedNodes.contains(this)) return Stream.empty();
        return Stream.concat(
                Stream.of(new Path()),
                links.stream().flatMap(link -> link.paths(copyWithThis(visitedNodes)))
        );
    }

    Stream<Path> paths(Node destination, List<Node> visitedNodes) {
        if (this == destination) return Stream.of(new Path());
        if (visitedNodes.contains(this)) return Stream.empty();
        return links.stream().flatMap(link -> link.paths(destination, copyWithThis(visitedNodes)));
    }

    private Path path(Node destination, ToDoubleFunction<Path> strategy) {
        List<Path> allPaths = paths(destination);
        if (allPaths.isEmpty()) throw new IllegalArgumentException("Destination unreachable");
        return min(allPaths, Comparator.comparingDouble(strategy));
    }

    private List<Node> copyWithThis(List<Node> originals) {
        List<Node> results = new ArrayList<>(originals);
        results.add(this);
        return results;
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }

    public LinkBuilder cost(double amount) {
        return new LinkBuilder(amount, links);
    }

    public static class LinkBuilder {
        private final double cost;
        private final List<Link> links;

        LinkBuilder(double cost, List<Link> links) {
            this.cost = cost;
            this.links = links;
        }

        public Node to(Node neighbor) {
            links.add(new Link(cost, neighbor));
            return neighbor;
        }
    }
}
