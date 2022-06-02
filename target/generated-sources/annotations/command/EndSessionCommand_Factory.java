package command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class EndSessionCommand_Factory implements Factory<EndSessionCommand> {
  @Override
  public EndSessionCommand get() {
    return newInstance();
  }

  public static EndSessionCommand_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EndSessionCommand newInstance() {
    return new EndSessionCommand();
  }

  private static final class InstanceHolder {
    private static final EndSessionCommand_Factory INSTANCE = new EndSessionCommand_Factory();
  }
}
