package lotto.service;

import lotto.dao.UserLottoDAO;
import lottogame.domain.Lotto;
import lottogame.domain.LottoTickets;

import java.util.List;

public class LottoTicketsService {
    public static void insertPurchaseResult(int round, LottoTickets lottoTickets) {
        List<Lotto> lottos = lottoTickets.getLottos();
        for (Lotto lotto : lottos) {
            List<Integer> lottoNumbers = lotto.getNumbers();
            UserLottoDAO.getInstance().insertLotto(round, lottoNumbers);
        }
    }

    public static LottoTickets findTicketsByRound(int round) {
        LottoTickets lottoTickets = new LottoTickets();
        List<String> lottoNumbers = UserLottoDAO.getInstance().findLottosByRound(round);
        for (String lottoNumber : lottoNumbers) {
            lottoTickets.addManualLotto(lottoNumber);
        }
        return lottoTickets;
    }
}
