package contextproject.sorters;

import com.googlecode.jctree.ArrayListTree;
import com.googlecode.jctree.NodeNotFoundException;
import com.googlecode.jctree.Tree;

import contextproject.models.Track;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.EdmondsKarpMaximumFlow;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MaxFlow {

  private DirectedGraph<Track, WeightedEdge> graph;
  private Map<WeightedEdge, Double> bestPath;
  private Track bestSource;
  private Track bestSink;

  public MaxFlow(Graph graph) {
    System.out.println(graph);
    this.graph = graph;
    calculateMaxflow();
    convertMap();
    System.out.println(bestPath.toString() + "\n" + bestSource.toString()
        + "\n" + bestSink.toString());
    System.out.println(getOptimalPath().toString());
    System.out.println("finished");
  }

  public void calculateMaxflow() {
    Double bestscore = 0.0;

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
          // System.out
          // .println("\n" + "Source: " + edmondsKarp.getCurrentSource());
          // System.out.println("Sink: " + edmondsKarp.getCurrentSink());
          // System.out.println(edmondsKarp.getMaximumFlowValue());
          // System.out.println(edmondsKarp.getMaximumFlow() + "\n");

          graph.addEdge(source, sink, edge1);
          graph.addEdge(sink, source, edge2);

          if (edmondsKarp.getMaximumFlowValue() > bestscore) {
            bestscore = edmondsKarp.getMaximumFlowValue();
            bestPath = edmondsKarp.getMaximumFlow();
            bestSource = edmondsKarp.getCurrentSource();
            bestSink = edmondsKarp.getCurrentSink();
          }

        }

      }
    }
  }

  private void convertMap() {
    Map<WeightedEdge, Double> tempMap = new HashMap<WeightedEdge, Double>(
        bestPath);

    for (WeightedEdge edge : bestPath.keySet()) {
      if (tempMap.containsKey(edge)) {
        double highScore = tempMap.get(edge);
        WeightedEdge edge2 = graph.getEdge((Track) edge.getEdgeTarget(),
            (Track) edge.getEdgeSource());
        double highScore2 = tempMap.get(edge2);

        if (!(highScore == 0 && highScore2 == 0)) {

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
    bestPath = new HashMap<WeightedEdge, Double>(tempMap);
  }

  public ArrayList<Track> getOptimalPath() {
    TrackTree tree = new TrackTree(new TrackNode(bestSource));
    ArrayList<TrackNode> children = new ArrayList<TrackNode>();
    ArrayList<TrackNode> newChildren = new ArrayList<TrackNode>();
    ArrayList<TrackNode> finishedNodes = new ArrayList<TrackNode>();
    children.add(new TrackNode(bestSource));
    while (children.size() > 0) {
      newChildren = new ArrayList<TrackNode>();
      for (WeightedEdge edge : bestPath.keySet()){ //get al possible childs
        TrackNode possibleChild = new TrackNode( (Track) edge.getEdgeTarget() ,bestPath.get(edge));
        TrackNode possibleParent = new TrackNode((Track) edge.getEdgeSource());

        if(children.contains(possibleParent)){
          for(TrackNode parent : children){
            if( parent.equals(possibleParent) && !tree.hasAncestor(parent, possibleChild) && !possibleChild.equals(new TrackNode(bestSource))){
              if(possibleChild.equals(new TrackNode(bestSink))){
                finishedNodes.add(possibleChild);
              }
              else{
                newChildren.add(possibleChild);
              }
              parent.addChild(possibleChild);  
              possibleChild.setParent(parent);
            }
          }
        }
      }
      children = newChildren;
    }
    return tree.optimalPath(finishedNodes);

  }
}
