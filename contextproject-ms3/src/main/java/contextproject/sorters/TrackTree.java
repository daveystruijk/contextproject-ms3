package contextproject.sorters;

import contextproject.models.Track;

import java.util.ArrayList;
import java.util.Collections;

public class TrackTree {

  private TrackNode root;

  public TrackTree(TrackNode root) {
    this.root = root;
  }

  /**
   * Check is a TrackNode is a ancestor of other TrackNode.
   * @param trackNode TrackNode
   * @param possibleAncestor TrackNode
   * @return boolean
   */
  public boolean hasAncestor(TrackNode trackNode, TrackNode possibleAncestor) {
    boolean res = true;
    if (!trackNode.equals(possibleAncestor)) {
      if (!trackNode.hasParent()) {
        res = false;
      } else {
        res = hasAncestor(trackNode.getParent(), possibleAncestor);
      }
    }
    return res;
  }

  public TrackNode getRoot() {
    return root;
  }

  /**
   * Return best path Depth > Score > Highest minimum.
   * @param endTracks ArrayList(TrackNode)
   * @return ArrayList(Track)
   */
  public ArrayList<Track> optimalPath(ArrayList<TrackNode> endTracks) {
    TrackNode bestPath = root;
    double bestScore = 0.0;
    double bestLowest = 0.0;
    int bestDepth = 0;
    for (TrackNode trackNode : endTracks) {
      TrackNode track = trackNode;
      double tempScore = 0.0;
      int tempDepth = 1;
      double tempLowest = Double.MAX_VALUE;

      while (trackNode.hasParent()) {
        tempScore += trackNode.getScore();
        if (tempScore < tempLowest) {
          tempLowest = tempScore;
        }
        tempDepth++;
        trackNode = trackNode.getParent();
      }
      if (tempDepth > bestDepth) {
        bestScore = tempScore;
        bestDepth = tempDepth;
        bestLowest = tempLowest;
        bestPath = track;
      } else if (tempDepth == bestDepth && tempScore > bestScore) {
        bestScore = tempScore;
        bestDepth = tempDepth;
        bestLowest = tempLowest;
        bestPath = track;
      } else if (tempDepth == bestDepth && tempScore == bestScore
          && tempLowest > bestLowest) {
        bestScore = tempScore;
        bestDepth = tempDepth;
        bestLowest = tempLowest;
        bestPath = track;
      }
    }

    ArrayList<Track> res = new ArrayList<Track>();
    res.add(bestPath.getTrack());
    while (bestPath.hasParent()) {
      bestPath = bestPath.getParent();
      res.add(bestPath.getTrack());
    }
    Collections.reverse(res);
    return res;
  }

}
