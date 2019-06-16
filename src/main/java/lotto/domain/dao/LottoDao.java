package lotto.domain.dao;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static lotto.domain.dao.JdbcConnector.getConnection;
import static lotto.domain.dao.sqls.Columns.*;
import static lotto.domain.dao.sqls.LottoDaoSqls.*;

public class LottoDao {

    private LottoDao() {
    }

    private static class LottoDaoHolder {
        private static final LottoDao INSTANCE = new LottoDao();
    }

    public static LottoDao getInstance() {
        return LottoDaoHolder.INSTANCE;
    }

    public int insertUser(String name) throws SQLDataException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public int insertLottoTicket(LottoTickets lottoTickets, int round) throws SQLDataException {
        StringBuilder queryBuilder = new StringBuilder(INSERT_LOTTO_TICKET);
        for (int i = 1; i < lottoTickets.getAllLottoTickets().size(); i++) {
            queryBuilder.append(COMMA_AND_THREE_COLUMNS);
        }

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameter = 1;

            for (Lotto lotto : lottoTickets.getAllLottoTickets()) {
                preparedStatement.setString(parameter++, lotto.toString());
                preparedStatement.setBoolean(parameter++, lotto.isAuto());
                preparedStatement.setInt(parameter++, round);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public int insertWinningLotto(WinningLotto winningLotto, int round) throws SQLDataException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WINNING_LOTTO)) {
            preparedStatement.setInt(1, round);
            preparedStatement.setString(2, winningLotto.getWinningLotto().toString());
            preparedStatement.setString(3, winningLotto.getBonusNumber().toString());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public List<Lotto> selectAllLotto(int round) throws SQLDataException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOTTO_BY_ROUND)) {
            preparedStatement.setInt(1, round);

            List<Lotto> lottos = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String lotto = resultSet.getString(LOTTO_COLUMN);
                    boolean isAuto = resultSet.getBoolean(IS_AUTO_COLUMN);

                    lottos.add(convertStringToLotto(lotto, isAuto));
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLDataException();
            }

            return lottos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public WinningLotto selectWinningLotto(int round) throws SQLDataException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WINNING_LOTTO_BY_ROUND)) {
            preparedStatement.setInt(1, round);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new SQLDataException();
                }

                String winningLotto = resultSet.getString(WINNING_LOTTO_COLUMN);
                String bonusBall = resultSet.getString(BONUS_BALL);

                Lotto lotto = convertStringToLotto(winningLotto, false);
                return new WinningLotto(lotto, Integer.parseInt(bonusBall));
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLDataException();
            }
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

}
