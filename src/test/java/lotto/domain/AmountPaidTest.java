package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountPaidTest {

    @DisplayName("구매 금액은 1000으로 나누어 떨어지지 않으면 예외를 발생한다.")
    @Test
    void 구매_금액은_1000으로_나누어_떨어지지_않으면_예외를_발생한다() {

        assertThatThrownBy(() -> new AmountPaid(15500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 1000으로 나누어 떨어져야 합니다.");
    }

    @DisplayName("구매 금액은 음수면 예외를 발생한다.")
    @Test
    void 구매_금액은_음수면_예외를_발생한다() {

        assertThatThrownBy(() -> new AmountPaid(-15000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음수는 입력할 수 없습니다.");
    }

    @DisplayName("이익률을 계산한다")
    @Test
    void 이익률을_계산한다() {
        AmountPaid amountPaid = new AmountPaid(14000);

        assertThat(amountPaid.calculateProfitRate(5000)).isEqualTo("0.35");
    }

    @DisplayName("로또 금액에 따른 로또 장수를 반환한다.")
    @Test
    void 로또_금액에_따른_로또_장수를_반환한다() {
        AmountPaid amountPaid = new AmountPaid(14000);

        assertThat(amountPaid.getLottoQuantity()).isEqualTo(14);
    }
}
