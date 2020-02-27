package domain.numberscontainer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("티켓 객체 테스트")
public class TicketTest {
    @Test
    @DisplayName("유효한 티켓 생성")
    void ticketConstructor() {
        LottoNumbersDto lottoNumbersDto = createLottoNumberDto(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new Ticket(lottoNumbersDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("티켓 생성")
    void compareTwoTickets() {
        LottoNumbersDto lottoNumbersDto = createLottoNumberDto(1, 2, 3, 4, 5, 6);
        Ticket ticket = new Ticket(lottoNumbersDto);
        assertThat(ticket.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    private LottoNumbersDto createLottoNumberDto(int number1, int number2, int number3, int number4, int number5, int number6) {
        return new LottoNumbersDto(String.format("%d, %d, %d, %d, %d, %d", number1, number2, number3, number4, number5, number6));
    }
}
