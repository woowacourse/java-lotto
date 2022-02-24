package lotto.view;

import lotto.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class ResultViewTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
    private final Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 33, 41, 5, 7));
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
    void printResultStatisticsTest() {
        WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        LottoResult lottoResult = new LottoResult();

        ResultView.printResultStatistics(lottoResult.generate(lottos, winningNumbers, bonusNumber));

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
