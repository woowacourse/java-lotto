package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    void numbers_hasSizeOfSix() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @Test
    void numbers_isSortedAsc() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getNumbers()).isSorted();
    }

    @Test
    void manualLotto_passesOnSizeOfSix() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @Test
    void manualLotto_failOnSizeNotOfSix() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1,2,3,4,5,6,7)));
    }

    @Test
    void manualLotto_isSorted() {
        Lotto lotto = new Lotto(Arrays.asList(6,5,4,3,2,1));
        assertThat(lotto.getNumbers()).isSorted();
    }
}
