package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.LottoStore;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.RealLottoStore;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.StatisticsResponseDTO;
import lotto.view.dto.WinningLottoRequestDTO;

public class LottoService {

    private final LottoStore lottoStore;

    public LottoService(LottoStore lottoStore) {
        this.lottoStore = lottoStore;
    }

    public LottoTicketBundle getLottoTicketBundle(BettingMoneyRequestDTO bettingMoneyRequestDTO) {
        return new LottoTicketBundle(lottoStore.buyTicket(bettingMoneyRequestDTO));
    }

    public StatisticsResponseDTO getStatisticsDTO(LottoTicketBundle lottoTicketBundle, WinningLottoRequestDTO winningLottoRequestDTO) {
        WinningLotto winningLotto = RealLottoStore.makeWinningLotto(winningLottoRequestDTO);
        LottoResultBundle lottoResultBundle = lottoTicketBundle.getLottoResults(winningLotto);

        return lottoResultBundle.getStatistics();
    }
}
