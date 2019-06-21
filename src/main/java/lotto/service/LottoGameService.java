package lotto.service;

import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;

import java.sql.SQLException;
import java.util.List;

import static lotto.db.dao.LottoGameDAO.*;
import static lotto.db.dao.LottoTicketDAO.addLotto;
import static lotto.db.dao.LottoTicketDAO.addLottoNumbers;

public class LottoGameService {
    public static void addWinningLottoTicket(String lottoNumbers, String bonusBall) throws SQLException {
        WinningLotto winningLotto = WinningLotto.of(lottoNumbers, Integer.parseInt(bonusBall));
        List<LottoNumber> parsed_lottoNumbers = winningLotto.getWinningNumbers().getLottoNumbers();

        int lotto_id = addLotto(1);
        addLottoNumbers(lotto_id, parsed_lottoNumbers);
        int winning_id = addWinningLotto(winningLotto, lotto_id);
        addWinningLottoIdIntoLottoGame(winning_id);
    }
}
