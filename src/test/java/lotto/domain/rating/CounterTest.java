package lotto.domain.rating;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CounterTest {

    @Test
    void name() {
        Counter expected = new Counter(1);
        Counter counter = new Counter(0);
        Counter actual = counter.plus();
        assertThat(actual).isEqualTo(expected);
    }
}
