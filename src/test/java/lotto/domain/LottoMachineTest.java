package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @DisplayName("로또 기계에 넣은 돈이 부족하면 빈 로또를 반환한다.")
    @Test
    void 돈_부족_테스트() {
        // given, when
        LottoMachine lottoMachine = LottoMachine
            .insertMoney(Money.priceOf(Lotto.LOTTO_PRICE - 100));

        // then
        assertThat(lottoMachine.buyAutomaticLottos()).isSameAs(Lottos.EMPTY_LOTTOS);
    }

}