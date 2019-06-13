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
}
