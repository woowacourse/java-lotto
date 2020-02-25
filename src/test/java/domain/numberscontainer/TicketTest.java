package domain.numberscontainer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("티켓 객체 테스트")
public class TicketTest {
    @Test
    @DisplayName("티켓 생성")
    void ticketConstructor() {
        SixLottoNumbersDTO sixLottoNumbersDTO = createLottoNumberDto(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new Ticket(sixLottoNumbersDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("두 티켓의 같은 숫자 비교")
    void compareTwoTickets() {
        SixLottoNumbersDTO sixLottoNumbersDTO = createLottoNumberDto(1, 2, 3, 4, 5, 6);
        Ticket ticket = new Ticket(sixLottoNumbersDTO);
        assertThat(ticket.contains(LottoNumber.THREE)).isTrue();
    }

    private SixLottoNumbersDTO createLottoNumberDto(int number1, int number2, int number3, int number4, int number5, int number6) {
        return new SixLottoNumbersDTO(String.format("%d, %d, %d, %d, %d, %d", number1, number2, number3, number4, number5, number6));
    }
}
