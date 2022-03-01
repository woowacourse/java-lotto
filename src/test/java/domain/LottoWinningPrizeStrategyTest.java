package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoWinningPrizeStrategyTest {

    private static final Map<Integer, WinningPrize> MATCH_COUNT_WINNING_PRIZE_INFO = new HashMap<>() {{
        put(6, WinningPrize.FIRST);
        put(5, WinningPrize.THIRD);
        put(4, WinningPrize.FOURTH);
        put(3, WinningPrize.FIFTH);
    }};
    private final LottoWinningPrizeStrategy defaultLottoWinningPrizeStrategy
            = new LottoWinningPrizeStrategy();

    @Test
    @DisplayName("기본 로또 당첨보상 전략이 2등을 잘 판단하는지 확인한다.")
    void checkWinningSecond() {
        WinningPrize winningPrize = defaultLottoWinningPrizeStrategy.findWinningPrize(5, true);
        assertThat(winningPrize).isEqualTo(WinningPrize.SECOND);
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 5, 4, 3})
    @DisplayName("기본 로또 당첨보상 전략이 2등을 제외한 당첨보상을 잘 판단하는지 확인한다.")
    void checkWinning(int matchCount) {
        WinningPrize winningPrize = defaultLottoWinningPrizeStrategy.findWinningPrize(matchCount, false);
        assertThat(winningPrize).isEqualTo(MATCH_COUNT_WINNING_PRIZE_INFO.get(matchCount));
    }

    @ParameterizedTest
    @EnumSource(WinningPrize.class)
    @DisplayName("기본 로또 당첨보상 전략이 보상으로 맞는 숫자 갯수를 도출할 수 있는지 확인한다.")
    void checkWinningPriceToMatchCount(WinningPrize winningPrize) {
        Map<WinningPrize, Integer> matchCounts = new HashMap<>() {{
            put(WinningPrize.FIRST, 6);
            put(WinningPrize.SECOND, 5);
            put(WinningPrize.THIRD, 5);
            put(WinningPrize.FOURTH, 4);
            put(WinningPrize.FIFTH, 3);
        }};

        assertThat(defaultLottoWinningPrizeStrategy.findMatchCount(winningPrize))
                .isEqualTo(matchCounts.get(winningPrize));
    }

    @Test
    @DisplayName("기본 로또 당첨보상 전략이 2등 보상이 입력되었을 때 matchBonus가 true를 반환하는지 확인한다.")
    void checkMatchBonus() {
        assertThat(defaultLottoWinningPrizeStrategy.findMatchBonus(WinningPrize.SECOND)).isTrue();
    }
}