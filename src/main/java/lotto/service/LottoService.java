package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.LottoCompany;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.StatisticsResponseDTO;
import lotto.view.dto.WinningLottoRequestDTO;

public class LottoService {

    public static LottoTicketBundle getLottoTicketBundle(BettingMoneyRequestDTO bettingMoneyRequestDTO) {
        return new LottoTicketBundle(LottoCompany.buyTicket(bettingMoneyRequestDTO));
    }

    public static StatisticsResponseDTO getStatisticsDTO(LottoTicketBundle lottoTicketBundle, WinningLottoRequestDTO winningLottoRequestDTO) {
        WinningLotto winningLotto = LottoCompany.makeWinningLotto(winningLottoRequestDTO);
        LottoResultBundle lottoResultBundle = lottoTicketBundle.getLottoResults(winningLotto);

        return lottoResultBundle.getStatistics();
    }
}
