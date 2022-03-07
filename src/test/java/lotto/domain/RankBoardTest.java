package lotto.domain;

import java.util.EnumMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RankBoardTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    @Test
    @DisplayName("당첨 결과를 구한다")
    void calcRank() {
        List<Lotto> lottos = initLottos();

        RankBoard board = new RankBoard(winningLotto, lottos);

        Map<Rank, Integer> expected = new EnumMap<>(Rank.class);
        expected.put(Rank.FIRST, 1);
        expected.put(Rank.SECOND, 1);
        expected.put(Rank.THIRD, 1);
        expected.put(Rank.FOURTH, 1);
        expected.put(Rank.FIFTH, 1);

        assertThat(board.getBoard()).isEqualTo(expected);
    }

    @Test
    @DisplayName("만약 아무 등수에 해당하지 않는 경우 MISS를 증가시킨다.")
    void testCalcRankEdgeCase() {
        List<Lotto> lottos = new ArrayList<>();
        LottoGenerator lottoMachine = new FixedLottoGenerator(List.of(1, 2, 8, 9, 10, 11));
        lottos.add(new Lotto(lottoMachine));

        RankBoard board = new RankBoard(winningLotto, lottos);

        Map<Rank, Integer> expected = new EnumMap<>(Rank.class);
        expected.put(Rank.MISS, 1);

        assertThat(board.getBoard()).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률을 계산해 반환한다")
    void calcProfit() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(new FixedLottoGenerator(List.of(1, 2, 3, 9, 10, 11))));

        RankBoard board = new RankBoard(winningLotto, lottos);

        assertThat(board.calculateProfitRatio(90_000)).isEqualTo(0.06);
    }

    private List<Lotto> initLottos() {
        ArrayList<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(new FixedLottoGenerator(List.of(1, 2, 3, 4, 5, 6))));
        lottos.add(new Lotto(new FixedLottoGenerator(List.of(1, 2, 3, 4, 5, 7))));
        lottos.add(new Lotto(new FixedLottoGenerator(List.of(1, 2, 3, 4, 5, 8))));
        lottos.add(new Lotto(new FixedLottoGenerator(List.of(1, 2, 3, 4, 8, 9))));
        lottos.add(new Lotto(new FixedLottoGenerator(List.of(1, 2, 3, 8, 9, 10))));
        return lottos;
    }
}
