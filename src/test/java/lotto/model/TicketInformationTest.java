package lotto.model;

import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketInformationTest {
    private List<LottoTicket> lottoTickets = Arrays.asList(
            new LottoTicket(Arrays.asList(
                    LottoNumber.valueOf(1),
                    LottoNumber.valueOf(2),
                    LottoNumber.valueOf(3),
                    LottoNumber.valueOf(4),
                    LottoNumber.valueOf(5),
                    LottoNumber.valueOf(6))));

    private Payment payment = new Payment(10000);

    @Test
    @DisplayName("수동 로또 구매 가능한 갯수를 초과했을 때")
    void checkOverPay() {
        assertThatThrownBy(() -> {
            TicketInformation ticketInformation = new TicketInformation(payment, 12, lottoTickets);
        }).isInstanceOf(OverRangeException.class)
                .hasMessage("금액의 한도를 초과하였습니다.");
    }

    @Test
    @DisplayName("수동 로또 리스트가 null값인 경우")
    void checkNullManualTickets() {
        assertThatThrownBy(() -> {
            TicketInformation ticketInformation = new TicketInformation(payment, 7, null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("자동 로또의 갯수 반환")
    void getAutoTicketCount() {
        TicketInformation ticketInformation = new TicketInformation(payment, 1, lottoTickets);

        assertThat(ticketInformation.getAutoTicketCount()).isEqualTo(9);
    }
}
