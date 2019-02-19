package co.com.jccp.dhaea.model.interfaces;

import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public interface InitializationInterface<T> {

    MutableList<T> generateIndividual(int dimensions, T[][] limits);

}
