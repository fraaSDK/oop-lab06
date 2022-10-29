package it.unibo.generics.graph.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Step<N> {
    
    private Step<N> previousSteps;
    private N currentPosition;
    
    public Step(final N currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Step(final Step<N> previousSteps, final N currentPosition) {
        this.previousSteps = previousSteps;
        this.currentPosition = currentPosition;
    }
    
    public N getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * @return the path from the current node to the source.
     */
    public List<N> getPath() {
        final List<N> path = new LinkedList<>();
        Step<N> step = this;
        while (Objects.nonNull(step)) {
            //Head Insert
            path.add(0, step.currentPosition);
            step = step.previousSteps;
        }
        return path;
    }
    
}
