package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    public static final List<Integer> WINNING_NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6);
    public static final int BONUS_NUMBER = 7;

    @Test
    @DisplayName("구입한 로또 매수만큼 로또 생성")
    void createLottos() {
        int expectedLottoSize = 14;
        Lottos lottos = new Lottos(expectedLottoSize);
        assertThat(lottos.getSize()).isEqualTo(expectedLottoSize);
    }

    private static Stream<Arguments> provideLottosResult() {
        return Stream.of(
            Arguments.of(new Lotto(Arrays.asList(2, 4, 8, 9, 13, 25)), "NONE"),
            Arguments.of(new Lotto(Arrays.asList(2, 4, 6, 8, 13, 25)), "FIFTH"),
            Arguments.of(new Lotto(Arrays.asList(2, 4, 6, 1, 7, 3)), "SECOND"),
            Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), "FIRST")
        );
    }

    @ParameterizedTest
    @DisplayName("당첨 통계 결과 수합")
    @MethodSource("provideLottosResult")
    void lottosResult(Lotto exampleLotto, String exampleRank) {
        Lottos exampleLottos = new Lottos(Collections.singletonList(exampleLotto));
        Map<String, Integer> exampleLottosResult =
            exampleLottos.getStatistics(WINNING_NUMBERS, BONUS_NUMBER);
        int value = exampleLottosResult.get(exampleRank);
        assertThat(value).isEqualTo(1);
    }


}
