package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class WinningLottoTest {

    private static Stream<Arguments> numberProvider() {
        return Stream.of(
                Arguments.of(new int[]{11, 12, 13, 18, 19, 20}, 0, false),
                Arguments.of(new int[]{1, 12, 13, 18, 19, 20}, 1, false),
                Arguments.of(new int[]{1, 2, 13, 18, 19, 20}, 2, false),
                Arguments.of(new int[]{1, 2, 3, 8, 9, 10}, 3, false),
                Arguments.of(new int[]{1, 2, 3, 4, 9, 10}, 4, false),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 10}, 5, false),
                Arguments.of(new int[]{1, 2, 3, 4, 7, 10}, 5, true),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, 6, false)
        );
    }

    @DisplayName("우승 로또 티켓과 비교하기")
    @ParameterizedTest
    @MethodSource("numberProvider")
    void name(int[] numbers, int matchCount, boolean isBonusMatch) {
        //given
        Set<LottoBall> winBalls = aLottoTicket(1, 2, 3, 4, 5, 6);
        WinningLotto winningLotto = new WinningLotto(winBalls, LottoBall.of(7));

        LottoTicket buyLottoTicket = new LottoTicket(aLottoTicket(numbers));

        //when
        LottoResult lottoResult = winningLotto.getResult(buyLottoTicket);

        //then
        assertAll(
                "맞춘 갯수와 보너스볼 맞춘지 확인",
                () -> assertThat(lottoResult.getMatchAmount()).isEqualTo(matchCount),
                () -> assertThat(lottoResult.isBonusMatch()).isEqualTo(isBonusMatch)
        );
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