import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("구입 금액에 따른 개수만큼 로또 생성")
    public void generateLottosTest() {
        int count = 14;
        Lottos lottos = new Lottos();
        lottos.generateLottos(count);
        assertThat(lottos.size()).isEqualTo(14);
    }

    @Test
    @DisplayName("당첨 번호 통계 테스트")
    public void checkWinningStatisticsTest() {

        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Lotto lotto3 = new Lotto(List.of(13, 14, 15, 16, 17, 18));

        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2, lotto3));
        Map<Rank, Integer> winningStatistics = lottos.getWinningStatistics(winningNumber, 23);

        assertThat(winningStatistics.get(Rank.FIRST)).isEqualTo(1);
        assertThat(winningStatistics.get(Rank.SECOND)).isEqualTo(0);
        assertThat(winningStatistics.get(Rank.FIFTH)).isEqualTo(1);

    }
}