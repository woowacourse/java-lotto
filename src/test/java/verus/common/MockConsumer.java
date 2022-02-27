package verus.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import verus.exception.InvalidFormatException;

public class MockConsumer extends Mock {

    private List<Object> values = new ArrayList<>();

    public void accept(Object o) {
        values.add(o);
        call();
    }

    public void throwInvalidFormatException(Object o, String message) {
        values.add(o);
        call();
        throw new InvalidFormatException(message);
    }

    public void throwRuntimeException(Object o, String message) {
        values.add(o);
        call();
        throw new RuntimeException(message);
    }

    public void verifyCalledOnce(Class<?> cls) {
        verifyCalledOnce();
        assertThat(cls).isAssignableFrom(values.get(0).getClass());
    }

    public void verifyCalledOnce(Object value) {
        verifyCalledOnce();
        assertThat(values.get(0)).isEqualTo(value);
    }

    public void verifyCalledTimes(int time, Class<?> cls) {
        verifyCalledTimes(time);
        for (Object value : values) {
            assertThat(cls).isAssignableFrom(value.getClass());
        }
    }

    public void verifyCalledTimes(int time, Object... values) {
        verifyCalledTimes(time);
        assertThat(this.values).containsExactlyInAnyOrder(values);
    }
}
