package lotto.service;

import lotto.dao.DBUtil;
import lotto.dao.LottoHistoryDAO;
import lotto.dao.LottoTicketDAO;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.dto.LottoHistoryDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class LottoHistoryService {
    private static LottoHistoryService instance;

    private LottoHistoryService() {
    }

    public static LottoHistoryService getInstance() {
        if (instance == null) {
            instance = new LottoHistoryService();
        }
        return instance;
    }

    public LottoHistoryDTO historyOf(int round) throws SQLException {
        Connection connection = DBUtil.getConnection();
        LottoHistoryDTO lottoHistoryDTO = LottoHistoryDAO.getInstance(connection).selectLottoHistory(round);
        LottoTicketGroup lottos = LottoTicketDAO.getInstance(connection).selectByLottoRound(round);
        lottoHistoryDTO.setLottos(lottos);

        return lottoHistoryDTO;
    }
}
