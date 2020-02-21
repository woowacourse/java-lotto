package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.strategy.LottoMachine;
import lotto.view.dto.PrizeResponseBundleDTO;
import lotto.view.dto.WinningLottoRequestDTO;

public class LottoService {

    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTicketBundle getLottoTicketBundle(BettingMoney bettingMoney) {
        return new LottoTicketBundle(lottoMachine.buyTickets(bettingMoney));
    }

    public PrizeResponseBundleDTO getStatisticsDTO(LottoTicketBundle lottoTicketBundle, WinningLottoRequestDTO winningLottoRequestDTO) {
        WinningLotto winningLotto = winningLottoRequestDTO.toWinningLotto();
        LottoResultBundle lottoResultBundle = lottoTicketBundle.getLottoResults(winningLotto);

        return lottoResultBundle.getStatistics();
    }
}
