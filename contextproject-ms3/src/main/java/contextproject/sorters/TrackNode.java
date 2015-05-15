package contextproject.sorters;

import contextproject.models.Track;

import java.util.ArrayList;

public class TrackNode {
  private Track track;
  private double score;
  private TrackNode parent;
  private ArrayList<TrackNode> children;

  public TrackNode(Track track) {
    this.track = track;
    this.score = 0;
    children = new ArrayList<TrackNode>();
    parent = null;
  }

  public TrackNode(Track track, double score) {
    this.track = track;
    this.score = score;
    children = new ArrayList<TrackNode>();
    parent = null;
  }

  public Track getTrack() {
    return this.track;
  }

  public double getScore() {
    return this.score;
  }

  public void setScore(double score) {
    this.score = score;
  }
  public void setParent(TrackNode trackNode){
    parent = trackNode;
  }
  public void addChild(TrackNode trackNode){
    children.add(trackNode);
  }
  public TrackNode getParent(){
    return parent;
  }
  public ArrayList<TrackNode> getChildren(){
    return children;
  }
  public boolean containsChild(TrackNode child){
    return children.contains(child);
  }
  public boolean hasParent(){
    return parent!=null;
  }

  @Override
  public boolean equals(Object that){
    if(that instanceof TrackNode){
      return (this.track.equals(((TrackNode) that).getTrack()));
    }
    return false;
  }

  public String toString(){
    return this.track.getTitle();
  }


}
