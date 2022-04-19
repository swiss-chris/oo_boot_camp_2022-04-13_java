/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.List;

// Understands a particular route from one Node to another
public abstract class Path {

    static Path NONE = new NoPath();

    Path prepend(Link link) { return this; }

    public abstract double cost();

    public abstract int hopCount();

    static class ActualPath extends Path {
        private final List<Link> links = new ArrayList<>();

        @Override
        Path prepend(Link link) {
            links.add(0, link);
            return this;
        }

        @Override
        public double cost() {
            return Link.cost(links);
        }

        @Override
        public int hopCount() {
            return links.size();
        }

    }

    static class NoPath extends Path {

        @Override
        public double cost() {
            return Double.POSITIVE_INFINITY;
        }

        @Override
        public int hopCount() {
            return Integer.MAX_VALUE;
        }
    }
}
