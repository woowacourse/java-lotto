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
        LottoTickets tickets = new LottoTickets(
            Arrays.stream(numbers)
                .map(LottoTicket::new)
                .collect(Collectors.toList())
        );
        LottoTicket winningTicket = new LottoTicket(LottoTicketTest.WINNING_NUMBER);
        LottoNumber bonus = LottoNumber.SEVEN;
        result = LottoResult.create(tickets, winningTicket, bonus);
    }

    @ParameterizedTest
    @DisplayName("정상적으로 통계가 반환되는지")
    @MethodSource("matchParameters")
    void match(Rank rank, int expected) {
        assertThat(result.getLottoResult().get(rank)).isEqualTo(expected);
    }
}
