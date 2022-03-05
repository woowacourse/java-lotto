package domain;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoResultsTest {

    @Test
    void 등수_계산() {
        /*given*/
        Ticket ticket1 = createTicket(1, 2, 3, 4, 5, 6);
        Ticket ticket2 = createTicket(2, 3, 4, 5, 6, 7);
        Ticket ticket3 = createTicket(3, 4, 5, 6, 7, 8);
        Ticket ticket4 = createTicket(4, 5, 6, 7, 8, 9);

        Tickets tickets = new Tickets(Arrays.asList(ticket1, ticket2, ticket3, ticket4));

        WinningNumbers winningNumbers = createWinningTicket(1, 2, 3, 4, 5, 6, 7);

        LottoResults lottoResults = LottoResults.of(winningNumbers, tickets);
        /* when */
        assertThat(lottoResults.getLottoResults())
                /*then*/
                .hasSize(4)
                .contains(entry(Rank.FIRST, 1), entry(Rank.SECOND, 1), entry(Rank.FOURTH, 1), entry(Rank.FIFTH, 1));
    }

    private WinningNumbers createWinningTicket(int num1, int num2, int num3, int num4, int num5, int num6,
                                               int bonusNum) {
        Ticket ticket = createTicket(num1, num2, num3, num4, num5, num6);
        LottoNumber bonusNumber = LottoNumber.valueOf(bonusNum);
        return new WinningNumbers(ticket, bonusNumber);
    }

    private Ticket createTicket(int num1, int num2, int num3, int num4, int num5, int num6) {
        return new Ticket(Set.of(LottoNumber.valueOf(num1),
                LottoNumber.valueOf(num2),
                LottoNumber.valueOf(num3),
                LottoNumber.valueOf(num4),
                LottoNumber.valueOf(num5),
                LottoNumber.valueOf(num6)));
    }

    @Test
    void 수익률_계산() {
        /* given */
        Map<Rank, Integer> results = new TreeMap<>(Comparator.comparingLong(Rank::getAmount));
        results.put(Rank.FIFTH, 2);
        results.put(Rank.SECOND, 2);
        results.put(Rank.FIRST, 1);
        LottoResults lottoResults = new LottoResults(results);

        /* when */
        assertThat(lottoResults.getProfit())
                /* then */
                .isEqualTo(5000 * 2 + 30_000_000 * 2 + 2_000_000_000);
    }
}
