package lotto.view.dto;

import lotto.domain.ticket.LottoMachineForTest;
import lotto.domain.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketDTOTest {

    @DisplayName("LottoTicketDTO 생성 및 멤버 변수(Integer[]) 확인")
    @Test
    void getNumbers() {
        LottoTicket ticket = new LottoMachineForTest().createOneTicket();
        LottoTicketDTO lottoTicketDTO = new LottoTicketDTO(ticket);

        Integer[] expected = {1, 2, 3, 4, 5, 6};

        //when
        Integer[] numbers = lottoTicketDTO.getNumbers();

        //then
        assertThat(numbers).isEqualTo(expected);
    }
}
