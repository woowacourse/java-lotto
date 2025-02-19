package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.LottoFixtures;
import lotto.rule.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            assertThat(dashBoard.getWinningLottoCountForRank(Rank.FIRST))
                    .isEqualTo(0);

            dashBoard.incrementWinningCount(Rank.FIRST);
            assertThat(dashBoard.getWinningLottoCountForRank(Rank.FIRST))
                    .isEqualTo(1);
        }

        @DisplayName("수익률을 계산할 수 있다.")
        @Test
        void calculateWinningRate() {
            dashBoard.incrementWinningCount(Rank.NO_PRIZE);
            assertThat(dashBoard.calculateWinningRate())
                    .isEqualTo(0.0f);

            dashBoard.incrementWinningCount(Rank.FIFTH);
            assertThat(dashBoard.calculateWinningRate())
                    .isEqualTo(2.5f);
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("결과를 기록할 때, 당첨 번호가 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenWinningLottoIsNull() {
            assertThatThrownBy(() -> dashBoard.recordWinningResults(null, LottoFixtures.lottoTicketOneToSix))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("당첨 번호는 null이 될 수 없습니다.");
        }

        @DisplayName("결과를 기록할 때, 로또 티켓이 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenLottoTicketIsNull() {
            assertThatThrownBy(() -> dashBoard.recordWinningResults(LottoFixtures.winningLottoOneToSix, null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("로또 티켓은 null이 될 수 없습니다.");
        }

        @DisplayName("당첨 결과가 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenRankIsNull() {
            assertThatThrownBy(() -> dashBoard.incrementWinningCount(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("당첨 결과는 null이 될 수 없습니다.");
        }

        @DisplayName("수익률을 계산할 때, 아무 로또도 구입하지 않은 경우 예외가 발생한다. (0으로 나눌 수 없음)")
        @Test
        void shouldThrowException_WhenPurchaseAmountIsZero() {
            assertThatThrownBy(() -> dashBoard.calculateWinningRate())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또를 구입한 내역이 없어서 수익률을 계산할 수 없습니다.");
        }
    }
}
