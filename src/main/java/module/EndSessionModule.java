package module;

import command.Command;
import command.EndSessionCommand;
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
public abstract class EndSessionModule {

    @Binds
    @IntoMap
    @StringKey(CommandConstant.END_SESSION_COMMAND_KEY)
    public abstract Command endSessionCommand(EndSessionCommand endSessionCommand);
}
