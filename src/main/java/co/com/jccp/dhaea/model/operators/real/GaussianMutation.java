package co.com.jccp.dhaea.model.operators.real;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.GOPInterface;
import lombok.Data;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
public class GaussianMutation implements GOPInterface<Double> {

    private double sigma;

    public GaussianMutation(double sigma)
    {
        this.sigma = sigma;
    }


    @Override
    public int getParentsNumber() {
        return 1;
    }

    @Override
    public MutableList<Individual<Double>> applyOperator(MutableList<Individual<Double>> parents, Double[][] limits) {
        Random rnd = new Random();
        Individual<Double> p = parents.get(0);

        FastList<Individual<Double>> offspring = new FastList<>();
        offspring.add(new Individual<Double>().setData(p.getData().collectWithIndex((value, i) -> {
            double g = rnd.nextGaussian();
            double off1Data = sigma*g + value;
            off1Data = off1Data > limits[i][1] ? limits[i][1] : (off1Data < limits[i][0] ? limits[i][0] : off1Data);
            return off1Data;
        })));
        return offspring;



    }
}
