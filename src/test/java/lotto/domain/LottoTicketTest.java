package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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

    private LottoTicket winningTicket;

    @BeforeEach
    void setUp() {
        winningTicket = new LottoTicket(WINNING_NUMBER);
    }

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

    @Test
    @DisplayName("특정 로또 숫자가 포함되어 있지 않은지")
    void notSpecificNum() {
        assertThatThrownBy(() -> {
            winningTicket.validateBonusNumber(new LottoNumber(1));
        }).isInstanceOf(InvalidLottoTicketException.class);
    }

    @ParameterizedTest
    @MethodSource("tickets")
    @DisplayName("로또 티켓을 비교하여 일치 개수를 반환하는지")
    void compare(String[] ticketNumbers, int matchResult) {
        assertThat(winningTicket.compare(new LottoTicket(ticketNumbers))).isEqualTo(matchResult);
    }

    static Stream<Arguments> tickets() {
        return Stream.of(
            Arguments.of(MATCH_SIX, 6),
            Arguments.of(MATCH_FIVE, 5),
            Arguments.of(MATCH_FOUR, 4),
            Arguments.of(MATCH_THREE, 3)
        );
    }

    @Test
    @DisplayName("만들어진 로또 티켓 안에 특정 숫자가 포함되어 있으면 true 반환하는지")
    void contains() {
        assertThat(winningTicket.contains(new LottoNumber(6))).isTrue();
    }
}
