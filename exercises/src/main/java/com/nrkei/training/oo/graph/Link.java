/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.List;

// Understands a connection from one Node to another
class Link {
    static CostStrategy LEAST_COST = (double cost) -> cost;
    static CostStrategy FEWEST_HOPS = (double cost) -> 1.0;

    private final double cost;
    private final Node target;

    Link(double cost, Node target) {
        this.cost = cost;
        this.target = target;
    }

    double cost(Node destination, List<Node> visitedNodes, CostStrategy strategy) {
        return target.cost(destination, visitedNodes, strategy) + strategy.cost(cost);
    }

    interface CostStrategy {
        double cost(double amount);
    }
}
