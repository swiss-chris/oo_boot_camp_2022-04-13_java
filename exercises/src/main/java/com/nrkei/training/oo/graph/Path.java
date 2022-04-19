/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.List;

// Understands a particular route from one Node to another
public class Path {
    private final List<Link> links = new ArrayList<>();

    Path() {} // locking down constructor to package only

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
