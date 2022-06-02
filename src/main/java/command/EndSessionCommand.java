package command;

import javax.inject.Inject;
import java.util.List;

/**
 * @author : Hau Nguyen
 * @created : 6/2/22
 **/

public class EndSessionCommand implements Command {

    @Inject
    public EndSessionCommand() {}

    @Override
    public String key() {
        return null;
    }

    @Override
    public Status handleInput(List<String> input) {
        if (input.isEmpty()) {
            return Status.INVALID;
        }
        System.out.println("EndSessionCommand input: " + input);
        return Status.HANDLED;
    }
}
