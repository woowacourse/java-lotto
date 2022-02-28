package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
    private WinningResult winningResult;

    @BeforeEach
    void setUp() {
        LottoQuantity purchasedLottoQuantity = new LottoQuantity(10);
        winningResult = new WinningResult.Builder(purchasedLottoQuantity)
                .first(new WinningCount(0))
                .second(new WinningCount(0))
                .third(new WinningCount(0))
                .fourth(new WinningCount(0))
                .fifth(new WinningCount(10))
                .noMatch(new WinningCount(0))
                .build();
    }

    @Test
    @DisplayName("WinningResult 생성 테스트")
    void createWinningResult() {
        // given
        LottoQuantity purchasedLottoQuantity = new LottoQuantity(100);

        // when
        WinningResult winningResult = new WinningResult.Builder(purchasedLottoQuantity)
                .first(new WinningCount(1))
                .second(new WinningCount(2))
                .third(new WinningCount(3))
                .fourth(new WinningCount(4))
                .fifth(new WinningCount(5))
                .noMatch(new WinningCount(6))
                .build();

        // then
        assertThat(winningResult).isNotNull();
    }

    @Test
    @DisplayName("수익률 계산")
    void getProfitRatio() {
        // given & when
        double actual = winningResult.getProfitRatio();

        // when
        double expected = 5.0;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("winningResult getter")
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