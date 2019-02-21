package co.com.jccp.dhaea.model.interfaces;

import co.com.jccp.dhaea.model.Individual;
import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public interface PopulationHandlerInterface<T> {

    MutableList<Individual<T>> getPopulation();

    void updateResults(int iteration, int individual, Individual<T> best);

    boolean isAllDone();

    void updatePopulation();

    void updateAll(MutableList<Individual<T>> newPop, int iteration );

    void printFinalResult();

    void printBehaviourOfIndividual(int individual);

    void printBestOfEachIteration();

    Individual<T> getBestOfIteration(int i);

}
