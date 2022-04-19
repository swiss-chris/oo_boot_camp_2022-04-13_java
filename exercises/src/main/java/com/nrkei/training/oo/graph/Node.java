/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.List;

// Understands its neighbors
public class Node {
    private static final int UNREACHABLE = -1;

    private final List<Node> neighbors = new ArrayList<>();

    public Node to(Node neighbor) {
        neighbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return hopCount(destination, noVisitedNodes()) != UNREACHABLE;
    }

    public int hopCount(Node destination) {
        int result = hopCount(destination, noVisitedNodes());
        if (result == UNREACHABLE) throw new IllegalArgumentException("Destination is not reachable");
        return result;
    }

    private int hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        visitedNodes.add(this);
        int champion = UNREACHABLE;
        for(Node n : neighbors) {
            int neighborHopCount = n.hopCount(destination, visitedNodes);
            if (neighborHopCount == UNREACHABLE) continue;
            if (champion == UNREACHABLE || neighborHopCount + 1 < champion) champion = neighborHopCount + 1;
        }
        return champion;
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
