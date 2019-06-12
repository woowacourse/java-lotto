package lotto.application.lottoticket;

import lotto.domain.lottoticket.dto.LottoTicketsDto;

public class AutomaticLottoService {
    public static LottoTicketsDto getAutomaticLottoTickets(String num) {
        long numOfAutomaticLotto = Long.parseLong(num);
        return LottoTicketAssembler.getAutomaticLottoTickets(numOfAutomaticLotto);
    }
}
