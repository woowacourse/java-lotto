package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.LottoRule.LottoNumber;
import lotto.exceptions.InvalidLottoTicketException;

class LottoTicketTest {
    static final String[] WINNING_NUMBER = "1,2,3,4,5,6".split(",");
    static final String[] MATCH_SIX = "1,2,3,4,5,6".split(",");
    static final String[] MATCH_BONUS = "1,2,3,4,5,7".split(",");
    static final String[] MATCH_FIVE = "1,2,3,4,5,9".split(",");
    static final String[] MATCH_FOUR = "1,2,3,4,8,9".split(",");
    static final String[] MATCH_THREE = "1,2,3,7,8,9".split(",");
    static final String[] MATCH_TWO = "1,2,10,7,8,9".split(",");
    static final String[] MATCH_ONE = "1,11,10,7,8,9".split(",");
    static final String[] MATCH_NONE = "12,11,10,7,8,9".split(",");
    static final int BONUS_NUMBER_VALUE = 7;
    static final LottoTicket WINNING_TICKET_NUMBERS = new LottoTicket(WINNING_NUMBER);
    static final LottoNumber BONUS_NUMBER = new LottoNumber(BONUS_NUMBER_VALUE);
    static final WinningTicket WINNING_TICKET = new WinningTicket(WINNING_TICKET_NUMBERS, BONUS_NUMBER);

    @Test
    @DisplayName("정상적으로 사용자 입력을 받았을 경우 제대로 로또티켓이 만들어지는지")
    void validLottoTicketFromUserInput() {
        new LottoTicket(MATCH_NONE);
    }

    @ParameterizedTest(name = "{1}")
    @DisplayName("정상적이지 않은 사용자 입력을 받았을 때 예외를 발생시키는지")
    @MethodSource("invalidParameters")
    void name(String[] parameter, String message) {
        assertThatThrownBy(() -> {
            new LottoTicket(parameter);
        }).isInstanceOf(InvalidLottoTicketException.class);
    }

    static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(new String[] {"1", "2", "3", "4", "5"}, "개수가 올바르지 않은 경우"),
            Arguments.of(new String[] {"1", "1", "1", "1", "1", "1"}, "중복된 번호를 입력할 경우")
        );
    }

    @ParameterizedTest
    @MethodSource("tickets")
    @DisplayName("로또 티켓을 비교하여 일치 개수를 반환하는지")
    void compare(String[] ticketNumbers, int matchResult) {
        assertThat(WINNING_TICKET_NUMBERS.compare(new LottoTicket(ticketNumbers))).isEqualTo(matchResult);
    }

    static Stream<Arguments> tickets() {
        return Stream.of(
            Arguments.of(MATCH_SIX, 6),
            Arguments.of(MATCH_FIVE, 5),
            Arguments.of(MATCH_FOUR, 4),
            Arguments.of(MATCH_THREE, 3),
            Arguments.of(MATCH_TWO, 2),
            Arguments.of(MATCH_ONE, 1),
            Arguments.of(MATCH_NONE, 0)
        );
    }

    @ParameterizedTest
    @CsvSource({"1,true", "7,false"})
    @DisplayName("만들어진 로또 티켓 안에 특정 숫자가 포함되어 있으면 true를 반환하고, 그렇지 않으면 false를 반환하는지")
    void contains(int value, boolean expected) {
        assertThat(WINNING_TICKET_NUMBERS.contains(new LottoNumber(value))).isEqualTo(expected);
    }
}
