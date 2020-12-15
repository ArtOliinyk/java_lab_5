import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.inject.Singleton;
import static org.assertj.core.api.Assertions.assertThat;

class InterfaceTest {

    interface A { }

    interface B { }

    public static class ExampleOne implements A { }

    public static class ExampleTwo implements A, B { }

    private DI di;

    @BeforeEach
    void setup() {
        di = new DI();
    }

    @Test
    void succesfulWithBinding() {
        di.bindInterface(A.class, ExampleOne.class);
        final A instance = di.getInstance(A.class);
        assertThat(instance).isNotNull().isInstanceOf(ExampleOne.class);
    }

    @Test
    void succesfulLastBindingIsUsed() {
        di.bindInterface(A.class, ExampleOne.class);
        di.bindInterface(A.class, ExampleTwo.class);
        final A instance = di.getInstance(A.class);
        assertThat(instance).isNotNull().isInstanceOf(ExampleTwo.class);
    }

    @Singleton interface WannabeSingleton { }

    public static class NonSingleton implements WannabeSingleton { }

    @Test
    void failedInterfacesCantBeMarkedAsSingleton() {
        di.bindInterface(WannabeSingleton.class, NonSingleton.class);
        final WannabeSingleton instanceOne = di.getInstance(WannabeSingleton.class);
        final WannabeSingleton instanceTwo = di.getInstance(WannabeSingleton.class);
        assertThat(instanceOne).isNotSameAs(instanceTwo);
    }
}