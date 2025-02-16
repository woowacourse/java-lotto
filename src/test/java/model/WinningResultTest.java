package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constants.ErrorType;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("당첨 결과를 올바르게 생성한다.")
        @Test
        void createWinningResult() {
            // given
            Map<LottoRank, Integer> lottoRanks = Map.of(LottoRank.FAIL, 3, LottoRank.FIFTH_PLACE, 1,
                    LottoRank.FIRST_PLACE, 1);

            // when
            WinningResult winningResult = new WinningResult(lottoRanks);

            // then
            assertThat(winningResult.getLottoRanks()).usingRecursiveComparison().isEqualTo(lottoRanks);
        }

        @DisplayName("손해 여부를 올바르게 계산한다.")
        @Test
        void isDamage() {
            // given
            Map<LottoRank, Integer> lottoRanks = Map.of(LottoRank.FAIL, 3);

            // when
            WinningResult winningResult = new WinningResult(lottoRanks);

            // then
            assertThat(winningResult.isRevenue()).isFalse();
        }

        @DisplayName("수익률을 올바르게 계산한다.")
        @Test
        void calculateRateOfRevenue() {
            // given
            Map<LottoRank, Integer> lottoRanks = Map.of(LottoRank.FIFTH_PLACE, 1);
            double expected = 5.0;

            // when
            WinningResult winningResult = new WinningResult(lottoRanks);

            // then
            assertThat(winningResult.calculateRateOfRevenue()).isEqualTo(expected);
        }


    }

    @Nested
    @DisplayName("유효하지 않은 경우의 테스트")
    class InvalidCases {

        @DisplayName("당첨 결과가 음수면 예외가 발생한다.")
        @Test
        void validateSize() {
            // given
            Map<LottoRank, Integer> lottoRanks = Map.of(LottoRank.FAIL, -1);

            // when & then
            assertThatThrownBy(() -> new WinningResult(lottoRanks)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.WINNING_RESULT_POSITIVE.getMessage());
        }
    }
}
