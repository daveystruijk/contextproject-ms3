package contextproject.sorters;

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
  private int maxDepth = 5;

  /**
   * Constructor for a MaxFlow calculator.
   * 
   * @param graph
   *          Graph
   */
  public MaxFlow(Graph graph) {
    this.graph = graph;
    optimalPath = new Playlist();
    calculateMaxflow();

    System.out.println(bestSource + "\n" + bestSink + "\n" + bestFlow.toString());
    convertMap();
    calculateOptimalPath();
    System.out.println(optimalPath.toString());

  }

  /**
   * calculate the maximum flow from graph.
   */
  private void calculateMaxflow() {
    Double bestscore = 0.0;
    Double bestAverage = 0.0;
    int bestCountNonZero = 0;
    for (int i = 0; i < graph.vertexSet().size(); i++) {
      for (int j = 0; j < graph.vertexSet().size(); j++) {
        if (i != j) {
          Track source = (Track) graph.vertexSet().toArray()[i];
          Track sink = (Track) graph.vertexSet().toArray()[j];
          WeightedEdge edge1 = graph.getEdge(source, sink);
          WeightedEdge edge2 = graph.getEdge(sink, source);

          graph.removeEdge(edge1);
          graph.removeEdge(edge2);

          EdmondsKarpMaximumFlow<Track, WeightedEdge> edmondsKarp = new EdmondsKarpMaximumFlow<Track, WeightedEdge>(
              graph);
          edmondsKarp.calculateMaximumFlow(source, sink);

          graph.addEdge(source, sink, edge1);
          graph.addEdge(sink, source, edge2);
          double average = 0.0;
          int countNonZero = 0;
          for (Double value : edmondsKarp.getMaximumFlow().values()) {
            {
              average += value;
              if (value > 0) {
                countNonZero++;
              }
            }
            boolean add = false;
            if (countNonZero > bestCountNonZero) {
              add = true;
            } else if (countNonZero == bestCountNonZero && average > bestAverage) {
              add = true;
            } else if (countNonZero == bestCountNonZero && average == bestAverage
                && edmondsKarp.getMaximumFlowValue() > bestscore) {
              add = true;
            }

            if (add) {
              bestscore = edmondsKarp.getMaximumFlowValue();
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
    Collections.reverse(scores);

    double minScore = 0.5;
    // if (scores.size() > 19) {
    // minScore = scores.get(19);
    // }

    for (WeightedEdge edge : bestFlow.keySet()) {
      if (tempMap.containsKey(edge)) {
        double highScore = tempMap.get(edge);
        WeightedEdge edge2 = graph.getEdge((Track) edge.getEdgeTarget(),
            (Track) edge.getEdgeSource());
        double highScore2 = tempMap.get(edge2);

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
    ArrayList<TrackNode> children = new ArrayList<TrackNode>();
    ArrayList<TrackNode> newChildren = new ArrayList<TrackNode>();
    ArrayList<TrackNode> finishedNodes = new ArrayList<TrackNode>();
    children.add(new TrackNode(bestSource));
    int depth = 0;
    while (children.size() > 0 && depth < maxDepth) {
      newChildren = new ArrayList<TrackNode>();
      for (WeightedEdge edge : bestFlow.keySet()) {
        TrackNode possibleChild = new TrackNode((Track) edge.getEdgeTarget(), bestFlow.get(edge));
        TrackNode possibleParent = new TrackNode((Track) edge.getEdgeSource());

        if (children.contains(possibleParent)) {
          for (TrackNode parent : children) {
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
      depth++;
      children = newChildren;
    }
    trackTree = tree;
    System.out.println(children.size() + "    " + finishedNodes.size());
    if (finishedNodes.size() > 0) {
      if (optimalPath.size() > 1) {
        optimalPath.addAll(trackTree.optimalPath(finishedNodes).subList(1, maxDepth - 1));
      } else {
        optimalPath = trackTree.optimalPath(finishedNodes);
      }

    } else {
      if (optimalPath.size() > 1) {
        optimalPath.addAll(trackTree.optimalPath(children).subList(1, maxDepth - 1));
      } else {
        optimalPath = trackTree.optimalPath(children);
      }
      bestSource = optimalPath.get(optimalPath.size() - 1);
      System.out.println(optimalPath.toString());
      calculateOptimalPath();
    }
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
