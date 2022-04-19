/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import com.nrkei.training.oo.quantity.IntervalQuantity;

import java.util.ArrayList;
import java.util.List;

// Understands its neighbors
public class Node {
    private static final double UNREACHABLE = Double.POSITIVE_INFINITY;

    private final List<Node> neighbors = new ArrayList<>();

    public Node to(Node neighbor) {
        neighbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return hopCount(destination, noVisitedNodes()) != UNREACHABLE;
    }

    public int hopCount(Node destination) {
        double result = hopCount(destination, noVisitedNodes());
        if (result == UNREACHABLE) throw new IllegalArgumentException("Destination is not reachable");
        return (int) result;
    }

    private double hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0.0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        return neighbors.stream()
                .mapToDouble(n -> n.hopCount(destination, copyWithThis(visitedNodes)) + 1)
                .min()
                .orElse(UNREACHABLE);
    }

    private List<Node> copyWithThis(List<Node> originals) {
        List<Node> results = new ArrayList<>(originals);
        results.add(this);
        return results;
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
