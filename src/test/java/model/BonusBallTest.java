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

class BonusBallTest {

    private List<LottoNumber> lottoNumbers;
    private WinningNumbers winningNumbers;

    @BeforeEach
    public void initTestFixture() {
        lottoNumbers = new ArrayList<>(
                Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)));
        winningNumbers = new WinningNumbers(lottoNumbers);
    }

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @Test
        @DisplayName("보너스 볼이 올바르게 생성된다.")
        void bonusBall() {
            // given
            LottoNumber lottoNumber = new LottoNumber(7);

            // when
            BonusBall actual = new BonusBall(lottoNumber, winningNumbers);

            // then
            assertThat(actual).extracting("lottoNumber").isEqualTo(lottoNumber);
        }

        @Test
        @DisplayName("보너스 볼이 로또와 올바르게 매칭된다.")
        void matchBonusNumber() {
            // given
            LottoNumber lottoNumber = new LottoNumber(7);
            BonusBall bonusBall = new BonusBall(lottoNumber, winningNumbers);
            Lotto lotto = new Lotto(new ArrayList<>(
                    Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                            new LottoNumber(5), new LottoNumber(7))));

            // when
            boolean actual = bonusBall.matchBonusNumber(lotto);

            // then
            assertThat(actual).isTrue();

        }

    }


    @Nested
    @DisplayName("유효하지 않은 경우의 테스트")
    class InvalidCases {

        @Test
        @DisplayName("보너스 볼이 당첨 번호와 중복된다면 예외가 발생한다.")
        void duplicatedNumberInWinningNumbers() {
            // given
            LottoNumber lottoNumber = new LottoNumber(1);

            assertThatThrownBy(() -> new BonusBall(lottoNumber, winningNumbers)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.BONUS_BALL_IS_DUPLICATION.getMessage());
        }
    }
}
