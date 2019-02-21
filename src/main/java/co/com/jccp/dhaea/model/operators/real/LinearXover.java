package co.com.jccp.dhaea.model.operators.real;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.GOPInterface;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class LinearXover implements GOPInterface<Double> {
    @Override
    public int getParentsNumber() {
        return 2;
    }

    @Override
    public MutableList<Individual<Double>> applyOperator(MutableList<Individual<Double>> parents, Double[][] limits) {
        Random rnd = new Random();
        double alpha = rnd.nextDouble();
        double alpha_1 = rnd.nextDouble();
        double neg_alpha = 1.0 - alpha;
        double neg_alpha_1 = 1.0 - alpha_1;

        Individual<Double> p1 = parents.get(0);
        Individual<Double> p2 = parents.get(1);
        int d = p1.getData().size();
        Individual<Double> off1 = new Individual<Double>().setData(new FastList<>());
        Individual<Double> off2 = new Individual<Double>().setData(new FastList<>());

        for (int i = 0; i < d; i++) {
            double off1Data = p1.getData().get(i) * alpha + p2.getData().get(i) * neg_alpha;
            double off2Data = p1.getData().get(i) * alpha_1 + p2.getData().get(i) * neg_alpha_1;

            off1Data = off1Data > limits[i][1] ? limits[i][1] : (off1Data < limits[i][0] ? limits[i][0] : off1Data);
            off2Data = off2Data > limits[i][1] ? limits[i][1] : (off2Data < limits[i][0] ? limits[i][0] : off2Data);

            off1.getData().add(off1Data);
            off2.getData().add(off2Data);
        }

        FastList<Individual<Double>> offspring = new FastList<>();
        offspring.add(off1);
        offspring.add(off2);
        return offspring;
    }
}
