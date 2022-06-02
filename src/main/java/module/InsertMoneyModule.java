package module;

import command.Command;
import command.InsertMoneyCommand;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import utils.CommandConstant;

/**
 * @author : Hau Nguyen
 * @created : 6/2/22
 **/

@Module
public abstract class InsertMoneyModule {

    @Binds
    @IntoMap
    @StringKey(CommandConstant.INSERT_MONEY_COMMAND_KEY)
    public abstract Command insertMoneyModule(InsertMoneyCommand insertMoneyCommand);
}
