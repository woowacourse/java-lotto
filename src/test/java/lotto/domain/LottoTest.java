package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @Test
    void getLotto() {
        Lotto lotto = new Lotto(Arrays.asList(
                new Number(1),
                new Number(2),
                new Number(3),
                new Number(4),
                new Number(5),
                new Number(6)
        ));
        assertThat(lotto.getLotto()).isEqualTo("1, 2, 3, 4, 5, 6");
    }

    @Test
    void contains() {
        Lotto lotto = new Lotto(Arrays.asList(
                new Number(1),
                new Number(2),
                new Number(3),
                new Number(4),
                new Number(5),
                new Number(6)
        ));
        assertTrue(lotto.contains(new Number(1)));
    }
}