package it.unibo.generics.graph.impl;

import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.api.SearchStrategy;

public class GraphImpl<N> implements Graph<N> {

    //A Map with nodes as keys. For each node the related value is a set of nodes (edges).
    private Map<N, Set<N>> nodes = new HashMap<>();
    private final SearchStrategy<Step<N>> strategy;

    public GraphImpl(final SearchStrategy<Step<N>> strategy) {
        this.strategy = strategy;
    }

    @Override
    public void addNode(N node) {
        nodes.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void addEdge(N source, N target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        nodes.get(source).add(target);
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(nodes.keySet());
    }

    @Override
    public Set<N> linkedNodes(N node) {
        return nodes.get(node);
    }

    @Override
    public List<N> getPath(N source, N target) {
        /*
         * Using a Deque (double ended queue) since it
         * provides insertion and removal at both ends.
         * We can mimic both a Queue and a Stack based
         * on what strategy we choose (BFS or DFS).
         */
        final Deque<Step<N>> frontier = new LinkedList<>();
        frontier.add(new Step<N>(source));
        while (!frontier.isEmpty()) {
            final Step<N> lastStep = frontier.remove();
            final N currentNode = lastStep.getCurrentPosition();
            if (currentNode.equals(target)) {
                return lastStep.getPath();
            }
            expandFrontier(frontier, lastStep);
        }

        return Collections.emptyList();
    }

    /**
     * This method finds the neighbours of the lastStep and puts them in the frontier.
     * @param frontier
     * @param step to expand.
     */
    private void expandFrontier(final Deque<Step<N>> frontier, final Step<N> step) {
        final N currentNode = step.getCurrentPosition();
        //Adds all the reachable nodes (neighbours) of currentNode in the frontier.
        for (final N node : linkedNodes(currentNode)) {
            strategy.addToFrontier(frontier, new Step<>(step, node));
        }
    }

}
