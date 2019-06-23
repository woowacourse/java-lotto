package lotto.dao.lotto;

import lotto.dao.DBCPDataSource;
import lotto.dto.LottoRoundDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOTTO_ROUND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<LottoRoundDTO> lottoRoundIds = new ArrayList<>();
            while (resultSet.next()) {
                lottoRoundIds.add(new LottoRoundDTO(resultSet.getInt("id")));
            }

            return lottoRoundIds;
        } catch (SQLException e) {
            throw e;
        }
    }

    public int insertLottoRoundReturnsKey() throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOTTO_ROUND, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw e;
        }
    }
}
