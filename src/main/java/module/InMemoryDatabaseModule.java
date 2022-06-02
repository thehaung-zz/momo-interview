package module;

import dagger.Binds;
import dagger.Module;
import datasource.InMemoryDatabase;

/**
 * @author : Hau Nguyen
 * @created : 6/2/22
 **/

@Module
public interface InMemoryDatabaseModule {

    @Binds
    InMemoryDatabase inMemoryDatabase(InMemoryDatabase inMemoryDatabaseImpl);
}
