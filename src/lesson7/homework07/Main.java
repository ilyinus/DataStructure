package lesson7.homework07;

import lesson7.Graph;

public class Main {
    private static Graph graph;

    public static void initGraph() {
        graph = new Graph(10);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Липецк");
        graph.addVertex("Рязань");
        graph.addVertex("Тамбов");
        graph.addVertex("Саратов");
        graph.addVertex("Калуга");
        graph.addVertex("Орел");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");

        graph.addEdges("Москва","Тула", "Рязань", "Калуга");
        graph.addEdges("Тула","Липецк");
        graph.addEdges("Липецк","Воронеж");
        graph.addEdges("Рязань","Тамбов");
        graph.addEdges("Тамбов","Саратов");
        graph.addEdges("Саратов","Воронеж");
        graph.addEdges("Калуга","Орел");
        graph.addEdges("Орел","Курск");
        graph.addEdges("Курск","Воронеж");
    }

    public static void main(String[] args) {
        initGraph();
        System.out.println(graph.findShortestWay("Москва", "Воронеж"));
    }

}
