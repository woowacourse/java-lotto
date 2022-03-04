package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public class LottosTest {

    WinningNumber winningNumber;
    LottoNumber bonusBall;

    @BeforeEach
    void initWinningNumber() {
        winningNumber = new WinningNumber(
            List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
        bonusBall = LottoNumber.valueOf(7);
    }

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

        Lottos lottos = Lottos.generateLottos(new ArrayList<>(), count);
        Statistic statistic = lottos.getWinningStatistics(winningNumber, bonusBall);

        int sumOfValues = statistic.getStatistics().values().stream()
            .mapToInt(Integer::intValue)
            .sum();

        assertEquals(sumOfValues, 5);
    }

    @ParameterizedTest
    @DisplayName("생성된 로또에 대해 당첨 결과가 모두 계산되었는지 테스트")
    @MethodSource("generateLottoData")
    public void getWinningStatisticsTestByRank(List<Integer> lottoNumbers, Rank rank) {

        Lotto lotto = new Lotto(
            lottoNumbers.stream()
                .map(number -> LottoNumber.valueOf(number))
                .collect(Collectors.toList()));

        List<Lotto> lottoList = new ArrayList<>(List.of(lotto));
        Lottos lottos = Lottos.generateLottos(lottoList, 0);
        Statistic statistic = lottos.getWinningStatistics(winningNumber, bonusBall);
        int countOfRank = statistic.getStatistics().get(rank);

        assertEquals(countOfRank, 1);
    }

    static Stream<Arguments> generateLottoData() {
        return Stream.of(
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Rank.FIRST),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), Rank.SECOND),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 8), Rank.THIRD),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 8, 9), Rank.FOURTH),
            Arguments.of(Arrays.asList(1, 2, 3, 10, 11, 12), Rank.FIFTH),
            Arguments.of(Arrays.asList(1, 2, 10, 11, 12, 13), Rank.SIXTH)
        );
    }
}
