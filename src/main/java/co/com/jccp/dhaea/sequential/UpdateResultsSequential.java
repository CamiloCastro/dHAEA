package co.com.jccp.dhaea.sequential;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.IndividualResults;
import co.com.jccp.dhaea.model.interfaces.UpdateResultsInterface;
import lombok.Data;
import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
public class UpdateResultsSequential<T> implements UpdateResultsInterface<T> {

    MutableList<IndividualResults<T>> results;

    @Override
    public void updateResults(int iteration, Individual<T> best) {

    }
}
