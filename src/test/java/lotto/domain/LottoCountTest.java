package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoCountTest {
    @Test
    void 정상적이고_평범한_생성_시도() {
        assertThat(new LottoCount(new LottoMoney(1300), 1))
            .isInstanceOf(LottoCount.class);
    }

    @Test
    void LottoCount_천원_미만_입력시_예외처리() {
        assertThatThrownBy(() -> {
            new LottoCount(new LottoMoney(999), 0);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Money 는 로또 한 장의 금액 이상이어야 합니다.");
    }
}
