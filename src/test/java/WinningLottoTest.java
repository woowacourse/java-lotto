import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호에 보너스 번호가 존재하면 예외가 발생한다")
    void should_throw_exception_when_bonus_number_is_in_winning_numbers() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(1);

        // when & then
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> correctMatchedCountArguments() {
        return Stream.of(arguments(new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Number(7)),
                        new Lotto(List.of(1, 2, 3, 43, 44, 45)), 3),
                arguments(new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Number(7)),
                        new Lotto(List.of(1, 2, 3, 7, 44, 45)), 3)

        );
    }

    @ParameterizedTest
    @DisplayName("로또 번호가 주어졌을 때 적중 개수를 정확히 반환한다")
    @MethodSource("correctMatchedCountArguments")
    void should_return_correct_matched_count(WinningLotto winningLotto, Lotto lotto, int expected) {
        // when
        final int matchedCount = winningLotto.getMatchedCount(lotto);

        // then
        assertThat(matchedCount).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("로또 번호가 주어졌을 때 보너스 번호의 적중 여부를 정확히 반환한다")
    @CsvSource(value = {"6, true", "7, false"})
    void should_return_correct_bonus_match(int bonusNumber, boolean expected) {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 45));
        Number bonus = new Number(bonusNumber);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        final boolean isBonusMatched = winningLotto.isMatchBonus(lotto);

        // then
        assertThat(isBonusMatched).isEqualTo(expected);
    }
}