package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @BeforeEach
    void initTestFixture() {
        lottoNumbers = new ArrayList<>(
                Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)));
    }

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("당첨 번호를 올바르게 생성한다.")
        @Test
        void createWinningNumbers() {
            // given & when
            WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers);

            // then
            assertThat(winningNumbers).extracting("winningNumbers").isEqualTo(lottoNumbers);
        }

        @DisplayName("당첨 번호를 올바르게 비교한다.")
        @Test
        void containsLottoNumber() {
            // given
            WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers);
            LottoNumber lottoNumber = new LottoNumber(1);

            // when
            boolean actual = winningNumbers.containsLottoNumber(lottoNumber);

            // then
            assertThat(actual).isTrue();
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
            assertThatThrownBy(() -> new WinningNumbers(lottoNumbers)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.WINNING_NUMBERS_IS_INVALID_SIZE.getMessage());
        }

        @DisplayName("당첨 번호에 중복이 존재한다면 예외가 발생한다.")
        @Test
        void validateDuplicate() {
            // given
            lottoNumbers.removeLast();
            lottoNumbers.add(new LottoNumber(1));

            // when & then
            assertThatThrownBy(() -> new WinningNumbers(lottoNumbers)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.WINNING_NUMBERS_IS_DUPLICATION.getMessage());
        }
    }
}
