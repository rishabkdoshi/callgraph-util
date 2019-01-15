package edu.ucla.java.callgraph.util;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphUtil {
    public static Graph buildGraph(Map<String, Set<String>> adjList) {
        Graph g = new Graph();

        adjList.entrySet().forEach(entry -> {

            String src = entry.getKey();

            Set<String> destSet = entry.getValue();

            destSet.forEach(dest -> {
                g.createOrRetrieveEdge(src, dest);
            });
        });

        return g;
    }

}
