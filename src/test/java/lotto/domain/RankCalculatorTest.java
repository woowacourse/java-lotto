package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class RankCalculatorTest {

    private RankCalculator rankCalculator;
    private TotalNumber totalNumber;

    @BeforeEach
    void setUp() {
        rankCalculator = new RankCalculator();

        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber("7");
        totalNumber = new TotalNumber(winningNumber, bonusNumber);
    }

    @Test
    @DisplayName("당첨 결과를 구한다")
    void calcRank() {
        List<Set<LottoNumber>> tickets = initTickets();
        Map<Rank, Integer> actual = rankCalculator.calcRank(totalNumber, tickets);
        Map<Rank, Integer> expected = new HashMap<>();
        expected.put(Rank.FIRST, 1);
        expected.put(Rank.SECOND, 1);
        expected.put(Rank.THIRD, 1);
        expected.put(Rank.FOURTH, 1);
        expected.put(Rank.FIFTH, 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("만약 아무 등수에 해당하지 않는 경우 빈 맵을 반환한다")
    void testCalcRankEdgeCase() {
        List<Set<LottoNumber>> tickets = new ArrayList<>();
        tickets.add(initTicket(List.of(1, 2, 8, 9, 10, 11)));

        Map<Rank, Integer> actual = rankCalculator.calcRank(totalNumber, tickets);
        Map<Rank, Integer> expected = new HashMap<>();
        expected.put(Rank.FIRST, 0);
        expected.put(Rank.SECOND, 0);
        expected.put(Rank.THIRD, 0);
        expected.put(Rank.FOURTH, 0);
        expected.put(Rank.FIFTH, 0);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률을 계산해 반환한다")
    void calcProfit() {
        List<Set<LottoNumber>> tickets = new ArrayList<>();
        tickets.add(initTicket(List.of(1, 2, 3, 9, 10, 11)));
        rankCalculator.calcRank(totalNumber, tickets);

        double profit = rankCalculator.calcProfitRatio(90_000);

        assertThat(profit).isEqualTo(0.06);
    }

    private List<Set<LottoNumber>> initTickets() {
        ArrayList<Set<LottoNumber>> tickets = new ArrayList<>();
        tickets.add(initTicket(List.of(1, 2, 3, 4, 5, 6)));
        tickets.add(initTicket(List.of(1, 2, 3, 4, 5, 7)));
        tickets.add(initTicket(List.of(1, 2, 3, 4, 5, 8)));
        tickets.add(initTicket(List.of(1, 2, 3, 4, 8, 9)));
        tickets.add(initTicket(List.of(1, 2, 3, 8, 9, 10)));
        return tickets;
    }

    private Set<LottoNumber> initTicket(List<Integer> numbers) {
        Set<LottoNumber> ticket = new HashSet<>();
        for (Integer number : numbers) {
            ticket.add(new LottoNumber(number));
        }
        return ticket;
    }
}
