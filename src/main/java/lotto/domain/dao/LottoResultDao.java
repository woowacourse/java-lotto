package lotto.domain.dao;

import lotto.domain.dto.ResultDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import static lotto.domain.dao.JdbcConnector.getConnection;
import static lotto.domain.dao.sqls.Columns.*;
import static lotto.domain.dao.sqls.LottoDaoSqls.INSERT_LOTTO_RESULT;
import static lotto.domain.dao.sqls.LottoResultDaoSqls.SELECT_ALL_LOTTO_RESULT;
import static lotto.domain.dao.sqls.LottoResultDaoSqls.SELECT_LOTTO_RESULT_BY_ROUND;
import static lotto.domain.lotto.Rank.*;

public class LottoResultDao {

    private LottoResultDao(){}

    private static class LottoResultDaoHolder{
        private static final LottoResultDao INSTANCE = new LottoResultDao();
    }

    public static LottoResultDao getInstance() {
        return LottoResultDaoHolder.INSTANCE;
    }

    public int insertLottoResult(ResultDTO resultDTO) throws SQLDataException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_LOTTO_RESULT)) {
            preparedStatement.setLong(1, resultDTO.get(FIRST));
            preparedStatement.setLong(2, resultDTO.get(SECOND));
            preparedStatement.setLong(3, resultDTO.get(THIRD));
            preparedStatement.setLong(4, resultDTO.get(FOURTH));
            preparedStatement.setLong(5, resultDTO.get(FIFTH));
            preparedStatement.setLong(6, resultDTO.get(MISS));
            preparedStatement.setLong(7, resultDTO.getRound());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public List<ResultDTO> selectAllLottoResult() throws SQLDataException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_LOTTO_RESULT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<ResultDTO> results = new ArrayList<>();
            while (resultSet.next()) {
                ResultDTO resultDTO = new ResultDTO.Builder(
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new SQLDataException();
        }
    }

    public ResultDTO selectLottoResult(int round) throws SQLDataException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_LOTTO_RESULT_BY_ROUND)) {
            preparedStatement.setInt(1, round);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new IllegalArgumentException("존재하지 않는 round입니다.");
                }
                return new ResultDTO.Builder(
                        resultSet.getInt(ROUND_COLUMN), resultSet.getString(NAME_COLUMN))
                        .first(resultSet.getInt(FIRST_COLUMN))
                        .second(resultSet.getInt(SECOND_COLUMN))
                        .third(resultSet.getInt(THIRD_COLUMN))
                        .fourth(resultSet.getInt(FOURTH_COLUMN))
                        .fifth(resultSet.getInt(FIFTH_COLUMN))
                        .miss(resultSet.getInt(MISS_COLUMN))
                        .payment(resultSet.getInt(PAYMENT_COLUMN))
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLDataException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }
}
