package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.LottoRule.LottoNumber;
import lotto.exceptions.InvalidWinningTicketException;

class WinningTicketTest {
    private static final int duplicatedBonusNumberValue = 6;
    private static final LottoNumber duplicatedBonusNumber = new LottoNumber(duplicatedBonusNumberValue);

    @Test
    @DisplayName("당첨 번호와 중복되는 숫자가 보너스 숫자로 들어오면 예외를 잘 처리하는지")
    void notSpecificNum() {
        assertThatThrownBy(() -> {
            new WinningTicket(LottoTicketTest.WINNING_TICKET_NUMBERS, duplicatedBonusNumber);
        }).isInstanceOf(InvalidWinningTicketException.class);
    }

    @ParameterizedTest
    @MethodSource("tickets")
    @DisplayName("당첨번호와 로또티켓을 비교했을 때 정확한 랭크값이 나오는지")
    void match(String[] ticketNumbers, Rank rank) {
        assertThat(LottoTicketTest.WINNING_TICKET.match(new LottoTicket(ticketNumbers))).isEqualTo(rank);
    }

    static Stream<Arguments> tickets() {
        return Stream.of(
            Arguments.of(LottoTicketTest.MATCH_SIX, Rank.FIRST),
            Arguments.of(LottoTicketTest.MATCH_BONUS, Rank.BONUS),
            Arguments.of(LottoTicketTest.MATCH_FIVE, Rank.SECOND),
            Arguments.of(LottoTicketTest.MATCH_FOUR, Rank.THIRD),
            Arguments.of(LottoTicketTest.MATCH_THREE, Rank.FOURTH),
            Arguments.of(LottoTicketTest.MATCH_TWO, Rank.NONE),
            Arguments.of(LottoTicketTest.MATCH_ONE, Rank.NONE),
            Arguments.of(LottoTicketTest.MATCH_NONE, Rank.NONE)
        );
    }
}
