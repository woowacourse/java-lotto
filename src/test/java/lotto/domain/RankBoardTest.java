package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RankBoardTest {

    private TotalWinningNumber totalWinningNumber;

    @BeforeEach
    void setUp() {
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber("7");
        totalWinningNumber = new TotalWinningNumber(winningNumber, bonusNumber);
    }

    @Test
    @DisplayName("당첨 결과를 구한다")
    void calcRank() {
        List<LottoNumbers> tickets = initTickets();

        RankBoard board = new RankBoard(totalWinningNumber, tickets);

        Map<Rank, Integer> expected = new HashMap<>();
        expected.put(Rank.FIRST, 1);
        expected.put(Rank.SECOND, 1);
        expected.put(Rank.THIRD, 1);
        expected.put(Rank.FOURTH, 1);
        expected.put(Rank.FIFTH, 1);

        assertThat(board.getBoard()).isEqualTo(expected);
    }

    @Test
    @DisplayName("만약 아무 등수에 해당하지 않는 경우 빈 맵을 반환한다")
    void testCalcRankEdgeCase() {
        List<LottoNumbers> tickets = new ArrayList<>();
        tickets.add(initTicket(List.of(1, 2, 8, 9, 10, 11)));

        RankBoard board = new RankBoard(totalWinningNumber, tickets);

        Map<Rank, Integer> expected = new HashMap<>();
        expected.put(Rank.FIRST, 0);
        expected.put(Rank.SECOND, 0);
        expected.put(Rank.THIRD, 0);
        expected.put(Rank.FOURTH, 0);
        expected.put(Rank.FIFTH, 0);

        assertThat(board.getBoard()).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률을 계산해 반환한다")
    void calcProfit() {
        List<LottoNumbers> tickets = new ArrayList<>();
        tickets.add(initTicket(List.of(1, 2, 3, 9, 10, 11)));

        RankBoard board = new RankBoard(totalWinningNumber, tickets);

        assertThat(board.calcProfitRatio(90_000)).isEqualTo(0.06);
    }

    private List<LottoNumbers> initTickets() {
        ArrayList<LottoNumbers> tickets = new ArrayList<>();
        tickets.add(initTicket(List.of(1, 2, 3, 4, 5, 6)));
        tickets.add(initTicket(List.of(1, 2, 3, 4, 5, 7)));
        tickets.add(initTicket(List.of(1, 2, 3, 4, 5, 8)));
        tickets.add(initTicket(List.of(1, 2, 3, 4, 8, 9)));
        tickets.add(initTicket(List.of(1, 2, 3, 8, 9, 10)));
        return tickets;
    }

    private LottoNumbers initTicket(List<Integer> numbers) {
        return new LottoNumbers(numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
