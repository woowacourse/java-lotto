package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class LottoResultsTest {
    @Test
    void 로또당첨_등수_개수() {
        WinningNumbers winningNumbers = getWinningNumbers();

        Ticket ticketFirst = getFirstTicket();
        Ticket ticketFirst2 = getFirstTicket();
        Ticket ticketSecond = getSecondTicket();
        Ticket ticketThird = getThirdTicket();

        Tickets tickets = new Tickets(Arrays.asList(ticketFirst, ticketSecond, ticketFirst2, ticketThird));
        LottoResults result = LottoResults.of(winningNumbers, tickets);

        assertThat(result.getLottoResults()).hasSize(3)
                .contains(entry(Rank.FIRST, 2), entry(Rank.SECOND, 1), entry(Rank.THIRD, 1));
    }

    @ParameterizedTest(name = "{index}: {4}")
    @MethodSource("validParameters")
    @DisplayName("수익률 검사")
    void validCreate(Ticket ticket1, Ticket ticket2, Ticket ticket3, int amount, double yield, String testName) {
        WinningNumbers winningNumbers = getWinningNumbers();
        Tickets tickets = new Tickets(Arrays.asList(ticket1, ticket2, ticket3));
        LottoResults results = LottoResults.of(winningNumbers, tickets);
        assertThat(new Amount(amount).getYield(results.getProfit())).isEqualTo(yield);
    }

    static Stream<Arguments> validParameters() {
        return Stream.of(
                Arguments.of(getOtherTicket(), getOtherTicket(), getOtherTicket(), 3000, 0, "수익률 0"),
                Arguments.of(getOtherTicket(), getThirdTicket(), getThirdTicket(), 3000, 1000, "수익률 1000"),
                Arguments.of(getFifthTicket(), getSecondTicket(), getFirstTicket(), 3000, 676668.33, "수익률 676668"),
                Arguments.of(getFirstTicket(), getFirstTicket(), getFirstTicket(), 3000, 2000000, "수익률 2000000"));
    }

    private WinningNumbers getWinningNumbers() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(16)));
        LottoNumber bonusNumber = new LottoNumber(6);
        return new WinningNumbers(winTicket, bonusNumber);
    }

    private static Ticket getFifthTicket() {
        return new Ticket(Set.of(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(14),
                new LottoNumber(15),
                new LottoNumber(43)));
    }

    private static Ticket getOtherTicket() {
        return new Ticket(Set.of(new LottoNumber(11),
                new LottoNumber(12),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15),
                new LottoNumber(43)));
    }

    private static Ticket getThirdTicket() {
        return new Ticket(Set.of(new LottoNumber(40),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(16)));
    }

    private static Ticket getSecondTicket() {
        return new Ticket(Set.of(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)));
    }

    private static Ticket getFirstTicket() {
        return new Ticket(Set.of(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(16)));
    }
}
