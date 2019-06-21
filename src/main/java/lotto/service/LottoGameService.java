package lotto.service;

import lotto.db.dao.LottoGameDAO;
import lotto.db.dao.LottoTicketDAO;
import lotto.domain.*;
import lotto.domain.dto.LottoGameResultDTO;

import java.sql.SQLException;
import java.util.List;

import static lotto.db.dao.LottoGameDAO.*;
import static lotto.db.dao.LottoTicketDAO.*;

public class LottoGameService {
    private static final int TYPE_OF_NORMAL_LOTTO = 0;
    private static final int TYPE_OF_WINNING_LOTTO = 1;

    public static void addLottoTicket(LottoTickets inputLottoTickets) throws SQLException {
        List<LottoTicket> lottoTickets = inputLottoTickets.getLottoTickets();
        for (LottoTicket lottoTicket : lottoTickets) {
            int lotto_id = addLotto(TYPE_OF_NORMAL_LOTTO);
            addLottoNumbers(lotto_id, lottoTicket.getLottoNumbers());
            addLottoGame(lotto_id);
        }
    }

    public static void addWinningLottoTicket(String lottoNumbers, String bonusBall) throws SQLException {
        WinningLotto winningLotto = WinningLotto.of(lottoNumbers, Integer.parseInt(bonusBall));
        List<LottoNumber> parsed_lottoNumbers = winningLotto.getWinningNumbers().getLottoNumbers();

        int lotto_id = addLotto(TYPE_OF_WINNING_LOTTO);
        addLottoNumbers(lotto_id, parsed_lottoNumbers);
        int winning_id = addWinningLotto(winningLotto, lotto_id);
        addWinningLottoIdIntoLottoGame(winning_id);
    }

    public static WinStatistics findLatestLottoGameResult() throws SQLException {
        LottoGameResultDTO winningLotto = LottoGameDAO.findLatestWinningLotto();
        List<LottoTicket> lottoTickets = LottoTicketDAO.findLottosByLottoId(winningLotto.getWinningLottoId());

        return new WinStatistics(lottoTickets, WinningLotto.of(winningLotto.getWinningNumbers(), winningLotto.getBonusBall()));
    }

}
