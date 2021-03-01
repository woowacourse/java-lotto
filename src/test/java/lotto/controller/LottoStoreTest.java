package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoProfitRate;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.Piece;
import lotto.domain.generator.LottoManualNumberGenerator;
import lotto.domain.LottoAnnouncement;
import lotto.domain.Lottos;
import lotto.domain.Number;
import lotto.utility.NumberListTranslator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

public class LottoStoreTest {

    public static final List<Number> WINNING_NUMBERS =
        NumberListTranslator.translateIntToNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
    public static final Number BONUS_NUMBER = Number.from(7);

    private static Stream<Arguments> provideLottosResult() {
        return Stream.of(
            Arguments.of("2, 4, 8, 9, 13, 25", 0.00),
            Arguments.of("2, 4, 6, 8, 13, 25", 5.00),
            Arguments.of("2, 4, 6, 1, 7, 3", 30000.00),
            Arguments.of("1, 2, 3, 4, 5, 6", 200000.00)
        );
    }

    @ParameterizedTest
    @DisplayName("총 수익률 계산")
    @MethodSource("provideLottosResult")
    void lottoProfitCalculateTest(String exampleLotto, double profitRate) {
        List<List<Number>> manualLottoNumbers = manualLottoNumbers(exampleLotto);
        LottoManualNumberGenerator lottoManualGenerator
            = new LottoManualNumberGenerator(manualLottoNumbers);
        Lottos exampleLottos = Lottos.generateLottos(lottoManualGenerator,
            new Piece(new Money(Lotto.PRICE),1));
        LottoAnnouncement lottoAnnouncement = new LottoAnnouncement(WINNING_NUMBERS, BONUS_NUMBER);
        LottoResult lottoResult = new LottoResult(lottoAnnouncement, exampleLottos);
        LottoProfitRate lottoProfitRate = lottoResult.getProfitRate();
        assertThat(lottoProfitRate.getProfitRate()).isEqualTo(profitRate);
    }

    private List<List<Number>> manualLottoNumbers(String exampleLotto) {
        List<List<Number>> manualLottoNumbers = new ArrayList<>();
        List<Number> singleManualLottoNumbers = NumberListTranslator.parseToWinner(exampleLotto);
        manualLottoNumbers.add(singleManualLottoNumbers);
        return manualLottoNumbers;
    }
}
