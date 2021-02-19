package lotto.domain;

import lotto.domain.lotto.LottoCount;
import lotto.exception.LowBalanceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoCountTest {

    @DisplayName("자동 생성 테스트")
    @Test
    void createAuto() {
        LottoCount lottoCount = new LottoCount(new Payment(10000), 10);

        assertThat(lottoCount.auto()).isEqualTo(0);
        assertThat(lottoCount.manual()).isEqualTo(10);
    }

    @DisplayName("수동 생성 테스트")
    @Test
    void createManual() {
        LottoCount lottoCount = new LottoCount(new Payment(10000), 0);

        assertThat(lottoCount.auto()).isEqualTo(10);
        assertThat(lottoCount.manual()).isEqualTo(0);
    }

    @DisplayName("자동, 수동 생성 테스트")
    @Test
    void createAll() {
        LottoCount lottoCount = new LottoCount(new Payment(10000), 5);

        assertThat(lottoCount.auto()).isEqualTo(5);
        assertThat(lottoCount.manual()).isEqualTo(5);
    }

    @DisplayName("구매금액 대비 구매개수 초과 예외트 처리 테스트")
    @Test
    void lowBalance() {
        assertThatThrownBy(() -> {
            new LottoCount(new Payment(10000), 11);
        }).isInstanceOf(LowBalanceException.class);
    }
}