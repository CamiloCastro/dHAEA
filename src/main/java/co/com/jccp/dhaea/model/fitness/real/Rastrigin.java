package co.com.jccp.dhaea.model.fitness.real;

import co.com.jccp.dhaea.model.interfaces.FitnessInterface;
import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class Rastrigin implements FitnessInterface<Double> {
    @Override
    public double getFitness(MutableList<Double> individual) {

        int d = individual.size();
        return 10.0*d + individual.sumOfDouble(anObject -> (anObject*anObject) - 10.0*Math.cos(2*Math.PI*anObject));

    }
}
