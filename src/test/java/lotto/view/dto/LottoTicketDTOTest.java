package lotto.view.dto;

import lotto.domain.ticket.LottoMachineForTest;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketDTOTest {
    private static LottoTicket ticket;
    private static LottoTicketDTO lottoTicketDTO;

    @BeforeAll
    static void setUpLottoTicket() {
        ticket = new LottoMachineForTest().createOneTicket();
        lottoTicketDTO = new LottoTicketDTO(ticket);
    }


    @DisplayName("로또 티켓 출력용 DTO 생성 확인")
    @Test
    void getLottoTicketDTOS() {
        //given
        LottoTicketBundle ticketBundle = new LottoTicketBundle(Arrays.asList(ticket));

        List<LottoTicketDTO> expectedDTOS = new ArrayList<>();
        expectedDTOS.add(lottoTicketDTO);

        //when
        List<LottoTicketDTO> lottoTicketDTOS = LottoTicketDTO.createLottoTicketDTOS(ticketBundle);

        //then
        assertThat(lottoTicketDTOS).isEqualTo(expectedDTOS);
    }

    @DisplayName("로또 티켓이 가지고 있는 숫자 Integer[]배열로 리턴 확인")
    @Test
    void getNumbers() {
        Integer[] expected = {1, 2, 3, 4, 5, 6};

        //when
        Integer[] numbers = lottoTicketDTO.getNumbers();

        //then
        assertThat(numbers).isEqualTo(expected);
    }
}
