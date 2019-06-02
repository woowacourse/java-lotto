package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.utils.InputParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @Test
    void getLotto() {
        Lotto lotto = new Lotto(InputParser.parseLotto("1, 2, 3, 4, 5, 6"));
        assertThat(lotto.getLotto()).isEqualTo("1, 2, 3, 4, 5, 6");
    }

    @Test
    void contains() {
        Lotto lotto = new Lotto(InputParser.parseLotto("1, 2, 3, 4, 5, 6"));
        assertTrue(lotto.contains(Number.of(1)));
    }
}