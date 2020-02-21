package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.LottoMachine;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.StatisticsResponseDTO;
import lotto.view.dto.WinningLottoRequestDTO;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTicketBundle getLottoTicketBundle(BettingMoneyRequestDTO bettingMoneyRequestDTO) {
        return new LottoTicketBundle(lottoMachine.buyTickets(bettingMoneyRequestDTO));
    }

    public StatisticsResponseDTO getStatisticsDTO(LottoTicketBundle lottoTicketBundle, WinningLottoRequestDTO winningLottoRequestDTO) {
        WinningLotto winningLotto = lottoMachine.createWinningLotto(winningLottoRequestDTO);
        LottoResultBundle lottoResultBundle = lottoTicketBundle.getLottoResults(winningLotto);

        return lottoResultBundle.getStatistics();
    }
}
