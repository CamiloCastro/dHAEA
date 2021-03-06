package co.com.jccp.dhaea.sequential;

import co.com.jccp.dhaea.model.BasicHaeaIndividual;
import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
@Accessors(chain = true)
public class HaeaSequential<T> {

    private int generations;

    private int numIndividuals;

    private int dimensions;

    private InitializationInterface<T> initializationFunction;

    private MutableList<GOPInterface<T>> operators;

    private SelectionInterface<T> selectionFunction;

    private FitnessInterface<T> fitnessFunction;

    private PopulationHandlerInterface<T> populationHandlerFunction;

    private T[][] limits;

    public void apply()
    {


        BasicHaeaIndividual<T> basicHaeaIndividual = new BasicHaeaIndividual<T>()
                .setDimensions(dimensions)
                .setFitnessFunction(fitnessFunction)
                .setPopulationHandlerFunction(populationHandlerFunction)
                .setInitializationFunction(initializationFunction)
                .setLimits(limits)
                .setOperators(operators)
                .setSelectionFunction(selectionFunction);

        for (int i = 0; i <= generations; i++) {
            MutableList<Individual<T>> population = populationHandlerFunction.getPopulation();
            MutableList<Individual<T>> newPop = population.collectWith((each, gen) -> basicHaeaIndividual.apply(gen, each), i);


//            for (int j = 0; j < population.size(); j++) {
//                Individual<T> best = basicHaeaIndividual.apply(i, population.get(j));
//            }
            populationHandlerFunction.updateAll(newPop, i);
        }

    }




}
