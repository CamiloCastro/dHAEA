package co.com.jccp.dhaea.model.interfaces;

import co.com.jccp.dhaea.model.Individual;
import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public interface SelectionInterface<T> {

    MutableList<Individual<T>> applySelection(MutableList<Individual<T>> popToBeSelected, int selectionsNumber);

}
