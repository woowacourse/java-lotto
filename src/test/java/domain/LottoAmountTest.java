package domain;

import Lotto.domain.LottoAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoAmountTest {
    @Test
    @DisplayName("입력으로부터 로또 장 수를 잘 반환해주는지 확인하는 테스트")
    void name() {
        LottoAmount lottoAmount = new LottoAmount("0");
        assertThat(lottoAmount.getLottoAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("입력이 잘못됐을 때 exception 확인")
    void wrongInput() {
        assertThatThrownBy(() -> new LottoAmount("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0장 이상");
    }
}
