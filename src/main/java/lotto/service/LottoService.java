package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.ticket.LottoMachine;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.WinLottoTicket;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.ResultDTO;
import lotto.view.dto.WinLottoTicketDTO;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTicketBundle createLottoTicketBundle(BettingMoneyDTO bettingMoneyDTO) {
        return new LottoTicketBundle(lottoMachine.buyTickets(bettingMoneyDTO.getBettingMoney()));
    }

    public ResultDTO getResult(WinLottoTicketDTO winLottoTicketDTO, LottoTicketBundle lottoTicketBundle) {
        WinLottoTicket winLottoTicket = getWinLottoTicket(winLottoTicketDTO);

        LottoResultBundle lottoResultBundle = lottoTicketBundle.createLottoResultBundle(winLottoTicket);

        return lottoResultBundle.createResultDTO();
    }

    private WinLottoTicket getWinLottoTicket(WinLottoTicketDTO winLottoTicketDTO) {
        return lottoMachine.createWinLottoTicket(winLottoTicketDTO);
    }
}
