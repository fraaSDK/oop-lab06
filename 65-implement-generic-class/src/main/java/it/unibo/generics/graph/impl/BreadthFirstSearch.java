package it.unibo.generics.graph.impl;

import java.util.Deque;

import it.unibo.generics.graph.api.SearchStrategy;

public class BreadthFirstSearch<S> implements SearchStrategy<S> {

    @Override
    public void addToFrontier(final Deque<S> frontier, final S step) {
        //BFS uses a Queue. (Adds elements to the end of the queue).
        frontier.addLast(step);
    }

}
