package contextproject.sorters;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class WeightedEdge extends DefaultWeightedEdge{
  
  public Object getEdgeSource(){
    return getSource();
  }
  
  public Object getEdgeTarget(){
    return getTarget();
  }

}
