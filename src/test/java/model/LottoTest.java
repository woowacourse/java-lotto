package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constans.ErrorType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("로또 번호가 적절하게 생성된다.")
        @Test
        void createLottoNumbers() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(9),
                    new LottoNumber(8), new LottoNumber(45), new LottoNumber(21)));

            // when
            Lotto actual = new Lotto(lottoNumbers);

            // then
            assertThat(actual.getLottoNumbers()).isEqualTo(lottoNumbers);
        }

        @DisplayName("당첨 번호와 매칭 개수를 적절하게 비교한다.")
        @Test
        void calculateWinningNumbersMatchCount() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(9),
                    new LottoNumber(8), new LottoNumber(45), new LottoNumber(21)));
            Lotto lotto = new Lotto(lottoNumbers);

            WinningNumbers winningNumbers = new WinningNumbers(new ArrayList<>(
                List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(9),
                    new LottoNumber(8), new LottoNumber(45), new LottoNumber(21))), new LottoNumber(30));
            // when
            int matchCount = lotto.calculateWinningNumbersMatchCount(winningNumbers);

            // then
            assertThat(matchCount).isEqualTo(6);
        }

        @DisplayName("보너스 볼과 매칭을 적절하게 비교한다.")
        @Test
        void isContainsBonusNumber() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                List.of(new LottoNumber(30), new LottoNumber(3), new LottoNumber(9),
                    new LottoNumber(8), new LottoNumber(45), new LottoNumber(21)));
            Lotto lotto = new Lotto(lottoNumbers);

            WinningNumbers winningNumbers = new WinningNumbers(new ArrayList<>(
                List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(9),
                    new LottoNumber(8), new LottoNumber(45), new LottoNumber(21))), new LottoNumber(30));
            // when
            boolean containsBonusNumber = lotto.isContainsBonusNumber(winningNumbers);

            // then
            assertThat(containsBonusNumber).isTrue();
        }
    }

    @Nested
    @DisplayName("유효하지 않은 경우의 테스트")
    class InvalidCases {

        @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다.")
        @Test
        void validateSize() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(9),
                    new LottoNumber(8), new LottoNumber(45), new LottoNumber(21), new LottoNumber(30)));

            // when & then
            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.LOTTO_NUMBER_IS_INVALID_SIZE.getMessage());
        }

        @DisplayName("로또 번호에 중복이 존재한다면 예외가 발생한다.")
        @Test
        void validateDuplicate() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(9),
                    new LottoNumber(1), new LottoNumber(45), new LottoNumber(21)));

            // when & then
            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.LOTTO_NUMBER_DUPLICATE.getMessage());
        }
    }
}
