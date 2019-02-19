package co.com.jccp.dhaea.model.selections;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.SelectionInterface;
import org.eclipse.collections.api.list.MutableList;


/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class RandomSelection<T> implements SelectionInterface<T> {

    @Override
    public MutableList<Individual<T>> applySelection(MutableList<Individual<T>> popToBeSelected, int selectionsNumber) {

        return popToBeSelected.shuffleThis().subList(0,selectionsNumber);

    }

}
