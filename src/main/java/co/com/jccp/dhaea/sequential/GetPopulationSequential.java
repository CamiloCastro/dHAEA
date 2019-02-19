package co.com.jccp.dhaea.sequential;

import co.com.jccp.dhaea.model.Individual;
import co.com.jccp.dhaea.model.interfaces.GetPopulationInterface;
import lombok.Data;
import org.eclipse.collections.api.list.MutableList;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
public class GetPopulationSequential<T> implements GetPopulationInterface<T> {

    MutableList<Individual<T>> population;

    public GetPopulationSequential()
    {

    }

    @Override
    public MutableList<Individual<T>> getPopulation() {
        return population;
    }
}
