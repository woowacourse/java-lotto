package lotto.model;

import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketInformationTest {
    private List<LottoTicket> lottoTickets = Arrays.asList(
            new LottoTicket(Arrays.asList(
                    LottoNumber.lottoNumber(1),
                    LottoNumber.lottoNumber(2),
                    LottoNumber.lottoNumber(3),
                    LottoNumber.lottoNumber(4),
                    LottoNumber.lottoNumber(5),
                    LottoNumber.lottoNumber(6))));
    @Test
    @DisplayName("수동 로또 구매 가능한 갯수를 초과했을 때")
    void checkOverPay() {
        assertThatThrownBy(() -> {
            TicketInformation ticketInformation = new TicketInformation(10, 12, lottoTickets);
        }).isInstanceOf(OverRangeException.class)
        .hasMessage("금액의 한도를 초과하였습니다.");
    }

    @Test
    @DisplayName("수동 로또 리스트가 null값인 경우")
    void checkNullManualTickets() {
        assertThatThrownBy(() -> {
            TicketInformation ticketInformation = new TicketInformation(10, 7, null);
        }).isInstanceOf(NullPointerException.class);
    }
}
