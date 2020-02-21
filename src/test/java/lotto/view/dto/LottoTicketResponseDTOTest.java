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

class LottoTicketResponseDTOTest {
    private static LottoTicket ticket;
    private static LottoTicketResponseDTO lottoTicketResponseDTO;

    @BeforeAll
    static void setUpLottoTicket() {
        ticket = new LottoMachineForTest().createOneTicket();
        lottoTicketResponseDTO = new LottoTicketResponseDTO(ticket);
    }


    @DisplayName("로또 티켓 출력용 DTO 생성 확인")
    @Test
    void getLottoTicketResponseDTOS() {
        //given
        LottoTicketBundle ticketBundle = new LottoTicketBundle(Arrays.asList(ticket));

        List<LottoTicketResponseDTO> expectedDTOS = new ArrayList<>();
        expectedDTOS.add(lottoTicketResponseDTO);

        //when
        List<LottoTicketResponseDTO> lottoTicketResponseDTOS = LottoTicketResponseDTO.getLottoTicketResponseDTOS(ticketBundle);

        //then
        assertThat(lottoTicketResponseDTOS).isEqualTo(expectedDTOS);
    }

    @DisplayName("로또 티켓이 가지고 있는 숫자 Integer[]배열로 리턴 확인")
    @Test
    void getNumbers() {
        Integer[] expected = {1, 2, 3, 4, 5, 6};

        //when
        Integer[] numbers = lottoTicketResponseDTO.getNumbers();

        //then
        assertThat(numbers).isEqualTo(expected);
    }
}
