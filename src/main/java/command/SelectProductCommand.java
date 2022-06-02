package command;

import datasource.InMemoryDatabaseImpl;
import utils.CommandConstant;

import javax.inject.Inject;
import java.util.List;

/**
 * @author : Hau Nguyen
 * @created : 6/2/22
 **/

public class SelectProductCommand implements Command {

    private InMemoryDatabaseImpl inMemoryDatabase;

    @Inject
    public SelectProductCommand(InMemoryDatabaseImpl inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public String key() {
        return CommandConstant.SELECT_PRODUCT_COMMAND_KEY;
    }

    @Override
    public Status handleInput(List<String> input) {
        if (input.isEmpty()) {
            return Status.INVALID;
        }
        System.out.println("SelectProductCommand input: " + input);
        inMemoryDatabase.endSession();
        return Status.HANDLED;
    }
}
