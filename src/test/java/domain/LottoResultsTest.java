package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoResultsTest {

    @ParameterizedTest(name = "{index}: {4}")
    @MethodSource("validRankCount")
    @DisplayName("로또당첨 등수 개수 검사")
    void validResult(Ticket ticket1,
                     Ticket ticket2,
                     Ticket ticket3,
                     int size,
                     String testName) {
        WinningNumbers winningNumbers = getWinningNumbers();
        Tickets tickets = new Tickets(Arrays.asList(ticket1, ticket2, ticket3));
        LottoResults result = LottoResults.of(winningNumbers, tickets);

        assertThat(result.getLottoResults()).hasSize(size);
    }

    static Stream<Arguments> validRankCount() {
        return Stream.of(
                Arguments.of(getOtherTicket(), getOtherTicket(), getOtherTicket(), 1, "등수 개수 1"),
                Arguments.of(getOtherTicket(), getThirdTicket(), getThirdTicket(), 2, "등수 개수 2"),
                Arguments.of(getFifthTicket(), getSecondTicket(), getFirstTicket(), 3, "등수 개수 3"));
    }

    @ParameterizedTest(name = "{index}: {4}")
    @MethodSource("validParameters")
    @DisplayName("수익률 검사")
    void validCreate(Ticket ticket1,
                     Ticket ticket2,
                     Ticket ticket3,
                     int amount,
                     double yield,
                     String testName) {
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
        Ticket winTicket = new Ticket(Set.of(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(16)));
        LottoNumber bonusNumber = LottoNumber.valueOf(6);
        return new WinningNumbers(winTicket, bonusNumber);
    }

    private static Ticket getFifthTicket() {
        return new Ticket(Set.of(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(14),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(43)));
    }

    private static Ticket getOtherTicket() {
        return new Ticket(Set.of(LottoNumber.valueOf(11),
                LottoNumber.valueOf(12),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(14),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(43)));
    }

    private static Ticket getThirdTicket() {
        return new Ticket(Set.of(LottoNumber.valueOf(40),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(16)));
    }

    private static Ticket getSecondTicket() {
        return new Ticket(Set.of(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));
    }

    private static Ticket getFirstTicket() {
        return new Ticket(Set.of(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(16)));
    }
}
