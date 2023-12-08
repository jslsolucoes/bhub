package com.bhurb.payments.application.payments.chain;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.collect.Sets.difference;
import static com.google.common.collect.Sets.newHashSet;


/**
 * Topological sort of paymentHandlers.
 * See reference for how topological sort works <a href="https://en.wikipedia.org/wiki/Topological_sorting">...</a>
 */

public class TopologicalSort {

    private final Graph<Class<?>> set = new Graph<>();

    public List<Class<?>> all() {
        return set.topologicalOrder();
    }

    public void register(Class<?>... paymentHandlers) {
        for (Class<?> paymentHandler : paymentHandlers) {
            PaymentHandlerPriority paymentHandlerPriority = paymentHandler.getAnnotation(PaymentHandlerPriority.class);
            if (paymentHandlerPriority != null) {
                addEdges(paymentHandler, paymentHandlerPriority.before(), paymentHandlerPriority.after());
            } else {
                addEdges(paymentHandler, new Class[0], new Class[0]);
            }
        }
    }

    private void addEdges(final Class<?> paymentHandler,
                          final Class<?>[] before,
                          final Class<?>[] after) {
        set.addEdges(paymentHandler, before);

        for (Class<?> other : after) {
            set.addEdge(other, paymentHandler);
        }
    }

    static class Graph<E> {

        private final Multimap<E, E> graph = LinkedHashMultimap.create();
        private final Lock lock = new ReentrantLock();
        private List<E> orderedList;

        public void addEdge(E from, E to) {
            graph.put(from, to);
        }

        @SafeVarargs
        public final void addEdges(E from, E... tos) {

            if (tos.length == 0) {
                addEdge(from, null);
            } else {
                for (E to : tos) {
                    addEdge(from, to);
                }
            }
        }

        public List<E> topologicalOrder() {
            if (orderedList == null) {
                lock.lock();
                try {
                    if (orderedList == null) {
                        this.orderedList = orderTopologically();
                    }
                } finally {
                    lock.unlock();
                }
            }
            return this.orderedList;
        }

        private List<E> orderTopologically() {
            List<E> list = new ArrayList<>();

            while (!graph.keySet().isEmpty()) {
                Set<E> roots = findRoots();

                if (roots.isEmpty()) {
                    throw new IllegalStateException("There is a cycle on the payment handler sequence: \n" + cycle());
                }

                list.addAll(roots);
                removeRoots(roots);
            }
            return list;
        }

        private void removeRoots(Set<E> roots) {
            for (E root : roots) {
                graph.removeAll(root);
            }
        }

        private Set<E> findRoots() {
            return difference(graph.keySet(), newHashSet(graph.values())).immutableCopy();
        }

        private String cycle() {
            removeLeaves();

            return findCycle().toString();
        }

        private List<E> findCycle() {
            E node = firstElement(graph.keySet());
            List<E> cycle = new ArrayList<>();
            do {
                cycle.add(node);
            } while (!cycle.contains(node = firstElement(graph.get(node))));

            return cycle.subList(cycle.indexOf(node), cycle.size());
        }

        private E firstElement(Iterable<E> elements) {
            return elements.iterator().next();
        }

        private void removeLeaves() {
            Set<E> leaves = findLeaves();
            if (leaves.isEmpty()) {
                return;
            }

            for (E key : newHashSet(graph.keySet())) {
                for (E value : leaves) {
                    graph.remove(key, value);
                }
            }
            removeLeaves();
        }

        private Set<E> findLeaves() {
            return difference(newHashSet(graph.values()), graph.keySet());
        }
    }
}
