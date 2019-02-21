package co.com.jccp.dhaea.model.selections;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.SelectionInterface;
import lombok.Data;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Random;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
public class TournamentSelection<T> implements SelectionInterface<T> {

    private int groupedSelection = 4;

    @Override
    public MutableList<Individual<T>> applySelection(MutableList<Individual<T>> popToBeSelected, int selectionsNumber) {

        Random rnd = new Random();
        MutableList<Individual<T>> newPopList = popToBeSelected.clone().shuffleThis();
        MutableList<Individual<T>> offspring = new FastList<>();

        for (int i = 0; i < selectionsNumber; i++) {
            int pos = rnd.nextInt(newPopList.size());
            Individual<T> best = newPopList.get(pos);
            for (int j = 1; j < groupedSelection; j++) {
                int k = pos + j;
                k = k >= newPopList.size() ? k - newPopList.size() : k;
                if(best.compareTo(newPopList.get(k)) > 0)
                    best = newPopList.get(k);
            }
            offspring.add(best);
        }

        return offspring;

    }
}
