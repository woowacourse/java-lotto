package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import constans.ErrorType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("당첨 번호를 올바르게 생성한다.")
        @Test
        void createWinningNumbers() {
            // given
            List<LottoNumber> numbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(9), new LottoNumber(8),
                            new LottoNumber(45), new LottoNumber(21)));
            LottoNumber bonusNumber = new LottoNumber(4);

            // when
            WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);

            // then

            assertSoftly(softly -> {
                softly.assertThat(winningNumbers.getWinningNumbers()).isEqualTo(numbers);
                softly.assertThat(winningNumbers.getBonusBall()).isEqualTo(bonusNumber);
            });
        }

        @DisplayName("당첨 번호를 올바르게 비교한다.")
        @Test
        void containsLottoNumber() {
            // given
            List<LottoNumber> numbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                            new LottoNumber(5), new LottoNumber(6)));
            LottoNumber bonusNumber = new LottoNumber(7);
            WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);
            LottoNumber lottoNumber = new LottoNumber(1);

            // when
            boolean actual = winningNumbers.containsLottoNumber(lottoNumber);

            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("보너스 번호를 올바르게 비교한다.")
        @Test
        void matchBonusNumber() {
            // given
            List<LottoNumber> numbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                            new LottoNumber(5), new LottoNumber(6)));
            LottoNumber bonusNumber = new LottoNumber(7);
            Lotto lotto = new Lotto(new ArrayList<>(
                    List.of(new LottoNumber(7), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                            new LottoNumber(5), new LottoNumber(6))));
            WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);

            // when
            boolean matchBonusNumber = winningNumbers.matchBonusNumber(lotto);

            // then
            assertThat(matchBonusNumber).isTrue();
        }
    }

    @Nested
    @DisplayName("유효하지 않은 경우의 테스트")
    class InvalidCases {

        @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다.")
        @Test
        void validateSize() {
            // given
            List<LottoNumber> numbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(9), new LottoNumber(8),
                            new LottoNumber(45), new LottoNumber(21), new LottoNumber(30)));
            LottoNumber bonusNumber = new LottoNumber(4);

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.WINNING_NUMBERS_IS_INVALID_SIZE.getMessage());
        }

        @DisplayName("당첨 번호에 중복이 존재한다면 예외가 발생한다.")
        @Test
        void validateDuplicate() {
            // given
            List<LottoNumber> numbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(1), new LottoNumber(9), new LottoNumber(8),
                            new LottoNumber(45), new LottoNumber(21)));
            LottoNumber bonusNumber = new LottoNumber(4);

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.WINNING_NUMBERS_IS_DUPLICATION.getMessage());
        }

        @DisplayName("보너스 볼이 당첨 번호에 존재한다면 예외가 발생한다.")
        @Test
        void validateBonusNumberDuplicate() {
            // given
            List<LottoNumber> numbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(9), new LottoNumber(8),
                            new LottoNumber(45), new LottoNumber(21)));
            LottoNumber bonusNumber = new LottoNumber(1);

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.BONUS_BALL_IS_DUPLICATION.getMessage());
        }

    }
}
