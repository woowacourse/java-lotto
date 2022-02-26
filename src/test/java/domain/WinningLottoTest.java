package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {

    private static Stream<Arguments> lottoAndExpectedPrize() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(3, 4, 5, 6, 10, 15)), Prize.FAIL),
                Arguments.of(new Lotto(List.of(4, 5, 6, 10, 15, 20)), Prize.FIFTH),
                Arguments.of(new Lotto(List.of(5, 6, 10, 15, 20, 25)), Prize.FOURTH),
                Arguments.of(new Lotto(List.of(6, 10, 15, 20, 25, 30)), Prize.THIRD),
                Arguments.of(new Lotto(List.of(10, 15, 20, 25, 30, 40)), Prize.SECOND),
                Arguments.of(new Lotto(List.of(10, 15, 20, 25, 30, 35)), Prize.FIRST));
    }

    @ParameterizedTest
    @MethodSource("lottoAndExpectedPrize")
    void calculatePrize_rightPrize(Lotto lotto, Prize expected) {
        WinningLotto winningLotto = new WinningLotto(List.of(10, 15, 20, 25, 30, 35), 40);
        assertThat(winningLotto.calculatePrize(lotto)).isEqualTo(expected);
    }

}
