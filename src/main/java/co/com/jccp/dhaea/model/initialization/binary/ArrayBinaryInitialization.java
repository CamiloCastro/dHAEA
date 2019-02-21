package co.com.jccp.dhaea.model.initialization.binary;

import co.com.jccp.dhaea.model.interfaces.InitializationInterface;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class ArrayBinaryInitialization implements InitializationInterface<Integer> {
    @Override
    public MutableList<Integer> generateIndividual(int dimensions, Integer[][] limits) {
        Random rnd = new Random();

        FastList<Integer> list = new FastList<>();
        for (int i = 0; i < dimensions; i++) {
            list.add(rnd.nextInt(2));
        }

        return list;
    }
}
