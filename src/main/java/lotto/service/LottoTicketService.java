package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import java.sql.SQLException;
import java.util.List;

import static lotto.db.dao.LottoTicketDAO.*;

public class LottoTicketService {
    public static void addLottoTicket(LottoTickets inputLottoTickets) throws SQLException {
        List<LottoTicket> lottoTickets = inputLottoTickets.getLottoTickets();
        for (LottoTicket lottoTicket : lottoTickets) {
            int lotto_id = addLotto(0);
            addLottoNumbers(lotto_id, lottoTicket.getLottoNumbers());
            addLottoGame(lotto_id);
        }
    }
}
