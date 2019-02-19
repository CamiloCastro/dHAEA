package co.com.jccp.dhaea.model.interfaces;

import co.com.jccp.dhaea.model.Individual;
import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public interface GetPopulationInterface<T> {

    MutableList<Individual<T>> getPopulation();


}
