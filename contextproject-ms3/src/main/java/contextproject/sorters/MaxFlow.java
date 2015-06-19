
package contextproject.sorters;

import contextproject.AppConfig;
import contextproject.models.Playlist;
import contextproject.models.Track;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.EdmondsKarpMaximumFlow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MaxFlow {

  private DirectedGraph<Track, WeightedEdge> graph;
  private Map<WeightedEdge, Double> bestFlow;
  private Track bestSource;
  private Track bestSink;
  private TrackTree trackTree;
  private Playlist optimalPath;
  private Double bestScore;
  private Double bestAverage;
  private int bestCountNonZero;
  private ArrayList<WeightedEdge> done;
  private static EdmondsKarpMaximumFlow<Track, WeightedEdge> edmondsKarp;

  /**
   * Constructor for a MaxFlow calculator.
   * 
   * @param graph
   *          Graph
   */
  public MaxFlow(Graph graph) {
    this.graph = graph;
    this.optimalPath = new Playlist();
    calculateMaxflow();
    try {
      convertMap();
      calculateOptimalPath();
    } catch (NullPointerException e) {
      for (WeightedEdge edge : graph.edgeSet()) {
        optimalPath.add((Track) edge.getEdgeSource());
      }
    }
  }
  
  /**
   * Set up variables for maxFlow.
   */
  private void setUp() {
    bestScore = 0.0;
    bestAverage = 0.0;
    bestCountNonZero = 0;
    done = new ArrayList<WeightedEdge>();
  }

  /**
   * calculate the maximum flow from graph.
   */
  private void calculateMaxflow() {
    setUp();

    for (int i = 0; i < graph.vertexSet().size(); i++) {
      for (int j = 0; j < graph.vertexSet().size(); j++) {
        if (i != j) {
          Track source = (Track) graph.vertexSet().toArray()[i];
          Track sink = (Track) graph.vertexSet().toArray()[j];
          WeightedEdge edge1 = graph.getEdge(source, sink);
          WeightedEdge edge2 = graph.getEdge(sink, source);
          if (!done.contains(edge1) && !done.contains(edge2)) {
            graph.removeEdge(edge1); // remove the edges directly from source to sink
            graph.removeEdge(edge2);

            edmondsKarp = new EdmondsKarpMaximumFlow<Track, WeightedEdge>(graph);
            edmondsKarp.calculateMaximumFlow(source, sink); // calculate max flow
            graph.addEdge(source, sink, edge1);
            graph.addEdge(sink, source, edge2);

            done.add(edge1);
            done.add(edge2);
            double average = 0.0;
            int countNonZero = 0;
            // calculate average, and the total non-zero's
            for (Double value : edmondsKarp.getMaximumFlow().values()) {
              average += value;
              if (value > 0) {
                countNonZero++;
              }
            }
            average /= (double) edmondsKarp.getMaximumFlow().values().size();

            if ((countNonZero > bestCountNonZero)
                | (countNonZero == bestCountNonZero && average > bestAverage)
                | (countNonZero == bestCountNonZero && average == bestAverage && edmondsKarp
                    .getMaximumFlowValue() > bestScore)) {
              bestScore = edmondsKarp.getMaximumFlowValue();
              bestFlow = edmondsKarp.getMaximumFlow();
              bestSource = edmondsKarp.getCurrentSource();
              bestSink = edmondsKarp.getCurrentSink();
              bestAverage = average;
              bestCountNonZero = countNonZero;
            }
          }
        }
      }

    }
  }

  /**
   * Convert the map so that all directed path exist in both ways with same score.
   */
  private void convertMap() {
    Map<WeightedEdge, Double> tempMap = new HashMap<WeightedEdge, Double>(bestFlow);
    ArrayList<Double> scores = new ArrayList<Double>();
    scores.addAll(bestFlow.values());
    Collections.sort(scores);
    Collections.reverse(scores); // begin with highest score

    double minScore = 1;

    for (WeightedEdge edge : bestFlow.keySet()) {
      if (tempMap.containsKey(edge)) {
        double highScore = tempMap.get(edge);
        WeightedEdge edge2 = graph.getEdge((Track) edge.getEdgeTarget(),
            (Track) edge.getEdgeSource());
        double highScore2 = tempMap.get(edge2);

        // check if it's higher than minimal score
        if (!(highScore < minScore && highScore2 < minScore)) {

          if (highScore > highScore2) {
            tempMap.put(edge2, highScore);
          } else {
            tempMap.put(edge, highScore2);
          }

        } else {
          tempMap.remove(edge);
          tempMap.remove(edge2);
        }
      }
    }
    bestFlow = new HashMap<WeightedEdge, Double>(tempMap);
  }

  /**
   * calculate longest possible path from the maximum flow.
   * 
   * @return ArrayList(Track)
   */
  private void calculateOptimalPath() {
    TrackTree tree = new TrackTree(new TrackNode(bestSource));
    ArrayList<TrackNode> parents = new ArrayList<TrackNode>();
    ArrayList<TrackNode> newChildren = new ArrayList<TrackNode>();
    ArrayList<TrackNode> finishedNodes = new ArrayList<TrackNode>();
    parents.add(new TrackNode(bestSource));
    while (parents.size() > 0) {
      newChildren = new ArrayList<TrackNode>();
      for (WeightedEdge edge : bestFlow.keySet()) {
        TrackNode possibleChild = new TrackNode((Track) edge.getEdgeTarget(), bestFlow.get(edge));
        TrackNode possibleParent = new TrackNode((Track) edge.getEdgeSource());
        if (parents.contains(possibleParent)) {
          for (TrackNode parent : parents) {
            if (parent.equals(possibleParent) && !tree.hasAncestor(parent, possibleChild)
                && !possibleChild.equals(new TrackNode(bestSource))
                && !optimalPath.contains(possibleChild.getTrack())) {
              if (possibleChild.equals(new TrackNode(bestSink))) {
                finishedNodes.add(possibleChild);
              } else {
                newChildren.add(possibleChild);
              }
              parent.addChild(possibleChild);
              possibleChild.setParent(parent);
            }
          }
        }
      }
      if (newChildren.size() > 0) {
        finishedNodes = new ArrayList<TrackNode>();
      }
      Collections.sort(newChildren);
      Collections.reverse(newChildren);
      if (newChildren.size() > AppConfig.maxFlowDepth) {
        parents = new ArrayList<TrackNode>();
        parents.addAll(newChildren.subList(0, 9));
      } else {
        parents = newChildren;
      }
    }
    trackTree = tree;
    optimalPath = trackTree.optimalPath(finishedNodes);
  }

  /**
   * Get optimal Path.
   * 
   * @return ArrayList(Track)
   */
  public ArrayList<Track> getOptimalPath() {
    return optimalPath;
  }

}
