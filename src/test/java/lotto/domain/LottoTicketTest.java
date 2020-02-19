package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exceptions.InvalidLottoNumberException;
import lotto.exceptions.InvalidLottoTicketException;

public class LottoTicketTest {
    @Test
    @DisplayName("정상적인 경우")
    void validLottoTicketFromUserInput() {
        new LottoTicket(new String[] {"1", "2", "3", "4", "5", "6"});
    }

    @ParameterizedTest
    @MethodSource("invalidParameters")
    void name(String[] parameter) {
        assertThatThrownBy(() -> {
            new LottoTicket(parameter);
        }).isInstanceOf(InvalidLottoTicketException.class);
    }

    static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of((Object)new String[] {"1", "2", "3", "4", "5"}),
            Arguments.of((Object)new String[] {"1", "1", "1", "1", "1", "1"})
        );
    }

    @Test
    @DisplayName("특정 로또 숫자가 포함되어 있지 않은지 테스트")
    void notSpecificNum() {
        LottoTicket lottoTicket = new LottoTicket(new String[] {"1", "2", "3", "4", "5", "6"});
        assertThatThrownBy(() -> {
            lottoTicket.validateBonusNumber(LottoNumber.ONE);
        }).isInstanceOf(InvalidLottoTicketException.class);
    }

    @ParameterizedTest
    @MethodSource("tickets")
    @DisplayName("로또 티켓을 비교하여 일치 개수를 반환하는지 테스트")
    void compare(String[] ticketNumbers, int matchResult) {
        LottoTicket winningTicket = new LottoTicket(new String[] {"1", "2", "3", "4", "5", "6"});
        assertThat(winningTicket.compare(new LottoTicket(ticketNumbers))).isEqualTo(matchResult);
    }

    static Stream<Arguments> tickets() {
        return Stream.of(
            Arguments.of(new String[] {"1", "2", "3", "4", "5", "6"}, 6),
            Arguments.of(new String[] {"1", "2", "3", "4", "5", "7"}, 5),
            Arguments.of(new String[] {"1", "2", "3", "4", "7", "8"}, 4),
            Arguments.of(new String[] {"1", "2", "3", "7", "8", "9"}, 3)
        );
    }

}
