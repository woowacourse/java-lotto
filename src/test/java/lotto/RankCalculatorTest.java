package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class RankCalculatorTest {

    @Test
    @DisplayName("당첨 결과를 구한다")
    void calcRank() {
        RankCalculator rankCalculator = new RankCalculator();
        List<Set<LottoNumber>> tickets = initTickets();

        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber("7");
        TotalNumber totalNumber = new TotalNumber(winningNumber, bonusNumber);

        Map<Rank, Integer> result = rankCalculator.calcRank(totalNumber, tickets);

        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("만약 아무 등수에 해당하지 않는 경우 빈 맵을 반환한다")
    void testCalcRankEdgeCase() {
        RankCalculator rankCalculator = new RankCalculator();
        List<Set<LottoNumber>> tickets = new ArrayList<>();
        tickets.add(initTicket(List.of(1, 2, 8, 9, 10, 11)));

        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber("7");
        TotalNumber totalNumber = new TotalNumber(winningNumber, bonusNumber);


        Map<Rank, Integer> result = rankCalculator.calcRank(totalNumber, tickets);

        assertThat(result.get(Rank.FIRST)).isEqualTo(0);
        assertThat(result.get(Rank.SECOND)).isEqualTo(0);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(0);
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
