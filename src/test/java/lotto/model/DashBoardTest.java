package lotto.model;

import static lotto.LottoConstants.Price.LOTTO_PRICE_UNIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("대시보드(당첨 결과 기록판) 테스트")
class DashBoardTest {

    private DashBoard dashBoard;

    @BeforeEach
    void setUp() {
        dashBoard = new DashBoard();
    }

    @DisplayName("정상적인 경우")
    @Nested
    class SuccessCase {

        @DisplayName("당첨 결과를 기록하고 순위별 당첨 횟수를 확인할 수 있다.")
        @Test
        void recordResult() {
            assertThat(dashBoard.getWinningCount(Rank.FIRST))
                    .isEqualTo(0);

            dashBoard.recordResult(Rank.FIRST);
            assertThat(dashBoard.getWinningCount(Rank.FIRST))
                    .isEqualTo(1);
        }

        @DisplayName("수익률을 계산할 수 있다.")
        @Test
        void calculateWinningRate() {
            assertThat(dashBoard.calculateWinningRate(1_000))
                    .isEqualTo(0.0f);

            dashBoard.recordResult(Rank.FIFTH);
            assertThat(dashBoard.calculateWinningRate(1_000))
                    .isEqualTo(5.0f);
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("당첨 결과가 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenRankIsNull() {
            assertThatThrownBy(() -> dashBoard.recordResult(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("당첨 결과는 null이 될 수 없습니다.");
        }

        @DisplayName("수익률을 계산할 때 구입 금액이 0원인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenPurchaseAmountIsZero() {
            assertThatThrownBy(() -> dashBoard.calculateWinningRate(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("수익률을 계산할 때, 구입 금액은 최소 %,d원 이상이어야 합니다.".formatted(LOTTO_PRICE_UNIT));
        }

        @DisplayName("수익률을 계산할 때 구입 금액 단위가 일치하지 않을 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {999, 1_001})
        void shouldThrowException_WhenInvalidUnit(int invalidAmount) {
            assertThatThrownBy(() -> dashBoard.calculateWinningRate(invalidAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("수익률을 계산할 때, 구입 금액은 %,d원 단위만 가능합니다.".formatted(LOTTO_PRICE_UNIT));
        }
    }
}
