package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoMatcher;
import lotto.model.ResultMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ResultViewTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
    private final Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 33, 41, 5, 7));
    private final List<Lotto> lottos = Arrays.asList(lotto1, lotto2);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("생성된 로또 출력 확인")
    void printGeneratedLottosTest() {
        ResultView.printGeneratedLottos(lottos);

        assertThat(outputStreamCaptor.toString())
                .contains("2개를 ")
                .contains("[1, 2, 5, 7, 33, 41]");
    }

    @Test
    @DisplayName("당첨 통계 출력 확인")
    void printResultStatisticsTest() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        LottoMatcher matcher = new LottoMatcher(winningNumbers, bonusNumber);
        ResultMap winningResult = matcher.getWinningResult(lottos);

        ResultView.printResultStatistics(winningResult);

        assertThat(outputStreamCaptor.toString())
                .contains("5개 일치, 보너스 볼 일치(30000000원)- 1개")
                .contains("3개 일치 (5000원)- 1개")
                .contains("4개 일치 (50000원)- 0개");
    }

    @Test
    @DisplayName("손해인 경우 수익률을 출력한다.")
    void printMinusYieldTest() {
        float yield = 0.351312312f;
        ResultView.printYield(yield);

        assertThat(outputStreamCaptor.toString())
                .contains("총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }

    @Test
    @DisplayName("이득인 경우 수익률을 출력한다.")
    void printPlusYieldTest() {
        float yield = 1.351312312f;
        ResultView.printYield(yield);

        assertThat(outputStreamCaptor.toString())
                .contains("총 수익률은 1.35입니다.(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");
    }
}
