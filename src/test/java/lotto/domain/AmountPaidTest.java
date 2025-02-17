package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountPaidTest {

    @DisplayName("구매 금액이 음수면 예외를 발생한다.")
    @Test
    void 구매_금액이_음수면_예외를_발생한다() {
        assertThatThrownBy(() -> new AmountPaid(-15000)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 양수만 입력 해 주세요.");
    }

    @DisplayName("구매 금액이 0일 경우 예외를 발생한다.")
    @Test
    void 구매_금액이_0일_경우_예외를_발생한다() {
        assertThatThrownBy(() -> new AmountPaid(0)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 양수만 입력 해 주세요.");
    }

    @DisplayName("구매금액이 로또금액으로 나누어 떨어지지 않으면 예외를 발생한다.")
    @Test
    void 구매_금액은_로또금액으로_나누어_떨어지지_않으면_예외를_발생한다() {
        assertThatThrownBy(() -> new AmountPaid(15500)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 1000으로 나누어 떨어져야 합니다.");
    }

    @DisplayName("당첨금을_입력받아_이익률을 계산한다")
    @Test
    void 당첨금을_입력받아_이익률을_계산한다() {
        AmountPaid amountPaid = new AmountPaid(14000);

        assertThat(amountPaid.calculateProfitRate(5000)).isEqualTo("0.35");
    }

    @DisplayName("로또 구입 수량을 계산한다.")
    @Test
    void 로또_구입_수량을_계산한다() {
        AmountPaid amountPaid = new AmountPaid(14000);

        assertThat(amountPaid.getLottoQuantity()).isEqualTo(14);
    }
}
