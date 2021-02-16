package lotto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LottoTest {
    @Test
    void 숫자가_6개인지_확인() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getLotto().size()).isEqualTo(6);
    }
}
