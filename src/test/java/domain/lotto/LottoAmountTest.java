package domain.lotto;

import lotto.domain.lotto.LottoAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LottoAmountTest {
    @Test
    @DisplayName("로또 구매 수량은 숫자만 들어와야 한다.")
    public void 로또_구매_수량은_숫자만_들어와야_한다() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            LottoAmount lottoAmount = new LottoAmount("A");
        });
    }



    @Test
    @DisplayName("로또 구매 수량은 음수 여서는 안된다.")
    public void 로또_구매_수량은_음수_여서는_안된다() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            LottoAmount lottoAmount = new LottoAmount("-1");
        });
    }
}
