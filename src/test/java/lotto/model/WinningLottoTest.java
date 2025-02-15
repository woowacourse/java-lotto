package lotto.model;

import static lotto.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("우승 로또 테스트")
class WinningLottoTest {

    @DisplayName("정상적인 경우")
    @Nested
    class SuccessCase {

        @DisplayName("당첨 번호와 보너스 번호를 가지고 있는지 우승 로또를 생성할 수 있다.")
        @Test
        void createTest() {
            Lotto lotto = LottoFixtures.createLottoOneToSix();
            int bonusNumber = 7;

            assertThatCode(() -> new WinningLotto(lotto, bonusNumber))
                    .doesNotThrowAnyException();
        }

        @DisplayName("우승 로또에 구매한 로또를 비교하여 순위를 확인할 수 있다.")
        @Test
        void determineRankTest() {
            Lotto purchasedLotto = LottoFixtures.createLottoOneToSix();
            int bonusNumber = 7;
            WinningLotto winningLotto = new WinningLotto(LottoFixtures.createLottoOneToSix(), bonusNumber);

            assertThat(winningLotto.determineRank(purchasedLotto))
                    .isEqualTo(Rank.FIRST);
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("당첨 번호가 보너스 번호와 중복되는 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6})
        void shouldThrowException_WhenDuplicateBonusNumber(int duplicateNumber) {
            Lotto lotto = LottoFixtures.createLottoOneToSix();

            assertThatThrownBy(() -> new WinningLotto(lotto, duplicateNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        @DisplayName("보너스 번호가 로또 번호 범위를 벗어나는 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {0, 46})
        void shouldThrowException_WhenInvalidBonusNumber(int invalidNumber) {
            Lotto lotto = LottoFixtures.createLottoOneToSix();

            assertThatThrownBy(() -> new WinningLotto(lotto, invalidNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("보너스 번호는 %d부터 %d 사이의 수여야 합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }

        @DisplayName("당첨 로또가 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenNullWinningLotto() {
            int bonusNumber = 7;

            assertThatThrownBy(() -> new WinningLotto(null, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("당첨 번호를 확인할 수 없습니다.");
        }
    }
}
