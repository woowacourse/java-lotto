package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.generator.MockedNumberGenerator;
import lotto.model.winning_lotto.WinningLotto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoPrizeTest {

    private static final MockedNumberGenerator mockedNumberGenerator = new MockedNumberGenerator();

    private static Stream<Arguments> 보너스_볼이_매칭되지_않는_경우의_당첨_결과를_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        makeLotto(List.of(1, 2, 3, 4, 5, 6)), makeWinningLotto(List.of(1, 2, 3, 4, 7, 8), 9),
                        LottoPrize.FOUR
                ),
                Arguments.of(
                        makeLotto(List.of(1, 2, 3, 4, 5, 6)), makeWinningLotto(List.of(1, 2, 3, 7, 8, 9), 10),
                        LottoPrize.THREE
                ),
                Arguments.of(
                        makeLotto(List.of(1, 2, 3, 4, 5, 6)), makeWinningLotto(List.of(1, 2, 3, 4, 5, 7), 10),
                        LottoPrize.FIVE
                ),
                Arguments.of(
                        makeLotto(List.of(1, 2, 3, 4, 5, 6)), makeWinningLotto(List.of(1, 2, 3, 4, 7, 8), 5),
                        LottoPrize.FOUR
                )
        );
    }

    private static Lotto makeLotto(List<Integer> numbers) {
        mockedNumberGenerator.setNumbersToGenerate(numbers);
        return Lotto.generateFrom(mockedNumberGenerator);
    }

    private static WinningLotto makeWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        return WinningLotto.of(winningNumbers, bonusNumber);
    }

    private static Stream<Arguments> 보너스_볼이_매칭되는_경우의_당첨_결과를_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        makeLotto(List.of(1, 2, 3, 4, 5, 6)), makeWinningLotto(List.of(1, 2, 3, 4, 5, 7), 6),
                        LottoPrize.FIVE_WITH_BONUS
                ),
                Arguments.of(
                        makeLotto(List.of(1, 2, 3, 4, 5, 6)), makeWinningLotto(List.of(1, 2, 3, 4, 5, 7), 8),
                        LottoPrize.FIVE
                )
        );
    }

    @ParameterizedTest
    @MethodSource("보너스_볼이_매칭되지_않는_경우의_당첨_결과를_반환한다_테스트_케이스")
    void 보너스_볼이_매칭되지_않는_경우의_당첨_결과를_반환한다(Lotto lotto, WinningLotto winningLotto, LottoPrize expected) {

        // When & Then
        LottoPrize actual = LottoPrize.determine(lotto, winningLotto);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("보너스_볼이_매칭되는_경우의_당첨_결과를_반환한다_테스트_케이스")
    void 보너스_볼이_매칭되는_경우의_당첨_결과를_반환한다(Lotto lotto, WinningLotto winningLotto, LottoPrize expected) {

        // When & Then
        LottoPrize actual = LottoPrize.determine(lotto, winningLotto);

        assertThat(actual).isEqualTo(expected);
    }
}