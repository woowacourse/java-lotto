package lotto.service;

import lotto.dao.DBConnection;
import lotto.dao.JdbcTemplate;
import lotto.dao.LottoHistoryDAO;
import lotto.dao.LottoTicketDAO;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.dto.LottoHistoryDTO;

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
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance(DBConnection.getConnection());
        LottoHistoryDTO lottoHistoryDTO = LottoHistoryDAO.getInstance(jdbcTemplate).selectLottoHistory(round);
        LottoTicketGroup lottos = LottoTicketDAO.getInstance(jdbcTemplate).selectByLottoRound(round);
        lottoHistoryDTO.setLottos(lottos);

        return lottoHistoryDTO;
    }
}
