package domain;

import static common.TestUtils.createNewLotto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    void lotto_passesOnSizeOfSix() {
        Lotto lotto = createNewLotto(1, 2, 3, 4, 5, 6);
        assertThat(lotto.getChosenNumbers().size()).isEqualTo(6);
    }

    @Test
    void lotto_failOnSizeNotOfSix() {
        assertThatThrownBy(() -> createNewLotto(1, 2, 3, 4, 5, 6, 7));
    }

    @Test
    void lotto_isSortedAsc() {
        Lotto lotto = createNewLotto(6, 5, 4, 3, 2, 1);
        assertThat(lotto.getChosenNumbers()).isSorted();
    }
}
