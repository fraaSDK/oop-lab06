package it.unibo.generics.graph.impl;

import java.util.Deque;

import it.unibo.generics.graph.api.SearchStrategy;

public class DepthFirstSearch<S> implements SearchStrategy<S> {

    @Override
    public void addToFrontier(final Deque<S> frontier, final S step) {
        //DFS uses a Stack. (Adds (push) elements to the top of the stack).
        frontier.addFirst(step);
    }

}
