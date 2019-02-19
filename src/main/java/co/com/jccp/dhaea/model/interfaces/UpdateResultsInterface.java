package co.com.jccp.dhaea.model.interfaces;

import co.com.jccp.dhaea.model.Individual;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public interface UpdateResultsInterface<T> {

    public void updateResults(int iteration, Individual<T> best);


}
