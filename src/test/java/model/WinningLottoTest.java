package model;

import static model.WinningLotto.WINNING_NUMBERS_CONTAIN_BONUS_BALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("provideTicketForCountMatching")
    @DisplayName("로또 티켓의 당첨 번호 개수를 반환한다")
    void returnWinningNumberCount(List<Integer> numbers, int expected) {
        // given
        List<Integer> winnings = Arrays.asList(1,2,3,4,5,6);
        int bonusBallNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winnings, bonusBallNumber);

        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        Lotto lotto = Lotto.from(lottoNumbers);

        // when
        int actual = winningLotto.countMatching(lotto);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideTicketForBonusBall")
    @DisplayName("보너스 볼을 포함하면 true 를 반환한다")
    void bonusBallContaining(List<Integer> numbers, boolean expected) {
        // given
        List<Integer> winnings = Arrays.asList(1,2,3,4,5,6);
        int bonusBallNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winnings, bonusBallNumber);

        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        Lotto lotto = Lotto.from(lottoNumbers);

        // when
        boolean actual = winningLotto.containBonusBall(lotto);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 볼은 중복될 수 없다.")
    void winningNumbersContainBonusBall() {
        // given
        List<Integer> winnings = IntStream.rangeClosed(1, 6)
                .boxed()
                .collect(Collectors.toList());
        int bonusBallNumber = 1;

        // then
        assertThatThrownBy(() -> WinningLotto.of(winnings, bonusBallNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBERS_CONTAIN_BONUS_BALL);
    }

    private static Stream<Arguments> provideTicketForCountMatching() {
        return Stream.of(
                Arguments.of(Arrays.asList(1,2,3,4,5,6), 6),
                Arguments.of(Arrays.asList(7,8,9,10,11,12), 0)
        );
    }

    private static Stream<Arguments> provideTicketForBonusBall() {
        return Stream.of(
                Arguments.of(Arrays.asList(1,2,3,4,5,6), false),
                Arguments.of(Arrays.asList(7,8,9,10,11,12), true)
        );
    }
}
