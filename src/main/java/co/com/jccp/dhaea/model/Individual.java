package co.com.jccp.dhaea.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
@Accessors(chain = true)
public class Individual<T> implements Comparable<Individual<T>> {

    private MutableList<T> data;

    private MutableDoubleList rates;

    private double fitness;

    @Override
    public int compareTo(Individual<T> o) {
        if(fitness ==  o.getFitness())
            return 0;
        else if(fitness < o.getFitness())
            return -1;
        return 1;
    }

    @Override
    public Individual<T> clone()
    {

        return new Individual<T>()
                .setFitness(this.fitness)
                .setRates(rates.collectDouble(value -> value, new DoubleArrayList()))
                .setData(data.collect(each -> each));

    }
}
