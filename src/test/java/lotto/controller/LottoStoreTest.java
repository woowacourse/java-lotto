package lotto.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

public class LottoStoreTest {

    public static final List<Integer> WINNING_NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6);
    public static final int BONUS_NUMBER = 7;

    private static Stream<Arguments> provideLottosResult() {
        return Stream.of(
            Arguments.of(new Lotto("2, 4, 8, 9, 13, 25"), 0.00),
            Arguments.of(new Lotto("2, 4, 6, 8, 13, 25"), 5.00),
            Arguments.of(new Lotto("2, 4, 6, 1, 7, 3"), 30000.00),
            Arguments.of(new Lotto("1, 2, 3, 4, 5, 6"), 200000.00)
        );
    }

    @ParameterizedTest
    @DisplayName("구입가능한 로또 매수 계산")
    @CsvSource(value = {"14000,14", "10200,10", "500,0"})
    void calculateAffordableLottoTicketsTest(int money, int expectedLottoTickets) {
        LottoStore lottoStore = new LottoStore();
        int calculatedLottoTickets = lottoStore.calculateAffordableLottoTickets(new Money(money));
        assertThat(calculatedLottoTickets).isEqualTo(expectedLottoTickets);
    }

    @ParameterizedTest
    @DisplayName("총 수익률 계산")
    @MethodSource("provideLottosResult")
    void lottoProfitCalculateTest(Lotto exampleLotto, double profitRate) {
        Lottos exampleLottos = new Lottos(Collections.singletonList(exampleLotto));
        Map<LottoRank, Integer> exampleLottosResult =
            exampleLottos.getStatistics(WINNING_NUMBERS, BONUS_NUMBER);
        double value = new LottoStore().calculateProfitRate(exampleLottosResult, 1);
        assertThat(value).isEqualTo(profitRate);
    }
}
