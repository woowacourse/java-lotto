package lotto.domain.result;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoBall;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {

    private static Stream<Arguments> numberProvider() {
        return Stream.of(
                Arguments.of(new int[]{11, 12, 13, 18, 19, 20}, new LottoResult(0, false)),
                Arguments.of(new int[]{1, 12, 13, 18, 19, 20}, new LottoResult(1, false)),
                Arguments.of(new int[]{1, 2, 13, 18, 19, 20}, new LottoResult(2, false)),
                Arguments.of(new int[]{1, 2, 3, 8, 9, 10}, new LottoResult(3, false)),
                Arguments.of(new int[]{1, 2, 3, 4, 9, 10}, new LottoResult(4, false)),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 10}, new LottoResult(5, false)),
                Arguments.of(new int[]{1, 2, 3, 4, 7, 10}, new LottoResult(5, true)),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, new LottoResult(6, false))
        );
    }

    @DisplayName("우승 로또 티켓과 비교하기")
    @ParameterizedTest
    @MethodSource("numberProvider")
    void name(int[] numbers, LottoResult expectedResult) {
        //given
        Set<LottoBall> winBalls = aLottoTicket(1, 2, 3, 4, 5, 6);
        WinningLotto winningLotto = new WinningLotto(winBalls, LottoBall.of(7));

        LottoTicket buyLottoTicket = new LottoTicket(aLottoTicket(numbers));

        //when
        LottoResult lottoResult = winningLotto.getResult(buyLottoTicket);

        //then
        assertThat(lottoResult).isEqualTo(expectedResult);
    }

    private Set<LottoBall> aLottoTicket(int... numbers) {
        return new HashSet<>(Arrays.asList(
                LottoBall.of(numbers[0]),
                LottoBall.of(numbers[1]),
                LottoBall.of(numbers[2]),
                LottoBall.of(numbers[3]),
                LottoBall.of(numbers[4]),
                LottoBall.of(numbers[5])));
    }
}
