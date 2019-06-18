package lotto.domain.dao;

import lotto.domain.dao.utils.DaoTemplate;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static lotto.domain.dao.sqls.Columns.*;
import static lotto.domain.dao.sqls.LottoDaoSqls.*;
import static lotto.domain.dao.utils.JdbcConnector.getConnection;

public class LottoDao {

    private LottoDao() {
    }

    private static class LottoDaoHolder {
        private static final LottoDao INSTANCE = new LottoDao();
    }

    public static LottoDao getInstance() {
        return LottoDaoHolder.INSTANCE;
    }

    public int insertLottoTicket(LottoTickets lottoTickets, int round) throws SQLDataException {
        StringBuilder queryBuilder = buildInsertLottoQuery(lottoTickets.getAllLottoTickets().size());

        DaoTemplate daoTemplate = (preparedStatement) -> {
            int index = 1;
            for (Lotto lotto : lottoTickets.getAllLottoTickets()) {
                preparedStatement.setString(index++, lotto.toString());
                preparedStatement.setBoolean(index++, lotto.getIsAuto());
                preparedStatement.setInt(index++, round);
            }
        };

        return daoTemplate.cudTemplate(queryBuilder.toString());
    }

    private StringBuilder buildInsertLottoQuery(int size) {
        StringBuilder queryBuilder = new StringBuilder(INSERT_LOTTO_TICKET);
        for (int i = 1; i < size; i++) {
            queryBuilder.append(COMMA_AND_THREE_COLUMNS);
        }
        return queryBuilder;
    }

    public int insertWinningLotto(WinningLotto winningLotto, int round) throws SQLDataException {
        DaoTemplate daoTemplate = (preparedStatement) -> {
            preparedStatement.setInt(1, round);
            preparedStatement.setString(2, winningLotto.getWinningLotto().toString());
            preparedStatement.setString(3, winningLotto.getBonusNumber().toString());
        };
        return daoTemplate.cudTemplate(INSERT_WINNING_LOTTO);
    }

    public List<Lotto> selectAllLotto(int round) throws SQLDataException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, round, SELECT_ALL_LOTTO_BY_ROUND);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Lotto> lottos = new ArrayList<>();
            while (resultSet.next()) {
                String lotto = resultSet.getString(LOTTO_COLUMN);
                boolean isAuto = resultSet.getBoolean(IS_AUTO_COLUMN);

                lottos.add(convertStringToLotto(lotto, isAuto));
            }

            return lottos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public WinningLotto selectWinningLotto(int round) throws SQLDataException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, round, SELECT_WINNING_LOTTO_BY_ROUND);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (!resultSet.next()) {
                throw new SQLDataException();
            }

            String winningLotto = resultSet.getString(WINNING_LOTTO_COLUMN);
            String bonusBall = resultSet.getString(BONUS_BALL);

            Lotto lotto = convertStringToLotto(winningLotto, false);
            return new WinningLotto(lotto, Integer.parseInt(bonusBall));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    private Lotto convertStringToLotto(String winningLotto, boolean isAuto) {
        return new Lotto(Arrays.stream(winningLotto.split(","))
                .map(Integer::parseInt)
                .collect(toList()), isAuto);
    }

    private PreparedStatement createPreparedStatement(Connection connection, int round, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        return preparedStatement;
    }
}
