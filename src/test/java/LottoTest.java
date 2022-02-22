import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
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
}
