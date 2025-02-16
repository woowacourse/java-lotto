package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constans.ErrorType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {

    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    public void initTestFixture() {
        lottoNumbers = new ArrayList<>(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
    }

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("로또 번호가 적절하게 생성된다.")
        @Test
        void createLottoNumbers() {
            // given & when
            Lotto actual = new Lotto(lottoNumbers);

            // then
            assertThat(actual.getLottoNumbers()).isEqualTo(lottoNumbers);
        }

        @DisplayName("당첨 번호와 매칭 개수를 적절하게 비교한다.")
        @Test
        void calculateWinningNumbersMatchCount() {
            // given
            Lotto lotto = new Lotto(lottoNumbers);
            WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, new LottoNumber(7));
            int expected = 6;

            // when
            int actual = lotto.calculateWinningNumbersMatchCount(winningNumbers);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("보너스 볼과 매칭을 적절하게 비교한다.")
        @Test
        void isContainsBonusNumber() {
            // given
            Lotto lotto = new Lotto(lottoNumbers);
            LottoNumber bonusBall = new LottoNumber(1);

            // when
            boolean actual = lotto.isContainsNumber(bonusBall);

            // then
            assertThat(actual).isTrue();
        }
    }

    @Nested
    @DisplayName("유효하지 않은 경우의 테스트")
    class InvalidCases {

        @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다.")
        @Test
        void validateSize() {
            // given
            lottoNumbers.add(new LottoNumber(10));

            // when & then
            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.LOTTO_NUMBER_IS_INVALID_SIZE.getMessage());
        }

        @DisplayName("로또 번호에 중복이 존재한다면 예외가 발생한다.")
        @Test
        void validateDuplicate() {
            // given
            lottoNumbers.removeLast();
            lottoNumbers.add(new LottoNumber(1));

            // when & then
            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.LOTTO_NUMBER_DUPLICATE.getMessage());
        }
    }
}
