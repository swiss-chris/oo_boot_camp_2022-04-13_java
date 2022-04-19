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

    void prepend(Link link) { } // Ignore by default

    public abstract double cost();

    public abstract int hopCount();

    static class ActualPath extends Path {
        private final List<Link> links = new ArrayList<>();

        void prepend(Link link) {
            links.add(0, link);
        }

        public double cost() {
            return Link.cost(links);
        }

        public int hopCount() {
            return links.size();
        }

    }


    static class NoPath extends Path {

        public double cost() {
            return Double.POSITIVE_INFINITY;
        }

        public int hopCount() {
            return Integer.MAX_VALUE;
        }

    }

}
