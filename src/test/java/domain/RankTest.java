package domain;

import Lotto.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    private static final LottoNumber ONE = new LottoNumber(1);
    private static final LottoNumber TWO = new LottoNumber(2);
    private static final LottoNumber THREE = new LottoNumber(3);
    private static final LottoNumber FOUR = new LottoNumber(4);
    private static final LottoNumber FIVE = new LottoNumber(5);
    private static final LottoNumber SIX = new LottoNumber(6);
    private static final LottoNumber SEVEN = new LottoNumber(7);
    private static final LottoNumber EIGHT = new LottoNumber(8);
    private static final LottoNumber NINE = new LottoNumber(9);
    private static final LottoNumber TEN = new LottoNumber(10);

    @Test
    @DisplayName("로또 당첨된 것 제대로 카운트 하는지 테스트")
    void countRanks() {
        Lotto winningLotto = new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX));
        WinningNumber winningNumber = new WinningNumber(winningLotto, SEVEN);

        Lottos inputLotto = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, EIGHT, NINE)),
                new Lotto(Arrays.asList(ONE, TWO, THREE, EIGHT, NINE, TEN)),
                new Lotto(Arrays.asList(ONE, TWO, THREE, EIGHT, NINE, SEVEN))
        ));
        Ranks ranks = new Ranks(winningNumber, inputLotto);

        Map<Rank, Long> expected = new HashMap<Rank, Long>();
        expected.put(Rank.FOURTH, 1L);
        expected.put(Rank.FIFTH, 2L);
        expected.put(Rank.FIRST, 0L);
        expected.put(Rank.SECOND, 0L);
        expected.put(Rank.THIRD, 0L);

        assertThat(ranks.getRanks()).isEqualTo(expected);
    }
    @Test
    @DisplayName("전체 상금 계산하는 기능 테스트")
    void calculateRankRewards() {
        Lotto winningLotto = new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX));
        WinningNumber winningNumber = new WinningNumber(winningLotto, SEVEN);

        Lottos inputLotto = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, EIGHT, NINE)),
                new Lotto(Arrays.asList(ONE, TWO, THREE, EIGHT, NINE, TEN))
        ));
        Ranks ranks = new Ranks(winningNumber, inputLotto);

        assertThat(ranks.addAllRankReward()).isEqualTo(55000);
    }
}
