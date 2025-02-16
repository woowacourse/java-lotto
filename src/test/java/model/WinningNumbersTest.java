package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import constants.ErrorType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    private List<LottoNumber> lottoNumbers;
    private LottoNumber bonusBall;
    private Lotto lotto;

    @BeforeEach
    void initTestFixture() {
        lottoNumbers = new ArrayList<>(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
                , new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        bonusBall = new LottoNumber(7);
        lotto = new Lotto(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(7)));
    }

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("당첨 번호를 올바르게 생성한다.")
        @Test
        void createWinningNumbers() {
            // given & when
            WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusBall);

            // then

            assertSoftly(softly -> {
                softly.assertThat(winningNumbers).extracting("winningNumbers").isEqualTo(lottoNumbers);
                softly.assertThat(winningNumbers).extracting("bonusBall").isEqualTo(bonusBall);
            });
        }

        @DisplayName("당첨 번호를 올바르게 비교한다.")
        @Test
        void containsLottoNumber() {
            // given
            WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusBall);
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
            WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusBall);

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
            lottoNumbers.add(new LottoNumber(10));

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(lottoNumbers, bonusBall)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.WINNING_NUMBERS_IS_INVALID_SIZE.getMessage());
        }

        @DisplayName("당첨 번호에 중복이 존재한다면 예외가 발생한다.")
        @Test
        void validateDuplicate() {
            // given
            lottoNumbers.removeLast();
            lottoNumbers.add(new LottoNumber(1));

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(lottoNumbers, bonusBall)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.WINNING_NUMBERS_IS_DUPLICATION.getMessage());
        }

        @DisplayName("보너스 볼이 당첨 번호에 존재한다면 예외가 발생한다.")
        @Test
        void validateBonusNumberDuplicate() {
            // given
            LottoNumber bonusBallOfContainedLottoNumber = new LottoNumber(1);

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(lottoNumbers, bonusBallOfContainedLottoNumber)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.BONUS_BALL_IS_DUPLICATION.getMessage());
        }

    }
}
