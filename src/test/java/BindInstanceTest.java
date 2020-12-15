import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BindInstanceTest {

    private static class Example implements MyInterface { }

    interface MyInterface { }

    static abstract class AbstractExample { }

    private DI di;

    @BeforeEach
    void setup(){
        di = new DI();
    }

    @Test
    void succesfulInstanceBind() {
        Example example = new Example();
        di.bindInstance(Example.class, example);
        final Example instance = di.getInstance(Example.class);
        assertThat(instance).isSameAs(example);
    }

    @Test
    void succesfulInterface() {
        Example example = new Example();
        di.bindInstance(MyInterface.class, example);
        final MyInterface instance = di.getInstance(MyInterface.class);
        assertThat(instance).isSameAs(example);
    }

    @Test
    void succesfulAbstractClass() {
        AbstractExample example = new AbstractExample() {};
        di.bindInstance(AbstractExample.class, example);
        final AbstractExample instance = di.getInstance(AbstractExample.class);
        assertThat(instance).isSameAs(example);
    }
}
