package lotto.domain.rating;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CounterTest {
    @Test
    void name() {
        Counter expected = new Counter();
        expected = expected.plus();
        Counter counter = new Counter();
        Counter actual = counter.plus();
        assertThat(actual).isEqualTo(expected);
    }
}
