package it.unibo.collections;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int START_VALUE = 1000;
    private static final int END_VALUE = 2000;
    private static final int ELEMS = 100_000;
    private static final int CYCLES = 1000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> arrayList = new ArrayList<>();
        for (int i = START_VALUE; i < END_VALUE; i++) {
            arrayList.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linkedList = new LinkedList<>(arrayList);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int firstIndex = arrayList.indexOf(START_VALUE);
        final int lastIndex = arrayList.size() - 1;
        final int first = arrayList.get(firstIndex);
        final int last = arrayList.get(lastIndex);
        arrayList.set(firstIndex, last);
        arrayList.set(lastIndex, first);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (final int i : arrayList) {
            System.out.println(i);
        }

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long arrayListTime = System.nanoTime();
        for (int i = 0; i < ELEMS; i++) {
            arrayList.add(0, i);
        }
        arrayListTime = System.nanoTime() - arrayListTime;

        long linkedListTime = System.nanoTime();
        for (int i = 0; i < ELEMS; i++) {
            linkedList.add(0, i);
        }
        linkedListTime = System.nanoTime() - linkedListTime;
        
        final long arrayListTimeMs = TimeUnit.NANOSECONDS.toMillis(arrayListTime);
        final long linkedListTimeMs = TimeUnit.NANOSECONDS.toMillis(linkedListTime);
        System.out.println("[INSERT] ArrayList: " + arrayListTime + "ns (" + arrayListTimeMs + "ms)");
        System.out.println("[INSERT] LinkedList: " + linkedListTime + "ns (" + linkedListTimeMs + "ms)");
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
        int middleElemIndex = arrayList.size() / 2;
        long arrayListReadTime = System.nanoTime();
        for (int i = 0; i < CYCLES; i++) {
            arrayList.get(middleElemIndex);
        }
        arrayListReadTime = System.nanoTime() - arrayListReadTime;

        middleElemIndex = linkedList.size() / 2;
        long linkedListReadTime = System.nanoTime();
        for (int i = 0; i < CYCLES; i++) {
            linkedList.get(middleElemIndex);
        }
        linkedListReadTime = System.nanoTime() - linkedListReadTime;

        final long arrayListReadTimeMs = TimeUnit.NANOSECONDS.toMillis(arrayListReadTime);
        final long linkedListReadTimeMs = TimeUnit.NANOSECONDS.toMillis(linkedListReadTime);
        System.out.println("[READ] ArrayList: " + arrayListReadTime + "ns (" + arrayListReadTimeMs + "ms)");
        System.out.println("[READ] LinkedList: " + linkedListReadTime + "ns (" + linkedListReadTimeMs + "ms)");

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> populations = new HashMap<>();
        populations.put("Africa", 1_110_635_000L);
        populations.put("Americas", 972_005_000L);
        populations.put("Antarctica", 0L);
        populations.put("Asia", 4_298_723_000L);
        populations.put("Europe", 742_452_000L);
        populations.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
        List<Long> populationList = new ArrayList<>(populations.values());
        Iterator<Long> populationIterator = populationList.iterator();
        long totalPopulation = 0;
        while (populationIterator.hasNext()) {
            totalPopulation += populationIterator.next();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        System.out.println("Total Population: " + formatter.format(totalPopulation));
    }
}
