package controller.dto;

import java.util.List;
import model.LottoTicket;
import model.WinningLotto;

public class LottoDtoMapper {

    public List<LottoTicketResponse> toLottoTicketResponse(List<LottoTicket> lottoTickets) {
        return lottoTickets.stream()
                .map(ticket -> LottoTicketResponse.from(ticket.getNumbers()))
                .toList();
    }

    public WinningLotto toWinningLotto(WinningLottoRequest winningLottoRequest) {
        return new WinningLotto(winningLottoRequest.numbers(), winningLottoRequest.bonusNumber());
    }
}
