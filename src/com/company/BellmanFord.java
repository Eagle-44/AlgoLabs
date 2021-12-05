package com.company;


import java.util.ArrayList;
import java.util.List;

public class BellmanFord {

    public static class Edge {
        int weight;
        int from, to;

        public Edge(int from, int to, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }
    }

    public static List<Edge>[] createGraph(final int V) {
        List[] graph = new List[V];
        for (int i = 0; i < V; i++) graph[i] = new ArrayList<>();
        return graph;
    }

    public static void addEdge(List<Edge>[] graph, int from, int to, int weight) {
        graph[from].add(new Edge(from, to, weight));
    }


    public static int[] bellmanFord(List<Edge>[] graph, int V, int start) {

        int [] dist = new int [V];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;

        for (int i = 0; i < V - 1; i++)
            for (List<Edge> edges : graph)
                for (Edge edge : edges)
                    if (dist[edge.from] + edge.weight < dist[edge.to])
                        dist[edge.to] = dist[edge.from] + edge.weight;


        for (int i = 0; i < V - 1; i++)
            for (List<Edge> edges : graph)
                for (Edge edge : edges)
                    if (dist[edge.from] + edge.weight < dist[edge.to]) dist[edge.to] = Integer.MAX_VALUE;
        return dist;
    }

    public static void main(String[] args) {

        int E = 10, V = 9, start = 0;
        List<Edge>[] graph = createGraph(V);
        addEdge(graph, 0, 1, 1);
        addEdge(graph, 1, 2, 1);
        addEdge(graph, 2, 4, 1);
        addEdge(graph, 4, 3, -3);
        addEdge(graph, 3, 2, 1);
        addEdge(graph, 1, 5, 4);
        addEdge(graph, 1, 6, 4);
        addEdge(graph, 5, 6, 5);
        addEdge(graph, 6, 7, 4);
        addEdge(graph, 5, 7, 3);

        int[] d = bellmanFord(graph, V, start);

        for (int i = 0; i < V; i++)
            System.out.printf("The weight to get from node %d to %d is %d\n", start, i, d[i]);

    }
}