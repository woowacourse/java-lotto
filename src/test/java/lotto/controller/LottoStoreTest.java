package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.controller.generator.LottoManualGenerator;
import lotto.domain.LottoAnnouncement;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.utility.InputTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

public class LottoStoreTest {

    public static final List<Integer> WINNING_NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6);
    public static final int BONUS_NUMBER = 7;
    private static final String DELIMITER = ", ";

    private InputTest inputTest = new InputTest();

    @BeforeEach
    public void setUp() {
        inputTest.setUp();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        inputTest.restoreSystemInputOutput();
    }

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
        List<List<Integer>> manualLottoNumbers = manualLottoNumbers(exampleLotto);
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator(manualLottoNumbers);
        Lottos exampleLottos = new Lottos(lottoManualGenerator, 1);
        LottoAnnouncement lottoAnnouncement = new LottoAnnouncement(WINNING_NUMBERS, BONUS_NUMBER);
        EnumMap<LottoRank, Integer> exampleLottosResult =
            exampleLottos.getStatistics(lottoAnnouncement);
        double value = new LottoStore().calculateProfitRate(exampleLottosResult, 1);
        assertThat(value).isEqualTo(profitRate);
    }

    private List<List<Integer>> manualLottoNumbers(String exampleLotto) {
        List<List<Integer>> manualLottoNumbers = new ArrayList<>();
        List<Integer> singleManualLottoNumbers = parseToWinner(exampleLotto);
        manualLottoNumbers.add(singleManualLottoNumbers);
        return manualLottoNumbers;
    }

    private List<Integer> parseToWinner(String exampleLotto) {
        String[] splittedWinningNumbers = exampleLotto.split(DELIMITER);
        return Arrays.stream(splittedWinningNumbers)
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}
