package co.com.jccp.dhaea.model.selections;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.SelectionInterface;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class UniformSelection<T> implements SelectionInterface<T> {

    @Override
    public MutableList<Individual<T>> applySelection(MutableList<Individual<T>> popToBeSelected, int selectionsNumber) {

        double sum = popToBeSelected.sumOfDouble(Individual::getFitness);

        MutableDoubleList normalizedList = popToBeSelected.collectDouble(each -> each.getFitness()/sum);

        return getPositions(normalizedList, selectionsNumber).collect(popToBeSelected::get);
    }

    public IntArrayList getPositions(MutableDoubleList normalizedList, int selectionsNumber)
    {
        Random rnd = new Random();
        IntArrayList positions = new IntArrayList();

        for (int i = 0; i < selectionsNumber; i++) {
            double d = rnd.nextDouble();
            double sum = 0.0;
            for (int j = 0; j < normalizedList.size(); j++) {
                sum += normalizedList.get(j);
                if (d < sum)
                {
                    positions.add(j);
                    break;
                }
            }
        }
        return positions;

    }

}
