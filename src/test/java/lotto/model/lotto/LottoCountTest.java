package lotto.model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoCountTest {

    @Test
    @DisplayName("투입 금액이 천원 단위 경우")
    void validateThousandUnitInputMoney() {
        LottoCount lottoCount = new LottoCount("10000", 1);

        assertThat(lottoCount.getCount()).isEqualTo(9);
    }

    @Test
    @DisplayName("로또 생성 중단")
    void checkLottoCountIsZero() {
        LottoCount lottoCount = new LottoCount("2000", 1);
        lottoCount.makeLotto();

        assertThat(lottoCount.isZero()).isTrue();
    }

    @Test
    @DisplayName("로또 생성 횟수 차감")
    void checkLottoCountReduce() {
        LottoCount lottoCount = new LottoCount("100000", 0);
        lottoCount.makeLotto();

        assertThat(lottoCount.getCount()).isEqualTo(99);
    }
}
