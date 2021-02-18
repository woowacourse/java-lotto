package lotto.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import lotto.controller.generator.LottoAutoGenerator;
import lotto.controller.generator.LottoManualGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

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

    @Test
    @DisplayName("구입한 로또 매수만큼 로또 생성")
    void createLottos() {
        int expectedLottoSize = 14;
        LottoAutoGenerator lottoAutoGenerator = new LottoAutoGenerator();
        Lottos lottos = new Lottos(lottoAutoGenerator, expectedLottoSize);
        assertThat(lottos.getSize()).isEqualTo(expectedLottoSize);
    }

    private static Stream<Arguments> provideLottosResult() {
        return Stream.of(
            Arguments.of("2, 4, 8, 9, 13, 25", LottoRank.NONE),
            Arguments.of("2, 4, 6, 8, 13, 25", LottoRank.FIFTH),
            Arguments.of("2, 4, 6, 1, 7, 3", LottoRank.SECOND),
            Arguments.of("1, 2, 3, 4, 5, 6", LottoRank.FIRST)
        );
    }

    @ParameterizedTest
    @DisplayName("당첨 통계 결과 수합")
    @MethodSource("provideLottosResult")
    void lottosResult(String exampleLotto, LottoRank exampleRank) {
        provideInput(exampleLotto);
        scanner = new Scanner(System.in);
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator(scanner);
        Lottos exampleLottos = new Lottos(lottoManualGenerator, 1);
        LottoAnnouncement lottoAnnouncement = new LottoAnnouncement(WINNING_NUMBERS, BONUS_NUMBER);
        Map<LottoRank, Integer> exampleLottosResult =
            exampleLottos.getStatistics(lottoAnnouncement);
        int value = exampleLottosResult.get(exampleRank);
        assertThat(value).isEqualTo(1);
    }


}
