package lotto.domain.result.win;

import lotto.LottoHelper;
import lotto.domain.result.MatchResult;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoBall;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import static lotto.LottoHelper.lottoBalls;
import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {

    private static Stream<Arguments> numberProvider() {
        return Stream.of(
                Arguments.of(lottoBalls(Arrays.asList(11, 12, 13, 18, 19, 20)), new MatchResult(0, false)),
                Arguments.of(lottoBalls(Arrays.asList(1, 12, 13, 18, 19, 20)), new MatchResult(1, false)),
                Arguments.of(lottoBalls(Arrays.asList(1, 2, 13, 18, 19, 20)), new MatchResult(2, false)),
                Arguments.of(lottoBalls(Arrays.asList(1, 2, 3, 8, 9, 10)), new MatchResult(3, false)),
                Arguments.of(lottoBalls(Arrays.asList(1, 2, 3, 4, 9, 10)), new MatchResult(4, false)),
                Arguments.of(lottoBalls(Arrays.asList(1, 2, 3, 4, 5, 10)), new MatchResult(5, false)),
                Arguments.of(lottoBalls(Arrays.asList(1, 2, 3, 4, 7, 10)), new MatchResult(5, true)),
                Arguments.of(lottoBalls(Arrays.asList(1, 2, 3, 4, 5, 6)), new MatchResult(6, false))
        );
    }

    @DisplayName("우승 로또 티켓과 비교하기")
    @ParameterizedTest
    @MethodSource("numberProvider")
    void name(Set<LottoBall> numbers, MatchResult expectedResult) {
        //given
        Set<Integer> winningNumbers = LottoHelper.numbers(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoTicket buyLottoTicket = new LottoTicket(numbers);

        //when
        MatchResult matchResult = winningLotto.getResult(buyLottoTicket);

        //then
        assertThat(matchResult).isEqualTo(expectedResult);
    }

}
