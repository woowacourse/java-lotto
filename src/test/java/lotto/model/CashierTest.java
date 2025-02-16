package lotto.model;

import static lotto.rule.LottoConstants.Price.LOTTO_PRICE_UNIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CashierTest {

    private final Cashier cashier = new Cashier(new LottoMachine(new TestNumberPicker()));

    @DisplayName("정상적인 경우")
    @Nested
    class SuccessCase {

        @DisplayName("구입 금액에 따라 로또를 구매할 수 있다.")
        @ParameterizedTest
        @ValueSource(ints = {1_000, 2_000, 3_000})
        void payForLotto(int purchaseAmount) {
            LottoTicket lottoTicket = cashier.payForLotto(purchaseAmount);
            int expectedCount = purchaseAmount / LOTTO_PRICE_UNIT;

            assertThat(lottoTicket.getLottoCount())
                    .isEqualTo(expectedCount);
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("로또 발급기가 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenLottoMachineIsNull() {
            assertThatThrownBy(() -> new Cashier(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("로또 발급기는 null이 될 수 없습니다.");
        }

        @DisplayName("구입 금액 단위가 일치하지 않을 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {999, 1_001})
        void shouldThrowException_WhenInvalidUnit(int invalidAmount) {
            assertThatThrownBy(() -> cashier.payForLotto(invalidAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또는 %,d원 단위로 구매할 수 있습니다.".formatted(LOTTO_PRICE_UNIT));
        }

        @DisplayName("구입 금액이 0원인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenZeroAmount() {
            assertThatThrownBy(() -> cashier.payForLotto(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또를 구매하려면 최소 %,d원 이상이어야 합니다.".formatted(LOTTO_PRICE_UNIT));
        }
    }
}
