package contextproject.sorters;

import org.jgrapht.graph.DefaultWeightedEdge;

public class WeightedEdge extends DefaultWeightedEdge {

  private static final long serialVersionUID = 1L;

  /**
   * Return edge source.
   * 
   * @return Track
   */
  public Object getEdgeSource() {
    return getSource();
  }

  /**
   * Return edge target.
   * 
   * @return Track
   */
  public Object getEdgeTarget() {
    return getTarget();
  }

}
