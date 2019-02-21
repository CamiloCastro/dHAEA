package co.com.jccp.dhaea.model.operators.binary;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.GOPInterface;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class BinarySinglePointXover implements GOPInterface<Integer> {
    @Override
    public int getParentsNumber() {
        return 2;
    }

    @Override
    public MutableList<Individual<Integer>> applyOperator(MutableList<Individual<Integer>> parents, Integer[][] limits) {
        Random rnd = new Random();
        Individual<Integer> p1 = parents.get(0);
        Individual<Integer> p2 = parents.get(1);
        int d = p1.getData().size();
        int xPoint = rnd.nextInt(d);

        Individual<Integer> off1 = new Individual<>();
        Individual<Integer> off2 = new Individual<>();



        MutableList<Integer> dataOff1 = new FastList<>(p1.getData().subList(0, xPoint));
        dataOff1.addAll(new FastList<>(p2.getData().subList(xPoint,d)));
        off1.setData(dataOff1);

        MutableList<Integer> dataOff2 = new FastList<>(p2.getData().subList(0, xPoint));
        dataOff2.addAll(new FastList<>(p1.getData().subList(xPoint,d)));
        off2.setData(dataOff2);

        FastList<Individual<Integer>> offspring = new FastList<>();
        offspring.add(off1);
        offspring.add(off2);
        return offspring;
    }
}
