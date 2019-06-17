package lotto.service;

import lotto.dao.DBUtil;
import lotto.dao.LottoHistoryDAO;
import lotto.dao.LottoTicketDAO;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.dto.LottoHistoryDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class LottoHistoryService {
    public static LottoHistoryDTO historyOf(int round) throws SQLException {
        Connection connection = DBUtil.getConnection();
        LottoHistoryDTO lottoHistoryDTO = new LottoHistoryDAO(connection).selectLottoHistory(round);
        LottoTicketGroup lottos = new LottoTicketDAO(connection).selectByLottoRound(round);
        lottoHistoryDTO.setLottos(lottos);

        return lottoHistoryDTO;
    }
}
