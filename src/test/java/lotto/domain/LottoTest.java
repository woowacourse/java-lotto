package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @Test
    void getLotto() {
        Lotto lotto = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(3),
                Number.of(4),
                Number.of(5),
                Number.of(6)
        ));
        assertThat(lotto.getLotto()).isEqualTo("1, 2, 3, 4, 5, 6");
    }

    @Test
    void contains() {
        Lotto lotto = new Lotto(Arrays.asList(
                Number.of(1),
                Number.of(2),
                Number.of(3),
                Number.of(4),
                Number.of(5),
                Number.of(6)
        ));
        assertTrue(lotto.contains(Number.of(1)));
    }
}