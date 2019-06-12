package lotto.domain.dao;

import lotto.domain.dto.ResultDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.domain.dao.JdbcConnector.getConnection;
import static lotto.domain.dao.sqls.Columns.*;
import static lotto.domain.dao.sqls.LottoDaoSqls.SELECT_ALL_LOTTO_RESULT;

public class LottoDao {
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
                        resultSet.getInt(ID), resultSet.getString(NAME))
                        .first(resultSet.getInt(FIRST))
                        .second(resultSet.getInt(SECOND))
                        .third(resultSet.getInt(THIRD))
                        .fourth(resultSet.getInt(FOURTH))
                        .fifth(resultSet.getInt(FIFTH))
                        .miss(resultSet.getInt(MISS))
                        .build();
                results.add(resultDTO);
            }
            return results;
        } catch (Exception e) {
            throw new SQLDataException();
        }
    }
}
