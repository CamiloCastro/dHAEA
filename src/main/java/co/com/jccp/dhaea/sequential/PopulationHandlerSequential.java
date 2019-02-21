package co.com.jccp.dhaea.sequential;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.IndividualResults;
import co.com.jccp.dhaea.model.interfaces.PopulationHandlerInterface;
import lombok.Data;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.io.FileWriter;
import java.io.OutputStream;
import java.util.function.Consumer;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
public class PopulationHandlerSequential<T> implements PopulationHandlerInterface<T> {

    MutableList<Individual<T>> population;

    MutableList<IndividualResults<T>> individualResults;

    int totalIterations;

    public PopulationHandlerSequential(int numIndividuals, int totalIterations)
    {
        population = new FastList<>(numIndividuals);
        individualResults = new FastList<>(numIndividuals);
        for (int i = 0; i < numIndividuals; i++) {
            population.add(null);
            individualResults.add(new IndividualResults<T>()
                    .setDone(false)
                    .setBest(null)
                    .setIndividualEachIteration(new FastList<>())
                    .setNumberIndividual(i));
        }
        this.totalIterations = totalIterations;
    }

    @Override
    public MutableList<Individual<T>> getPopulation() {
        return population.clone();
    }

    @Override
    public void updateResults(int iteration, int individual, Individual<T> best) {

        IndividualResults<T> result = individualResults.get(individual);
        result.setBest(best);
        result.getIndividualEachIteration().add(best);
        result.setDone(iteration == totalIterations);
        individualResults.set(individual, result);

    }

    @Override
    public boolean isAllDone() {
        return individualResults.count(IndividualResults::isDone) == population.size();
    }

    @Override
    public void updatePopulation() {
        population = individualResults.collect(IndividualResults::getBest);
    }

    @Override
    public void updateAll(MutableList<Individual<T>> newPop, int iteration) {
        newPop.forEachWithIndex((val, i) -> updateResults(iteration, i, val));
        updatePopulation();
    }

    @Override
    public void printFinalResult()
    {
        System.out.println(population.makeString("\n"));
    }

    @Override
    public void printBehaviourOfIndividual(int individual)
    {
        System.out.println(individualResults.get(individual).getIndividualEachIteration().makeString("\n"));
    }

    @Override
    public Individual<T> getBestOfIteration(int i) {
        MutableList<Individual<T>> individuals = individualResults.collect(IndividualResults::getIndividualEachIteration).collect(each -> each.get(i));
        return individuals.min();
    }

    @Override
    public void printBestOfEachIteration()
    {
        for (int i = 0; i < totalIterations; i++) {
            System.out.println(getBestOfIteration(i));
        }
    }


}
