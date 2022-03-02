package domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @DisplayName("생성된 로또에 대해 당첨 결과가 모두 계산되었는지 테스트")
    @CsvSource(value = {"1,2,3,4,5,6,FIRST", "1,2,3,4,5,7,SECOND", "1,2,3,4,5,8,THIRD",
        "1,2,3,4,7,8,FOURTH", "1,2,3,8,9,10,FIFTH", "1,2,9,10,11,12,SIXTH"})
    public void getWinningStatisticsTest2(int number1, int number2, int number3, int number4, int number5, int number6, String rank) {
        WinningNumber winningNumber = new WinningNumber(
            List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
        LottoNumber bonusBall = LottoNumber.valueOf(7);

        Lotto lotto1 = new Lotto(List.of(LottoNumber.valueOf(number1), LottoNumber.valueOf(number2)
            , LottoNumber.valueOf(number3), LottoNumber.valueOf(number4)
            , LottoNumber.valueOf(number5), LottoNumber.valueOf(number6)));

        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(lotto1);
        Lottos lottos = new Lottos(lottoList);
        Statistic statistic = lottos.getWinningStatistics(winningNumber, bonusBall);

        assertEquals(statistic.getStatistics().get(Rank.valueOf(rank)), 1);
    }
}
