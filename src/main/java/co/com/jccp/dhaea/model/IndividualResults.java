package co.com.jccp.dhaea.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eclipse.collections.api.list.MutableList;


/**
 * Created by: Juan Camilo Castro Pinto
 **/
@Data
@Accessors(chain = true)
public class IndividualResults<T> {

    private String uuid;

    private int numberIndividual;

    private MutableList<Individual<T>> individualEachIteration;

    private Individual<T> best;

    private boolean done;

}
