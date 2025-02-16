package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static testfixture.LottoNumberFixture.convertToLottoNumbers;

import error.ErrorType;
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
            List<LottoNumber> numbers = convertToLottoNumbers(1, 2, 3, 4, 5, 6);
            LottoNumber bonusNumber = LottoNumber.of(7);

            // when
            WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);

            // then

            assertSoftly(softly -> {
                softly.assertThat(winningNumbers.getWinningNumbers())
                    .isEqualTo(numbers);
                softly.assertThat(winningNumbers.getBonusBall())
                    .isEqualTo(bonusNumber);
            });
        }

        @DisplayName("로또 매치 수를 올바르게 계산한다.")
        @Test
        void calculateLottoMatchCount() {
            // given
            List<LottoNumber> numbers = convertToLottoNumbers(1, 2, 3, 4, 5, 6);
            LottoNumber bonusNumber = LottoNumber.of(7);
            WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);

            // when
            int matchCount = winningNumbers.calculateLottoMatchCount(numbers);

            // then
            assertThat(matchCount).isEqualTo(6);
        }

        @DisplayName("보너스 번호를 올바르게 비교한다.")
        @Test
        void matchBonusNumber() {
            // given
            List<LottoNumber> numbers = convertToLottoNumbers(1, 2, 3, 4, 5, 6);
            LottoNumber bonusNumber = LottoNumber.of(7);
            WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);

            List<LottoNumber> lottoNumbers = convertToLottoNumbers(1, 2, 3, 4, 5, 7);
            // when
            boolean matchBonusNumber = winningNumbers.matchBonusNumber(lottoNumbers);

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
            List<LottoNumber> numbers = convertToLottoNumbers(1, 2, 3, 4, 5, 6, 7);
            LottoNumber bonusNumber = LottoNumber.of(8);

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber)).isInstanceOf(
                    IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.WINNING_NUMBERS_IS_INVALID_SIZE.getMessage());
        }

        @DisplayName("당첨 번호에 중복이 존재한다면 예외가 발생한다.")
        @Test
        void validateDuplicate() {
            // given
            List<LottoNumber> numbers = convertToLottoNumbers(1, 2, 3, 4, 5, 5);
            LottoNumber bonusNumber = LottoNumber.of(4);

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber)).isInstanceOf(
                    IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.WINNING_NUMBERS_IS_DUPLICATION.getMessage());
        }

        @DisplayName("보너스 볼이 당첨 번호에 존재한다면 예외가 발생한다.")
        @Test
        void validateBonusNumberDuplicate() {
            // given
            List<LottoNumber> numbers = convertToLottoNumbers(1, 2, 3, 4, 5, 6);
            LottoNumber bonusNumber = LottoNumber.of(6);

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber)).isInstanceOf(
                    IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.BONUS_BALL_IS_DUPLICATION.getMessage());
        }
    }
}
