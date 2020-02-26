package domain;

import Lotto.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    @DisplayName("rank 계산하는 기능 테스트")
    void getRankFromLotto() {
        Lotto winningLotto = new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX));
        LottoNumber bonusNumber = SEVEN;
        WinningNumber winningNumber = new WinningNumber(winningLotto, bonusNumber);

        Lottos inputLotto = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX)),
                new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SEVEN)),
                new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, EIGHT)),
                new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, EIGHT, NINE)),
                new Lotto(Arrays.asList(ONE, TWO, THREE, EIGHT, NINE, TEN))
        ));
        Ranks ranks = inputLotto.calculateMultipleRanks(winningNumber);
        Ranks expected = new Ranks(Arrays.asList(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH));
        assertThat(ranks).isEqualTo(expected);
    }

}
