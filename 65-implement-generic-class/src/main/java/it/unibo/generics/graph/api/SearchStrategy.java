package it.unibo.generics.graph.api;

import java.util.Deque;

public interface SearchStrategy<S> {

    /**
     * Adds to the frontier a Set of a node's neighbours.
     * @param frontier
     * @param step a new step composed by a new neighbour and the previous step.
     */
    public void addToFrontier(final Deque<S> frontier, final S step);
}
