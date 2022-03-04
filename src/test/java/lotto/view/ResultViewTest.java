package lotto.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.model.LottoMoney;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.Yield;
import lotto.model.numbergenerator.LottoNumberGenerator;

class ResultViewTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final Lottos lottos = new Lottos(new TestNumberGenerator(), 2);
    private final Lottos manualLottos = new Lottos(new ManualTestNumberGenerator(), 1);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("생성된 로또 출력 확인")
    void printGeneratedLottosTest() {
        ResultView.printGeneratedLottos(manualLottos.getLottos(), lottos.getLottos());

        assertThat(outputStreamCaptor.toString())
            .contains("수동으로 1장")
            .contains("[8, 21, 23, 41, 42, 43]")
            .contains("자동으로 2개")
            .contains("[1, 2, 3, 4, 5, 7]");
    }

    @Test
    @DisplayName("당첨 통계 출력 확인")
    void printResultStatisticsTest() {
        ResultView.printResultStatistics(new LottoResult(lottos, Arrays.asList(1, 2, 3, 4, 5, 6), 7));

        assertThat(outputStreamCaptor.toString())
            .contains("5개 일치, 보너스 볼 일치(30000000원)- 1개")
            .contains("3개 일치 (5000원)- 1개")
            .contains("4개 일치 (50000원)- 0개");
    }

    @ParameterizedTest
    @CsvSource(value = {"5000:총 수익률은 0.36입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)",
        "14000:총 수익률은 1.00입니다.(기준이 1이기 때문에 결과적으로 이득이라는 의미임)"}, delimiter = ':')
    @DisplayName("손해인 경우 수익률을 출력한다.")
    void printMinusYieldTest(Long totalWinningMoney, String expectedMessage) throws
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        LottoMoney lottoMoney = new LottoMoney(14000, 0);

        Constructor<Yield> yieldConstructor = Yield.class.getDeclaredConstructor(LottoMoney.class, long.class);
        yieldConstructor.setAccessible(true);
        Yield yield = yieldConstructor.newInstance(lottoMoney, totalWinningMoney);

        ResultView.printYield(yield);

        assertThat(outputStreamCaptor.toString())
            .contains(expectedMessage);
    }

    @AfterEach
    void afterAll() {
        System.setOut(new PrintStream(System.out));
    }

    static class TestNumberGenerator implements LottoNumberGenerator {
        private final Iterator<List<Integer>> lottoList = generateLotto();

        @Override
        public List<Integer> generate() {
            if (lottoList.hasNext()) {
                return lottoList.next();
            }
            return Collections.emptyList();
        }

        private Iterator<List<Integer>> generateLotto() {
            List<List<Integer>> LottoNumberList = new ArrayList<>(List.of(List.of(1, 2, 3, 4, 5, 7),
                Arrays.asList(1, 2, 5, 7, 33, 41)));
            return LottoNumberList.iterator();
        }
    }

    static class ManualTestNumberGenerator implements LottoNumberGenerator {
        private final Iterator<List<Integer>> lottoList = generateLotto();

        @Override
        public List<Integer> generate() {
            if (lottoList.hasNext()) {
                return lottoList.next();
            }
            return Collections.emptyList();
        }

        private Iterator<List<Integer>> generateLotto() {
            List<List<Integer>> LottoNumberList = new ArrayList<>(List.of(List.of(8, 21, 23, 41, 42, 43)));
            return LottoNumberList.iterator();
        }
    }
}
