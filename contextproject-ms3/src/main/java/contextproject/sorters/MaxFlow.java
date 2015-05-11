package contextproject.sorters;

import contextproject.models.Track;

import org.jgrapht.alg.EdmondsKarpMaximumFlow;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.Map;

public class MaxFlow {

  private Map<DefaultWeightedEdge, Double> bestPath;
  private Track bestSource;
  private Track bestSink;

  public MaxFlow(Graph graph) {
    System.out.println(graph);
    int bestscore = 0;

    for (int i = 0; i < graph.vertexSet().size(); i++) {
      for (int j = 0; j < graph.vertexSet().size(); j++) {
        if (i != j) {
          Track source = (Track) graph.vertexSet().toArray()[i];
          Track sink = (Track) graph.vertexSet().toArray()[j];
          DefaultWeightedEdge edge1 = graph.getEdge(source, sink);
          DefaultWeightedEdge edge2 = graph.getEdge(sink, source);
          graph.removeEdge(edge1);
          graph.removeEdge(edge2);

          EdmondsKarpMaximumFlow<Track, DefaultWeightedEdge> edmondsKarp = new EdmondsKarpMaximumFlow<Track, DefaultWeightedEdge>(
              graph);

          edmondsKarp.calculateMaximumFlow(source, sink);
          System.out
              .println("\n" + "Source: " + edmondsKarp.getCurrentSource());
          System.out.println("Sink: " + edmondsKarp.getCurrentSink());
          System.out.println(edmondsKarp.getMaximumFlowValue());
          System.out.println(edmondsKarp.getMaximumFlow() + "\n");

          graph.addEdge(source, sink, edge1);
          graph.addEdge(sink, source, edge2);

          if (edmondsKarp.getMaximumFlowValue() > bestscore) {
            bestPath = edmondsKarp.getMaximumFlow();
            bestSource = edmondsKarp.getCurrentSource();
            bestSink = edmondsKarp.getCurrentSink();
          }

        }
      }
    }
  }
}
