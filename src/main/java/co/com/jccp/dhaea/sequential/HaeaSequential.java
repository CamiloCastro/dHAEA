package co.com.jccp.dhaea.sequential;

import co.com.jccp.dhaea.model.BasicHaeaIndividual;
import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.IndividualResults;
import co.com.jccp.dhaea.model.interfaces.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HaeaSequential<T> {

    private int generations;

    private int numIndividuals;

    private int dimensions;

    private InitializationInterface<T> initializationFunction;

    private GOPInterface<T>[] operators;

    private SelectionInterface<T> selectionFunction;

    private FitnessInterface<T> fitnessFunction;

    private UpdateResultsInterface<T> updateFunction;

    private GetPopulationInterface<T> getPopulationFunction;

    private T[][] limits;

    public void apply()
    {
        MutableList<Individual<T>> population = new FastList<>(numIndividuals);
        for (int i = 0; i < numIndividuals; i++) {
            population.add(null);
        }

        BasicHaeaIndividual<T> basicHaeaIndividual = new BasicHaeaIndividual<T>()
                .setDimensions(dimensions)
                .setFitnessFunction(fitnessFunction)
                .setGetPopulationFunction(getPopulationFunction)
                .setInitializationFunction(initializationFunction)
                .setLimits(limits)
                .setOperators(operators)
                .setSelectionFunction(selectionFunction)
                .setUpdateFunction(updateFunction);

        for (int i = 0; i <= generations; i++) {

            for (int j = 0; j < population.size(); j++) {

                Individual<T> best = basicHaeaIndividual.apply(i, population.get(j));


            }

        }

    }




}
