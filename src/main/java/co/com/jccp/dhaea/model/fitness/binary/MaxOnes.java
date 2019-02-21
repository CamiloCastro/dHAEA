package co.com.jccp.dhaea.model.fitness.binary;

import co.com.jccp.dhaea.model.interfaces.FitnessInterface;
import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class MaxOnes implements FitnessInterface<Integer> {

    @Override
    public double getFitness(MutableList<Integer> individual) {
        return -1.0*individual.sumOfDouble(anObject -> anObject);
    }

}
