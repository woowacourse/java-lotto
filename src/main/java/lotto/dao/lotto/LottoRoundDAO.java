package lotto.dao.lotto;

import lotto.dao.JdbcTemplate.JdbcTemplate;
import lotto.dto.LottoRoundDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static lotto.dao.lotto.sqls.LottoRoundDAOSQLs.INSERT_LOTTO_ROUND;
import static lotto.dao.lotto.sqls.LottoRoundDAOSQLs.SELECT_LOTTO_ROUND_ALL;

public class LottoRoundDAO {
    private static class LottoRoundDAOLazyHolder {
        private static final LottoRoundDAO INSTANCE = new LottoRoundDAO();
    }

    public static LottoRoundDAO getInstance() {
        return LottoRoundDAOLazyHolder.INSTANCE;
    }

    public List<LottoRoundDTO> selectLottoRoundAll() throws SQLException {
        Map<String, Integer> rowMapper = Collections.emptyMap();
        List<LottoRoundDTO> lottoRoundIds = new ArrayList<>();

        JdbcTemplate.query(SELECT_LOTTO_ROUND_ALL, rowMapper, (preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lottoRoundIds.add(new LottoRoundDTO(resultSet.getInt("id")));
            }
        }));

        return lottoRoundIds;
    }

    public Integer insertLottoRoundReturnsKey() throws SQLException {
        Map<String, Object> rowMapper = Collections.emptyMap();
        AtomicInteger lottoRoundId = new AtomicInteger();

        JdbcTemplate.query(INSERT_LOTTO_ROUND, rowMapper, (preparedStatement -> {
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            lottoRoundId.set(resultSet.getInt(1));
        }));

        return lottoRoundId.get();
    }
}
