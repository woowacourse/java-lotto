package lotto.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.model.Lotto;
import lotto.model.LottoFactory;
import lotto.model.LottoMoney;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.ManualLottoFactory;
import lotto.model.Rank;
import lotto.model.Yield;

class ResultViewTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    Constructor<Lottos> lottosConstructor = Lottos.class
        .getDeclaredConstructor(LottoFactory.class, int.class);
    Constructor<LottoResult> lottoResultConstructor = LottoResult.class.
        getDeclaredConstructor(Lottos.class, Lottos.class, List.class, int.class);
    Constructor<LottoMoney> lottoMoneyConstructor = LottoMoney.class.getDeclaredConstructor(long.class, int.class);
    Constructor<Yield> yieldConstructor = Yield.class.getDeclaredConstructor(LottoMoney.class, Map.class);

    ResultViewTest() throws
        NoSuchMethodException {
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("생성된 로또 출력 확인")
    void printGeneratedLottosTest() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        lottosConstructor.setAccessible(true);

        Lottos manualLottos = lottosConstructor.newInstance(new TestManualLottoFactory(), 1);
        Lottos autoLottos = lottosConstructor.newInstance(new TestAutoLottoFactory(), 2);

        ResultView.printGeneratedLottos(manualLottos.getLottos(), autoLottos.getLottos());

        assertThat(outputStreamCaptor.toString())
            .contains("수동으로 1장")
            .contains("[8, 21, 23, 41, 42, 43]")
            .contains("자동으로 2개")
            .contains("[1, 2, 3, 4, 5, 7]");
    }

    @Test
    @DisplayName("당첨 통계 출력 확인")
    void printResultStatisticsTest() throws
        InvocationTargetException, InstantiationException, IllegalAccessException {
        lottosConstructor.setAccessible(true);
        lottoResultConstructor.setAccessible(true);
        lottosConstructor.setAccessible(true);

        Lottos manualLottos = lottosConstructor.newInstance(new TestManualLottoFactory(), 1);
        Lottos autoLottos = lottosConstructor.newInstance(new TestAutoLottoFactory(), 2);

        LottoResult lottoResult = lottoResultConstructor
            .newInstance(manualLottos, autoLottos, List.of(1, 2, 3, 4, 5, 6), 7);

        ResultView.printResultStatistics(lottoResult);

        assertThat(outputStreamCaptor.toString())
            .contains("5개 일치, 보너스 볼 일치(30000000원)- 1개")
            .contains("3개 일치 (5000원)- 1개")
            .contains("4개 일치 (50000원)- 0개");
    }

    @ParameterizedTest
    @CsvSource(value = {"14000:총 수익률은 0.36입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)",
        "5000:총 수익률은 1.00입니다.(기준이 1이기 때문에 결과적으로 이득이라는 의미임)"}, delimiter = ':')
    @DisplayName("손해인 경우 수익률을 출력한다.")
    void printMinusYieldTest(long rawlottoMoney, String expectedMessage) throws
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        lottoMoneyConstructor.setAccessible(true);
        yieldConstructor.setAccessible(true);

        LottoMoney lottoMoney = lottoMoneyConstructor.newInstance(rawlottoMoney, 0);

        Map<Rank, Long> result = new EnumMap<>(Rank.class);
        result.put(Rank.FIFTH, 1L);

        Yield yield = yieldConstructor.newInstance(lottoMoney, result);

        ResultView.printYield(yield);

        assertThat(outputStreamCaptor.toString())
            .contains(expectedMessage);
    }

    @AfterEach
    void afterAll() {
        System.setOut(new PrintStream(System.out));
    }

    static class TestAutoLottoFactory implements LottoFactory {
        private final LottoFactory factory;

        public TestAutoLottoFactory() {
            this.factory = new ManualLottoFactory(List.of(List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 5, 7, 33, 41)));
        }

        @Override
        public Lotto generate() {
            return factory.generate();
        }
    }

    static class TestManualLottoFactory implements LottoFactory {
        @Override
        public Lotto generate() {
            ManualLottoFactory factory = new ManualLottoFactory(List.of(List.of(8, 21, 23, 41, 42, 43)));
            return factory.generate();
        }
    }
}
