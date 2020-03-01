package domain;

import Lotto.domain.LottoCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    @Test
    @DisplayName("입력으로부터 로또 장 수를 잘 반환해주는지 확인하는 테스트")
    void name() {
        LottoCount lottoCount = new LottoCount("0");
        assertThat(lottoCount.getLottoCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("입력이 잘못됐을 때 exception 확인")
    void wrongInput() {
        assertThatThrownBy(() -> new LottoCount("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0장 이상");
    }
}
