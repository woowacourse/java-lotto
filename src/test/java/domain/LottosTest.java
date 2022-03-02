package domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottosTest {

    @Test
    @DisplayName("구입 금액에 따른 개수만큼 로또 생성")
    public void generateLottosTest() {
        int count = 14;
        Lottos lottos = Lottos.generateLottos(new ArrayList<>(), count);

        assertEquals(lottos.size(), 14);
    }

    @Test
    @DisplayName("생성된 로또에 대해 당첨 결과가 모두 계산되었는지 테스트")
    public void getWinningStatisticsTest() {
        int count = 5;
        WinningNumber winningNumber = new WinningNumber(
            List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
        LottoNumber bonusBall = LottoNumber.valueOf(7);
        Lottos lottos = Lottos.generateLottos(new ArrayList<>(), count);
        Statistic statistic = lottos.getWinningStatistics(winningNumber, bonusBall);

        int sumOfValues = statistic.getStatistics().values().stream()
            .mapToInt(Integer::intValue)
            .sum();

        assertEquals(sumOfValues, 5);
    }
}
