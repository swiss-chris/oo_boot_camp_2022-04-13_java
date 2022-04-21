package com.nrkei.training.oo.graph;

import java.util.ArrayList;
import java.util.List;

class NullPath implements Path {

    private final List<Link> links = new ArrayList<>();

    NullPath() {}

    @Override
    public int hopCount() {
        return links.size();
    }

    @Override
    public double cost() {
        return Link.cost(links);
    }

    @Override
    public boolean contains(Node node) {
        return Link.contains(links, node);
    }

    @Override
    public NullPath prepend(Link link) {
        this.links.add(0, link);
        return this;
    }

}
