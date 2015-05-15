package contextproject.sorters;

import com.googlecode.jctree.NodeNotFoundException;
import com.sun.javafx.fxml.builder.TriangleMeshBuilder;

import contextproject.models.Track;

import java.util.ArrayList;
import java.util.Collections;

public class TrackTree{
  
  private TrackNode root;
  
  public TrackTree(TrackNode root){
    this.root = root;
  }

  public boolean hasAncestor(TrackNode trackNode, TrackNode possibleAncestor) {
    boolean res = true;
    if(!trackNode.equals(possibleAncestor)){
      if(!trackNode.hasParent()){
        res = false;
      }
      else{
        res = hasAncestor(trackNode.getParent(), possibleAncestor);
      }
    }
    return res;
  }
  
  public TrackNode getRoot(){
    return root;
  }
  
  public ArrayList<Track> optimalPath(ArrayList<TrackNode> endTracks){
    TrackNode bestPath = root;
    double bestScore = 0.0;
    double bestLowest = 0.0;
    int bestDepth = 0;
    for(TrackNode trackNode : endTracks){
      TrackNode track = trackNode;
      double tempScore = 0.0;
      int tempDepth = 1;
      double tempLowest = Double.MAX_VALUE;
      
      while(trackNode.hasParent()){
        tempScore+=trackNode.getScore();
        if(tempScore<tempLowest){
          tempLowest = tempScore;
        }
        tempDepth++;
        trackNode = trackNode.getParent();
      }
      if(tempDepth>bestDepth){
        bestScore = tempScore;
        bestDepth = tempDepth;
        bestLowest = tempLowest;
        bestPath = track;
      }
      else if(tempDepth == bestDepth && tempLowest>bestLowest){
        bestScore = tempScore;
        bestDepth = tempDepth;
        bestLowest = tempLowest;
        bestPath = track;
      }
    }
    
    ArrayList<Track> res = new ArrayList<Track>();
    res.add(bestPath.getTrack());
    while(bestPath.hasParent()){
      bestPath = bestPath.getParent();
      res.add(bestPath.getTrack());
    }
    Collections.reverse(res);
    return res;
  }
  
}
