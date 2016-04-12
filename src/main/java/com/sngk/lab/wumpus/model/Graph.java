package com.sngk.lab.wumpus.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * We have decided to change it to abstract class.
 */

public abstract class Graph<N extends Node<E>, E extends Edge> {

    private class NodeInfo {
        public boolean visited;
        public int distance;
        public E step;

        public NodeInfo(boolean visited, int distance, E step) {
            this.visited = visited;
            this.distance = distance;
            this.step = step;
        }
    }
    
    public final Map<String, N> nodes;
    public final Map<String, NodeInfo> infos;
    
    public Graph( Map<String, N> n ) {
        nodes = n;
        infos = new HashMap<>();
        for (String l : n.keySet()) {
            infos.put(l, new NodeInfo(false, 0, null));
        }
    }
    
    public Node getNode(String label ) {
        return nodes.get( label );
    }

    public NodeInfo getNodeInfo( N node ) {
        return infos.get( node.label() );
    }
    
    private N getNextNode() {
        // choose the node with the shortest distance from the start node
        // that hasn't been visited

        N next = null;
        int d = Integer.MAX_VALUE;
        for (N n : nodes.values()) {
            if ( getNodeInfo(n).visited )
                continue;
            if ( getNodeInfo(n).distance < d ) {
                d = getNodeInfo(n).distance;
                next = n;
            }
	    }
	    return next;
    }

    public Stack<E> findShortestPath(N n1, N n2) {
        
	// To work out the shortest path from n1 to n2, we use Dijkstra's algorithm, as 
	// described in Weiss, M. (2007) Data Structures and Algorithm Analysis in Java, 
	// Pearson Education, p. 337

        for (N n : nodes.values()) {
            getNodeInfo(n).step = null;
            getNodeInfo(n).distance = Integer.MAX_VALUE;
            getNodeInfo(n).visited = false;
        }

        getNodeInfo(n1).distance = 0;
        for ( ;; ) {
            N r = getNextNode();
            if ( r == null )
                break;
            getNodeInfo(r).visited = true;

            List<E> ale = r.edges();
            for ( E e : ale ) {
                N s = nodes.get( e.to() );
                if (!getNodeInfo(s).visited) {
                    if ( getNodeInfo(r).distance + e.distance() < getNodeInfo(s).distance ) {
                        getNodeInfo(s).distance = getNodeInfo(r).distance + e.distance();
                        getNodeInfo(s).step = e;
                     }
                }
            }
	    }

        //The path to n2 is stored in the step fields.
        Stack<E> path = new Stack<>();
        N t = n2;
        while ( getNodeInfo(t).step != null ) {
                path.push( getNodeInfo(t).step );
                t = nodes.get( getNodeInfo(t).step.from() );
        }
	    return path;
    }
	
    public int distance( N n1, N n2 ) {
        Stack<E> sp = findShortestPath( n1, n2 );
        return sp.size();
    }    
}
