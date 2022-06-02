package component;

import command.CommandRouter;
import dagger.Component;
import datasource.InMemoryDatabaseImpl;
import module.EndSessionModule;
import module.InsertMoneyModule;
import module.SelectProductModule;

import javax.inject.Singleton;

/**
 * @author : Hau Nguyen
 * @created : 6/2/22
 **/

@Singleton
@Component(modules = {
        InsertMoneyModule.class,
        SelectProductModule.class,
        EndSessionModule.class,
})
public interface CommandRouterFactory {
    CommandRouter router();

    InMemoryDatabaseImpl database();
}

