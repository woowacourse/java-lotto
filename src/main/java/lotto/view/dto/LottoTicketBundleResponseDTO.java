package lotto.view.dto;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketBundleResponseDTO {
    private final List<LottoTicketResponseDTO> lottoTicketResponseDTOS;

    public LottoTicketBundleResponseDTO(LottoTicketBundle lottoTicketBundle) {
        List<LottoTicketResponseDTO> dtos = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTicketBundle.getLottoTickets()) {
            dtos.add(new LottoTicketResponseDTO(lottoTicket));
        }
        this.lottoTicketResponseDTOS = dtos;
    }

    public List<LottoTicketResponseDTO> getLottoTicketResponseDTOS() {
        return Collections.unmodifiableList(lottoTicketResponseDTOS);
    }
}
