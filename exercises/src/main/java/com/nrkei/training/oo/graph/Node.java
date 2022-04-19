/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import com.nrkei.training.oo.graph.Path.ActualPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

// Understands its neighbors
public class Node {
    private final List<Link> links = new ArrayList<>();

    public boolean canReach(Node destination) {
        return path(destination, noVisitedNodes(), Path::cost) instanceof ActualPath;
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

    private Path path(Node destination, ToDoubleFunction<Path> strategy) {
        Path result = path(destination, noVisitedNodes(), strategy);
        if (result == Path.NONE) throw new IllegalArgumentException("Destination is not reachable");
        return result;
    }

    Path path(Node destination, List<Node> visitedNodes, ToDoubleFunction<Path> strategy) {
        if (this == destination) return new ActualPath();
        if (visitedNodes.contains(this)) return Path.NONE;
        return links.stream()
                .map(l -> l.path(destination, copyWithThis(visitedNodes), strategy))
                .min(Comparator.comparingDouble(strategy))
                .orElse(Path.NONE);
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
