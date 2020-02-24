package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.ticket.LottoMachine;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.WinLottoTicket;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.ResultDTO;
import lotto.view.dto.WinLottoTicketDTO;

import java.util.List;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTicketBundle createLottoTicketBundle(BettingMoneyDTO bettingMoneyDTO) {
        int bettingMoney = bettingMoneyDTO.getBettingMoney();

        return new LottoTicketBundle(lottoMachine.buyTickets(bettingMoney));
    }

    public ResultDTO getResult(WinLottoTicketDTO winLottoTicketDTO, LottoTicketBundle lottoTicketBundle) {
        WinLottoTicket winLottoTicket = createWinLottoTicket(winLottoTicketDTO);

        LottoResultBundle lottoResultBundle = lottoTicketBundle.createLottoResultBundle(winLottoTicket);

        return lottoResultBundle.createResultDTO();
    }

    private WinLottoTicket createWinLottoTicket(WinLottoTicketDTO winLottoTicketDTO) {
        List<Integer> winNumbers = winLottoTicketDTO.getWinNumbers();
        int bonusNumber = winLottoTicketDTO.getBonusNumber();

        return lottoMachine.createWinLottoTicket(winNumbers, bonusNumber);
    }
}
