package lotto.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import lotto.controller.generator.LottoManualGenerator;
import lotto.domain.LottoAnnouncement;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
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
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String inputStringData) {
        testIn = new ByteArrayInputStream(inputStringData.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
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
        provideInput(exampleLotto);
        scanner = new Scanner(System.in);
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator(scanner);
        Lottos exampleLottos = new Lottos(lottoManualGenerator, 1);
        LottoAnnouncement lottoAnnouncement = new LottoAnnouncement(WINNING_NUMBERS, BONUS_NUMBER);
        Map<LottoRank, Integer> exampleLottosResult =
            exampleLottos.getStatistics(lottoAnnouncement);
        double value = new LottoStore().calculateProfitRate(exampleLottosResult, 1);
        assertThat(value).isEqualTo(profitRate);
    }
}
