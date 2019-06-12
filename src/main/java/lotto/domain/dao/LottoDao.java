package lotto.domain.dao;

import lotto.domain.dto.ResultDTO;
import lotto.domain.lotto.Rank;
import lotto.domain.lotto.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static lotto.domain.dao.JdbcConnector.getConnection;
import static lotto.domain.dao.sqls.LottoDaoSqls.SELECT_ALL_LOTTO_RESULT;

public class LottoDao {

    private LottoDao() {
        throw new AssertionError();
    }

    private static class LottoDaoHolder {
        private static final LottoDao INSTANCE = new LottoDao();
    }

    public static LottoDao getInstance() {
        return LottoDaoHolder.INSTANCE;
    }

    public List<ResultDTO> selectAllLottoGame() throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_LOTTO_RESULT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<ResultDTO> results = new ArrayList<>();
            while (resultSet.next()) {
                ResultDTO resultDTO = new ResultDTO.Builder(
                        resultSet.getInt("id"), resultSet.getString("name"))
                        .first(resultSet.getInt("first"))
                        .second(resultSet.getInt("second"))
                        .third(resultSet.getInt("third"))
                        .fourth(resultSet.getInt("fourth"))
                        .fifth(resultSet.getInt("fifth"))
                        .miss(resultSet.getInt("miss"))
                        .build();
                results.add(resultDTO);
            }
            return results;
        } catch (Exception e) {
            throw new SQLDataException();
        }
    }
}
