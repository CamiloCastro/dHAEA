package co.com.jccp.dhaea.haea;

import co.com.jccp.dhaea.model.fitness.binary.MaxOnes;
import co.com.jccp.dhaea.model.fitness.real.Rastrigin;
import co.com.jccp.dhaea.model.initialization.binary.ArrayBinaryInitialization;
import co.com.jccp.dhaea.model.initialization.real.RealInitialization;
import co.com.jccp.dhaea.model.interfaces.*;
import co.com.jccp.dhaea.model.operators.binary.BinaryRandomMutation;
import co.com.jccp.dhaea.model.operators.binary.BinarySinglePointXover;
import co.com.jccp.dhaea.model.operators.real.GaussianMutation;
import co.com.jccp.dhaea.model.operators.real.LinearXover;
import co.com.jccp.dhaea.model.operators.real.PowerLawMutation;
import co.com.jccp.dhaea.model.selections.RandomSelection;
import co.com.jccp.dhaea.model.selections.RouletteSelection;
import co.com.jccp.dhaea.model.selections.TournamentSelection;
import co.com.jccp.dhaea.sequential.HaeaSequential;
import co.com.jccp.dhaea.sequential.PopulationHandlerSequential;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.Test;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class SequentialTest {


    @Test
    public void binaryTest()
    {
        int ITERATIONS = 1000;
        int NUMBER_INDIVIDUALS = 10;
        int DIMENSIONS = 30;


        FitnessInterface<Integer> fitnessFunction = new MaxOnes();
        InitializationInterface<Integer> initializationFunction = new ArrayBinaryInitialization();
        FastList<GOPInterface<Integer>> operators = new FastList<>();
        operators.add(new BinarySinglePointXover());
        operators.add(new BinaryRandomMutation());
        SelectionInterface<Integer> selectionFunction = new TournamentSelection<>();
        PopulationHandlerInterface<Integer> populationHandlerFunction = new PopulationHandlerSequential<>(NUMBER_INDIVIDUALS, ITERATIONS);

        HaeaSequential<Integer> sequential = new HaeaSequential<>();

        sequential.setDimensions(DIMENSIONS)
                .setFitnessFunction(fitnessFunction)
                .setGenerations(ITERATIONS)
                .setInitializationFunction(initializationFunction)
                .setNumIndividuals(NUMBER_INDIVIDUALS)
                .setOperators(operators)
                .setSelectionFunction(selectionFunction)
                .setPopulationHandlerFunction(populationHandlerFunction)
                .setLimits(new Integer[0][0]);

        sequential.apply();

        populationHandlerFunction.printBestOfEachIteration();

//        populationHandlerFunction.printFinalResult();
//
//        populationHandlerFunction.printBehaviourOfIndividual(0);
    }

    @Test
    public void realTest()
    {
        int ITERATIONS = 10000;
        int NUMBER_INDIVIDUALS = 250;
        int DIMENSIONS = 30;


        FitnessInterface<Double> fitnessFunction = new Rastrigin();
        InitializationInterface<Double> initializationFunction = new RealInitialization();
        FastList<GOPInterface<Double>> operators = new FastList<>();
        operators.add(new PowerLawMutation(0.001));
        operators.add(new LinearXover());
        SelectionInterface<Double> selectionFunction = new TournamentSelection<>();
        PopulationHandlerInterface<Double> populationHandlerFunction = new PopulationHandlerSequential<>(NUMBER_INDIVIDUALS, ITERATIONS);
        HaeaSequential<Double> sequential = new HaeaSequential<>();

        Double[][] limits = new Double [DIMENSIONS][2];

        for (int i = 0; i < DIMENSIONS; i++) {
            limits[i][0] = -5.12;
            limits[i][1] = 5.12;
        }


        sequential.setDimensions(DIMENSIONS)
                .setFitnessFunction(fitnessFunction)
                .setGenerations(ITERATIONS)
                .setInitializationFunction(initializationFunction)
                .setNumIndividuals(NUMBER_INDIVIDUALS)
                .setOperators(operators)
                .setSelectionFunction(selectionFunction)
                .setPopulationHandlerFunction(populationHandlerFunction)
                .setLimits(limits);

        sequential.apply();

//        populationHandlerFunction.printBestOfEachIteration();
//
//        System.out.println("\n*********************************************\n");
//
        populationHandlerFunction.printFinalResult();

//        populationHandlerFunction.printBehaviourOfIndividual(0);
    }

}
