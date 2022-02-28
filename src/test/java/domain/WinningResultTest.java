package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("WinningResult 테스트")
public class WinningResultTest {
    private WinningResult winningResult;

    @BeforeEach
    void setUp() {
        LottoQuantity purchasedLottoQuantity = new LottoQuantity(10);
        winningResult = new WinningResult.Builder(purchasedLottoQuantity)
                .setWinningCountByRank(Rank.FIFTH, new WinningCount(10))
                .build();
    }

    @Test
    @DisplayName("Builder 에 LottoQuantity 를 전달하고, setWinningCountByRank 를 실행하여 WinningResult 를 생성할 수 있다.")
    void createWinningResult() {
        // given
        LottoQuantity purchasedLottoQuantity = new LottoQuantity(100);

        // when
        WinningResult.Builder builder = new WinningResult.Builder(purchasedLottoQuantity);
        for (Rank rank : Rank.values()) {
            builder.setWinningCountByRank(rank, new WinningCount(1));
        }
        builder.build();

        // then
        assertThat(winningResult).isNotNull();
    }

    @Test
    @DisplayName("calculateProfitRatio 는 수익률을 계산할 수 있다.")
    void getProfitRatio() {
        // given & when
        double actual = winningResult.calculateProfitRatio();

        // when
        double expected = 5.0;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("getWinningResult 는 Map<Rank, WinningCount> 형태로 당첨 통계를 반환한다.")
    void getWinningResult() {
        // given
        Map<Rank, WinningCount> actual = winningResult.getWinningResult();

        // when
        Map<Rank, WinningCount> expected = new HashMap<>();
        expected.put(Rank.FIRST, new WinningCount(0));
        expected.put(Rank.SECOND, new WinningCount(0));
        expected.put(Rank.THIRD, new WinningCount(0));
        expected.put(Rank.FOURTH, new WinningCount(0));
        expected.put(Rank.FIFTH, new WinningCount(10));
        expected.put(Rank.NO_MATCH, new WinningCount(0));

        // then
        assertThat(actual).isEqualTo(expected);
    }
}