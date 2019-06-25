package lotto.dao.winninglotto;

import lotto.dao.JdbcTemplate.JdbcTemplate;
import lotto.dto.WinningResultDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static lotto.dao.winninglotto.sqls.WinningResultDAOSQLs.INSERT_WINNING_RESULT;
import static lotto.dao.winninglotto.sqls.WinningResultDAOSQLs.SELECT_WINNING_RESULT_BY_LOTTO_ROUND_ID;


public class WinningResultDAO {
    private static class WinningResultDAOLazyHolder {
        private static final WinningResultDAO INSTANCE = new WinningResultDAO();
    }

    public static WinningResultDAO getInstance() {
        return WinningResultDAOLazyHolder.INSTANCE;
    }

    public WinningResultDTO selectWinningResultByLottoRoundId(int lottoRoundId) throws SQLException {
        Map<String, Integer> rowMapper = new HashMap<>();
        AtomicReference<WinningResultDTO> winningResultDTO = new AtomicReference<>();
        rowMapper.put("lottoRoundId", lottoRoundId);
        JdbcTemplate.query(SELECT_WINNING_RESULT_BY_LOTTO_ROUND_ID, rowMapper, (preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            long first = resultSet.getBigDecimal("first").longValue();
            long second = resultSet.getBigDecimal("second").longValue();
            long third = resultSet.getBigDecimal("third").longValue();
            long fourth = resultSet.getBigDecimal("fourth").longValue();
            long fifth = resultSet.getBigDecimal("fifth").longValue();
            long miss = resultSet.getBigDecimal("miss").longValue();
            long roi = resultSet.getBigDecimal("roi").longValue();
            winningResultDTO.set(new WinningResultDTO(lottoRoundId, first, second, third, fourth, fifth, miss, roi));
        }));

        return winningResultDTO.get();
    }

    public void insertWinningResult(WinningResultDTO winningResult) throws SQLException {
        Map<String, Long> rowMapper = new HashMap<>();
        rowMapper.put("lottoRoundId", winningResult.getLottoRoundId());
        rowMapper.put("first", winningResult.getFirst());
        rowMapper.put("second", winningResult.getSecond());
        rowMapper.put("third", winningResult.getThird());
        rowMapper.put("fourth", winningResult.getFourth());
        rowMapper.put("fifth", winningResult.getFifth());
        rowMapper.put("miss", winningResult.getMiss());
        rowMapper.put("roi", winningResult.getRoi());

        JdbcTemplate.query(INSERT_WINNING_RESULT, rowMapper, (PreparedStatement::executeUpdate));
    }
}
