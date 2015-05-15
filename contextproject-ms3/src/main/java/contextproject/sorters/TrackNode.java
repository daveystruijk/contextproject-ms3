package contextproject.sorters;

import contextproject.models.Track;

import java.util.ArrayList;

public class TrackNode {
  private Track track;
  private double score;
  private TrackNode parent;
  private ArrayList<TrackNode> children;

  /**
   * Tracknode constructor without score.
   * @param track Track
   */
  public TrackNode(Track track) {
    this.track = track;
    this.score = 0;
    children = new ArrayList<TrackNode>();
    parent = null;
  }

  /**
   * Tracknode constructor with score.
   * @param track Track
   * @param score Double score
   */
  public TrackNode(Track track, double score) {
    this.track = track;
    this.score = score;
    children = new ArrayList<TrackNode>();
    parent = null;
  }

  /**
   * Get track.
   * @return Track
   */
  public Track getTrack() {
    return this.track;
  }

  /**
   * Get score.
   * @return Double score
   */
  public double getScore() {
    return this.score;
  }

  /**
   * Set score.
   * @param score Double score
   */
  public void setScore(double score) {
    this.score = score;
  }

  /**
   * Set parent.
   * @param trackNode TrackNode parent
   */
  public void setParent(TrackNode trackNode) {
    parent = trackNode;
  }

  /**
   * Add child to arrrayList.
   * @param trackNode TrackNode child
   */
  public void addChild(TrackNode trackNode) {
    children.add(trackNode);
  }

  /**
   * Get parent.
   * @return TrackNode parent.
   */
  public TrackNode getParent() {
    return parent;
  }

  /**
   * Get children.
   * @return ArrayList(TrackNode)  children
   */
  public ArrayList<TrackNode> getChildren() {
    return children;
  }

  /**
   * Check if TrackNode is a child.
   * @param child TrackNode
   * @return boolean
   */
  public boolean containsChild(TrackNode child) {
    return children.contains(child);
  }

  /**
   * check if TrackNode has a parent.
   * @return boolean
   */
  public boolean hasParent() {
    return parent != null;
  }

  /**
   * Overrides normal equals method.
   */
  public boolean equals(Object that) {
    if (that instanceof TrackNode) {
      return (this.track.equals(((TrackNode) that).getTrack()));
    }
    return false;
  }

  /**
   * Overrides normal toString method.
   */
  public String toString() {
    return this.track.getTitle();
  }

}
