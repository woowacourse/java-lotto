package lotto.dao.winninglotto;

import lotto.dao.JdbcTemplate.JdbcTemplate;
import lotto.dto.WinningLottoDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static lotto.dao.winninglotto.sqls.WinningLottoDAOSQLs.INSERT_WINNING_LOTTO;
import static lotto.dao.winninglotto.sqls.WinningLottoDAOSQLs.SELECT_WINNING_LOTTO_BY_LOTTO_ROUND_ID;

public class WinningLottoDAO {
    private static class WinningLottoDAOLazyHolder {
        private static final WinningLottoDAO INSTANCE = new WinningLottoDAO();
    }

    public static WinningLottoDAO getInstance() {
        return WinningLottoDAOLazyHolder.INSTANCE;
    }

    public WinningLottoDTO selectWinningLottoByLottoRoundId(int lottoRoundId) throws SQLException {
        Map<String, Integer> rowMapper = Collections.singletonMap("lottoRoundId", lottoRoundId);
        AtomicReference<WinningLottoDTO> winningLottoDTO = new AtomicReference<>();

        JdbcTemplate.query(SELECT_WINNING_LOTTO_BY_LOTTO_ROUND_ID, rowMapper, preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            String lottoTicket = resultSet.getString("lotto_ticket");
            int bonusNumber = resultSet.getInt("bonus_number");
            winningLottoDTO.set(new WinningLottoDTO(lottoTicket, bonusNumber));
        });

        return winningLottoDTO.get();
    }

    public void insertWinningLotto(WinningLottoDTO winningLotto, int lottoRoundId) throws SQLException {
        Map<String, Object> rowMapper = new HashMap<>();
        rowMapper.put("lottoRoundId", lottoRoundId);
        rowMapper.put("winningLotto", winningLotto.getWinningLotto());
        rowMapper.put("bonusNumber", winningLotto.getBonusNumber());

        JdbcTemplate.query(INSERT_WINNING_LOTTO, rowMapper, (PreparedStatement::executeUpdate));
    }
}
