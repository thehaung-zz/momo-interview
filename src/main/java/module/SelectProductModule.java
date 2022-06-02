package module;

import command.Command;
import command.SelectProductCommand;
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
public abstract class SelectProductModule {

    @Binds
    @IntoMap
    @StringKey(CommandConstant.SELECT_PRODUCT_COMMAND_KEY)
    public abstract Command selectProductCommand(SelectProductCommand selectProductCommand);
}
