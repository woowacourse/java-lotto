package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMachineTest {

    @Test
    void 로또를_구매하면_금액에_알맞은_티켓_수를_반환한다() {
        final int price = 5000;
        LottoMachine lottoMachine = new LottoMachine(price);

        assertThat(lottoMachine.getTicketAmount())
                .isEqualTo(5);
    }

    @ParameterizedTest(name = "구입 금액 : {0}")
    @ValueSource(ints = {100, 200, 40500, 56070})
    void 구입_금액이_올바르지_않으면_예외가_발생한다(int price) {
        assertThatThrownBy(() -> new LottoMachine(price))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액과_당첨금액으로_수익률을_계산한다() {
        final int price = 5000;
        final int winningPrice = 5000;
        LottoMachine lottoMachine = new LottoMachine(price);

        assertThat(lottoMachine.calculateReturnOfInvestment(winningPrice))
                .isEqualTo(1.00);
    }
}