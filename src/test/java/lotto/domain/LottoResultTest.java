package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultTest {
    private LottoResult result;
    private LottoTickets tickets;
    private LottoTicket winningTicket;
    private LottoNumber bonus;

    static Stream<Arguments> matchParameters() {
        return Stream.of(
            Arguments.of(Rank.FIRST, 2),
            Arguments.of(Rank.SECOND, 1),
            Arguments.of(Rank.BONUS, 1)
        );
    }

    @BeforeEach
    void setUp() {
        String[][] numbers = {
            LottoTicketTest.MATCH_SIX,
            LottoTicketTest.MATCH_SIX,
            LottoTicketTest.MATCH_BONUS,
            LottoTicketTest.MATCH_FIVE,
        };
        tickets = new LottoTickets(
            Arrays.stream(numbers)
                .map(LottoTicket::new)
                .collect(Collectors.toList())
        );
        winningTicket = new LottoTicket(LottoTicketTest.WINNING_NUMBER);
        bonus = LottoNumber.SEVEN;
        result = LottoResult.create();
    }

    @ParameterizedTest
    @DisplayName("정상적으로 통계가 반환되는지")
    @MethodSource("matchParameters")
    void match(Rank rank, int expected) {
        assertThat(result.match(tickets, winningTicket, bonus).get(rank)).isEqualTo(expected);
    }
}
