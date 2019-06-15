package lotto.service;

import lotto.dao.LottoTicketDAO;
import lotto.domain.Lotto;
import lotto.domain.LottoTicket;

import java.util.List;

public class LottoTicketService {

    private LottoTicketDAO lottoTicketDAO;

    public static void insertLottos(int round, LottoTicket lottoTicket){
        List<Lotto> lottos = lottoTicket.getLottos();
        lottos.stream().forEach(lotto -> LottoTicketDAO.getInstance().addLotto(round, lotto));
    }

}
