package com.alex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kruskal {
    public List<Integer> vertexes;
    public List<Edge> edges;

    public Kruskal() {
        this.vertexes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal();
        kruskal.addVertex(5);
        kruskal.addVertex(4);
        kruskal.addVertex(6);
        kruskal.addVertex(1);
        kruskal.addVertex(8);
        kruskal.addVertex(7);
        kruskal.addEdge(5, 1, 1);
        kruskal.addEdge(5, 4, 2);
        kruskal.addEdge(4, 1, 10);
        kruskal.addEdge(4, 6, 3);
        kruskal.addEdge(5, 6, 0);
        kruskal.addEdge(6, 8, 9);
        kruskal.addEdge(4, 8, -1);
        kruskal.addEdge(5, 7, 3);
        kruskal.addEdge(1, 7, 4);
        kruskal.addEdge(8, 7, 2);
        kruskal.kruskalMst();
    }

    public void addVertex(int vertex) {
        if (!vertexes.contains(vertex)) {
            vertexes.add(vertex);
        }
    }

    public void addEdge(int from, int to, int weight) {
        if (!vertexes.contains(from) || !vertexes.contains(to)) {
            return;
        }
        Edge edgeToPut = new Edge(from, to, weight);
        for (Edge edge : edges) {
            if (edge.from.equals(edgeToPut.from) && edge.to.equals(edgeToPut.to)) {
                edge.weight = weight;
                return;
            } else if (edge.to.equals(edgeToPut.from) && edge.from.equals(edgeToPut.to)) {
                edge.weight = weight;
                return;
            }
        }
        edges.add(edgeToPut);
    }

    public void kruskalMst() {
        Map<Integer,Integer> mapConnectivity = new HashMap<>();
        List<Edge> res = new ArrayList<>();
        edges.sort((Edge e1, Edge e2) -> Integer.compare(e1.weight, e2.weight));

        int i = 1;
        for (Edge edge : edges) {
            if(!mapConnectivity.containsKey(edge.from) && !mapConnectivity.containsKey(edge.to)){
                mapConnectivity.put(edge.to,i);
                mapConnectivity.put(edge.from,i);
                res.add(edge);
                i++;
            }else if(mapConnectivity.containsKey(edge.from) && mapConnectivity.containsKey(edge.to)){
                if(!mapConnectivity.get(edge.from).equals(mapConnectivity.get(edge.to))){
                    res.add(edge);
                    Integer rightConnectivity = mapConnectivity.get(edge.from);
                    Integer leftConnectivity = mapConnectivity.get(edge.to);
                    for (Map.Entry<Integer, Integer> entry : mapConnectivity.entrySet()) {
                        if(entry.getValue().equals(rightConnectivity) || entry.getValue().equals(leftConnectivity) ){
                            mapConnectivity.replace(entry.getKey(),i);
                        }
                    }
                    i++;
                }
            }else if (mapConnectivity.containsKey(edge.from) && !mapConnectivity.containsKey(edge.to)) {
                mapConnectivity.put(edge.to, i);
                res.add(edge);
                Integer currentConnectivity = mapConnectivity.get(edge.from);
                for (Map.Entry<Integer, Integer> entry : mapConnectivity.entrySet()) {
                    if (entry.getValue().equals(currentConnectivity)) {
                        mapConnectivity.replace(entry.getKey(), i);
                    }
                }
                i++;
            } else if (!mapConnectivity.containsKey(edge.from) && mapConnectivity.containsKey(edge.to)) {
                mapConnectivity.put(edge.from, i);
                res.add(edge);
                Integer currentConnectivity = mapConnectivity.get(edge.to);
                for (Map.Entry<Integer, Integer> entry : mapConnectivity.entrySet()) {
                    if (entry.getValue().equals(currentConnectivity)) {
                        mapConnectivity.replace(entry.getKey(), i);
                    }
                }
                i++;
            }
        }

        System.out.println(mapConnectivity);
        System.out.println(res);
        int mstWeight = 0;
        for (Edge edge:res) {
            mstWeight += edge.weight;
        }
        System.out.println(mstWeight);
    }

    class Edge {
        Integer weight;
        Integer from, to;

        public Edge(int from, int to, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + " <-" + weight + "-> " + to;
        }
    }
}