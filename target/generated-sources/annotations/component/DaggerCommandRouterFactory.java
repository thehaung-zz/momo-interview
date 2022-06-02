package component;

import com.google.common.collect.ImmutableMap;
import command.Command;
import command.CommandRouter;
import command.EndSessionCommand;
import command.InsertMoneyCommand;
import command.SelectProductCommand;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import datasource.InMemoryDatabaseImpl;
import datasource.InMemoryDatabaseImpl_Factory;
import java.util.Map;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerCommandRouterFactory {
  private DaggerCommandRouterFactory() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static CommandRouterFactory create() {
    return new Builder().build();
  }

  public static final class Builder {
    private Builder() {
    }

    public CommandRouterFactory build() {
      return new CommandRouterFactoryImpl();
    }
  }

  private static final class CommandRouterFactoryImpl implements CommandRouterFactory {
    private final CommandRouterFactoryImpl commandRouterFactoryImpl = this;

    private Provider<InMemoryDatabaseImpl> inMemoryDatabaseImplProvider;

    private CommandRouterFactoryImpl() {

      initialize();

    }

    private SelectProductCommand selectProductCommand() {
      return new SelectProductCommand(inMemoryDatabaseImplProvider.get());
    }

    private Map<String, Command> mapOfStringAndCommand() {
      return ImmutableMap.<String, Command>of("insert_money_command", new InsertMoneyCommand(), "select_product_command", selectProductCommand(), "end_session_command", new EndSessionCommand());
    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.inMemoryDatabaseImplProvider = DoubleCheck.provider(InMemoryDatabaseImpl_Factory.create());
    }

    @Override
    public CommandRouter router() {
      return new CommandRouter(mapOfStringAndCommand());
    }

    @Override
    public InMemoryDatabaseImpl database() {
      return inMemoryDatabaseImplProvider.get();
    }
  }
}
