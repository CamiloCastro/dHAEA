package co.com.jccp.dhaea.model.operators.binary;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.GOPInterface;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class BinaryRandomMutation implements GOPInterface<Integer> {

    @Override
    public int getParentsNumber() {
        return 1;
    }

    @Override
    public MutableList<Individual<Integer>> applyOperator(MutableList<Individual<Integer>> parents, Integer[][] limits) {
        Random rnd = new Random();
        Individual<Integer> p = parents.get(0);
        int val = rnd.nextInt(p.getData().size());
        FastList<Individual<Integer>> offspring = new FastList<>();
        offspring.add(new Individual<Integer>().setData(p.getData().collectWithIndex((dim, i) -> val == i ? 1-dim:dim)));
        return offspring;



    }
}
