package edu.ucla.java.callgraph.util;

public class Edge {

    private Node src, dest;

    Edge(Node src, Node dest){
        this.src = src;
        this.dest = dest;
    }

    public Node getSrc() {
        return src;
    }

    public Node getDest() {
        return dest;
    }

}
