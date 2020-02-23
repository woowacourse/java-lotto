package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.LottoMachine;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.ResultDTO;
import lotto.view.dto.WinningLottoDTO;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTicketBundle createLottoTicketBundle(BettingMoneyDTO bettingMoneyDTO) {
        return new LottoTicketBundle(lottoMachine.buyTickets(bettingMoneyDTO));
    }

    public ResultDTO getResult(WinningLottoDTO winningLottoDTO, LottoTicketBundle lottoTicketBundle) {
        WinningLotto winningLotto = getWinningLotto(winningLottoDTO);

        LottoResultBundle lottoResultBundle = lottoTicketBundle.createLottoResultBundle(winningLotto);

        return lottoResultBundle.createResultDTO();
    }

    private WinningLotto getWinningLotto(WinningLottoDTO winningLottoDTO) {
        return lottoMachine.createWinningLotto(winningLottoDTO);
    }
}
