package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.LottoCompany;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.StatisticsResponseDTO;
import lotto.view.dto.WinningLottoRequestDTO;

public class LottoService {
    private final LottoCompany lottoCompany;

    public LottoService(LottoCompany lottoCompany) {
        this.lottoCompany = lottoCompany;
    }

    public LottoTicketBundle getLottoTicketBundle(BettingMoneyRequestDTO bettingMoneyRequestDTO) {
        return new LottoTicketBundle(lottoCompany.buyTicket(bettingMoneyRequestDTO));
    }

    public StatisticsResponseDTO getStatisticsDTO(LottoTicketBundle lottoTicketBundle, WinningLottoRequestDTO winningLottoRequestDTO) {
        WinningLotto winningLotto = lottoCompany.makeWinningLotto(winningLottoRequestDTO);
        LottoResultBundle lottoResultBundle = lottoTicketBundle.getLottoResults(winningLotto);

        return lottoResultBundle.getStatistics();
    }
}
