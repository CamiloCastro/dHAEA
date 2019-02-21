package co.com.jccp.dhaea.model.initialization.real;

import co.com.jccp.dhaea.model.interfaces.InitializationInterface;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class RealInitialization implements InitializationInterface<Double> {

    @Override
    public MutableList<Double> generateIndividual(int dimensions, Double[][] limits) {

        Random rnd = new Random();
        FastList<Double> list = new FastList<>();
        for (int i = 0; i < dimensions; i++) {
            list.add((limits[i][1] - limits[i][0]) * rnd.nextDouble() + limits[i][0]);
        }
        return list;

    }
}
