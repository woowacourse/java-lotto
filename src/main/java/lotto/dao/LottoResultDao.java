package lotto.dao;

import lotto.dao.exception.DataAccessException;
import lotto.dao.utils.DaoTemplate;
import lotto.service.dto.ResultDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.dao.sqls.Columns.*;
import static lotto.dao.sqls.LottoDaoSqls.INSERT_LOTTO_RESULT;
import static lotto.dao.sqls.LottoResultDaoSqls.SELECT_ALL_LOTTO_RESULT;
import static lotto.dao.sqls.LottoResultDaoSqls.SELECT_LOTTO_RESULT_BY_ROUND;
import static lotto.dao.utils.JdbcConnector.getConnection;
import static lotto.domain.lotto.Rank.*;

public class LottoResultDao {

    private LottoResultDao() {
    }

    private static class LottoResultDaoHolder {
        private static final LottoResultDao INSTANCE = new LottoResultDao();
    }

    public static LottoResultDao getInstance() {
        return LottoResultDaoHolder.INSTANCE;
    }

    public int insertLottoResult(ResultDto resultDto) {
        DaoTemplate daoTemplate = (preparedStatement) -> {
            preparedStatement.setLong(1, resultDto.get(FIRST));
            preparedStatement.setLong(2, resultDto.get(SECOND));
            preparedStatement.setLong(3, resultDto.get(THIRD));
            preparedStatement.setLong(4, resultDto.get(FOURTH));
            preparedStatement.setLong(5, resultDto.get(FIFTH));
            preparedStatement.setLong(6, resultDto.get(MISS));
            preparedStatement.setLong(7, resultDto.getRound());
        };
        return daoTemplate.cudTemplate(INSERT_LOTTO_RESULT);
    }

    public List<ResultDto> selectAllLottoResult() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOTTO_RESULT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<ResultDto> results = new ArrayList<>();
            while (resultSet.next()) {
                ResultDto resultDTO = new ResultDto.Builder(
                        resultSet.getInt(ROUND_COLUMN), resultSet.getString(NAME_COLUMN))
                        .first(resultSet.getInt(FIRST_COLUMN))
                        .second(resultSet.getInt(SECOND_COLUMN))
                        .third(resultSet.getInt(THIRD_COLUMN))
                        .fourth(resultSet.getInt(FOURTH_COLUMN))
                        .fifth(resultSet.getInt(FIFTH_COLUMN))
                        .miss(resultSet.getInt(MISS_COLUMN))
                        .payment(resultSet.getInt(PAYMENT_COLUMN))
                        .build();
                results.add(resultDTO);
            }
            return results;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public ResultDto selectLottoResult(int round) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, round);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.next()) {
                throw new IllegalArgumentException("존재하지 않는 round입니다.");
            }
            return new ResultDto.Builder(
                    resultSet.getInt(ROUND_COLUMN), resultSet.getString(NAME_COLUMN))
                    .first(resultSet.getInt(FIRST_COLUMN))
                    .second(resultSet.getInt(SECOND_COLUMN))
                    .third(resultSet.getInt(THIRD_COLUMN))
                    .fourth(resultSet.getInt(FOURTH_COLUMN))
                    .fifth(resultSet.getInt(FIFTH_COLUMN))
                    .miss(resultSet.getInt(MISS_COLUMN))
                    .payment(resultSet.getInt(PAYMENT_COLUMN))
                    .build();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection, int round) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOTTO_RESULT_BY_ROUND);
        preparedStatement.setInt(1, round);
        return preparedStatement;
    }
}
