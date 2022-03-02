package lotto.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.model.Lotto;
import lotto.model.LottoMoney;
import lotto.model.LottoNumber;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.Yield;

class ResultViewTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
    private final Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 5, 7, 33, 41));
    private final Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("생성된 로또 출력 확인")
    void printGeneratedLottosTest() {
        ResultView.printGeneratedLottos(lottos.getLottos());

        assertThat(outputStreamCaptor.toString())
            .contains("2개를 ")
            .contains("[1, 2, 5, 7, 33, 41]");
    }

    @Test
    @DisplayName("당첨 통계 출력 확인")
    void printResultStatisticsTest() throws
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Constructor<LottoNumber> lottoNumberConstructor = LottoNumber.class.getDeclaredConstructor(int.class);
        lottoNumberConstructor.setAccessible(true);
        LottoNumber bonusNumber = lottoNumberConstructor.newInstance(7);
        ResultView.printResultStatistics(new LottoResult(lottos, winningNumbers, bonusNumber));

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
        LottoMoney lottoMoney = new LottoMoney(14000);

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
}
