package co.com.jccp.dhaea.model;

import co.com.jccp.dhaea.model.interfaces.*;
import co.com.jccp.dhaea.model.selections.RouletteSelection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BasicHaeaIndividual<T> {

    private int dimensions;

    private InitializationInterface<T> initializationFunction;

    private MutableList<GOPInterface<T>> operators;

    private SelectionInterface<T> selectionFunction;

    private FitnessInterface<T> fitnessFunction;

    private PopulationHandlerInterface<T> populationHandlerFunction;

    private T[][] limits;


    public Individual<T> apply(int iteration, Individual<T> beforeIndividual)
    {
        if(iteration == 0)
        {
            MutableList<T> data = initializationFunction.generateIndividual(dimensions, limits);
            MutableDoubleList rates = new DoubleArrayList(operators.size());
            for (int i = 0; i < operators.size(); i++) {
                rates.add(1.0/operators.size());
            }
            double fitness = fitnessFunction.getFitness(data);

            return new Individual<T>().setData(data).setFitness(fitness).setRates(rates);
        }

        beforeIndividual = beforeIndividual.clone();

        RouletteSelection us = new RouletteSelection();

        int gopPosition = us.getPositions(beforeIndividual.getRates(), 1).get(0);
        GOPInterface<T> operator = operators.get(gopPosition);

        MutableList<Individual<T>> parents = new FastList<>(operator.getParentsNumber());

        MutableList<Individual<T>> population = populationHandlerFunction.getPopulation();

        if(operator.getParentsNumber() > 1)
            parents = selectionFunction.applySelection(population, operator.getParentsNumber() - 1);

        parents.add(beforeIndividual);

        MutableList<Individual<T>> offspring = operator.applyOperator(parents, limits);

        offspring = offspring.collect(each -> each.setFitness(fitnessFunction.getFitness(each.getData())));

        Individual<T> bestOffspring = offspring.min();

        if(bestOffspring.compareTo(beforeIndividual) == 0)
        {
            bestOffspring.setRates(modifyRates(-1, beforeIndividual.getRates(), gopPosition));
            return bestOffspring;
        }
        else if(bestOffspring.compareTo(beforeIndividual) < 1 )
        {
            bestOffspring.setRates(modifyRates(1, beforeIndividual.getRates(), gopPosition));
            return bestOffspring;
        }
        else
        {
            beforeIndividual.setRates(modifyRates(-1, beforeIndividual.getRates(), gopPosition));
            return beforeIndividual;
        }

    }

    public MutableDoubleList modifyRates(int reward, MutableDoubleList rates, int pos)
    {
        Random rnd = new Random();
        double delta = 1.0 + rnd.nextDouble()*reward;
        rates.set(pos, rates.get(pos)*delta);

        double sum = rates.sum();
        return rates.collectDouble(doubleParameter -> doubleParameter/sum, new DoubleArrayList());
    }







}
