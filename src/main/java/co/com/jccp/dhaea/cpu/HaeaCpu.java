package co.com.jccp.dhaea.cpu;

import co.com.jccp.dhaea.model.BasicHaeaIndividual;
import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.parallel.ParallelIterate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
@Accessors(chain = true)
public class HaeaCpu<T> {

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
            MutableList<Individual<T>> newPop = executeParallel(population, basicHaeaIndividual, i);
            populationHandlerFunction.updateAll(newPop, i);
        }

    }

    private MutableList<Individual<T>> executeParallel(MutableList<Individual<T>> population, BasicHaeaIndividual<T> basicHaeaIndividual, int iteration)
    {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        int batchSize = population.size();

        return ParallelIterate.collect(population, each -> basicHaeaIndividual.apply(iteration, each), new FastList<>(), batchSize, threadPool, false);
    }



}
