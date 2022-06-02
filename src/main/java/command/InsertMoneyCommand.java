package command;

import utils.CommandConstant;

import javax.inject.Inject;
import java.util.List;

/**
 * @author : Hau Nguyen
 * @created : 6/2/22
 **/

public class InsertMoneyCommand implements Command {

    @Inject
    public InsertMoneyCommand() {}

    @Override
    public String key() {
        return CommandConstant.INSERT_MONEY_COMMAND_KEY;
    }

    @Override
    public Status handleInput(List<String> input) {
        if (input.isEmpty()) {
            return Status.INVALID;
        }
        System.out.println("InsertMoneyCommand input: " + input);
        return Status.HANDLED;
    }
}
