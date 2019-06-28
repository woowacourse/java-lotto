package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6), new KoreaLottoRule());
    }

    @Test
    void getMatchCount() {
        Lotto winLotto = new Lotto(Arrays.asList(1, 3, 5, 7, 9, 11), new KoreaLottoRule());
        assertThat(lotto.getMatchCount(winLotto)).isEqualTo(3);
    }

    @Test
    void hasNumber() {
        assertThat(lotto.hasNumber(3)).isTrue();
        assertThat(lotto.hasNumber(7)).isFalse();
    }
}