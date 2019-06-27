package lotto;

import lotto.controller.LottoGame;
import lotto.model.Lotto;
import lotto.model.Rank;
import lotto.model.WinningLotto;

import java.sql.*;
import java.util.*;

class LottoDAO {
    private static final String INIT_SQL_TABLE_WIN_LOTTO;
    private static final String INIT_SQL_TABLE_RESULT;
    private static final String INIT_SQL_TABLE_LOTTO;
    private static final String SQL_INSERT_FAILED;
    private static final Map<Rank, Integer> RANK_TO_SQL_ORDER;

    static {
        INIT_SQL_TABLE_WIN_LOTTO =
                "CREATE TABLE IF NOT EXISTS `win_lotto` (\n" +
                "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                "`first` INT NOT NULL,\n" +
                "`second` INT NOT NULL,\n" +
                "`third` INT NOT NULL,\n" +
                "`fourth` INT NOT NULL,\n" +
                "`fifth` INT NOT NULL,\n" +
                "`sixth` INT NOT NULL,\n" +
                "`bonus` INT NOT NULL,\n" +
                "PRIMARY KEY (`id`));";
        INIT_SQL_TABLE_RESULT =
                "CREATE TABLE IF NOT EXISTS `result` (\n" +
                "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                "`win_lotto` INT NOT NULL,\n" +
                "`amount` INT NOT NULL,\n" +
                "`auto_count` INT NOT NULL,\n" +
                "`manual_count` INT NOT NULL,\n" +
                "`rank1st` INT,\n" +
                "`rank2nd` INT,\n" +
                "`rank3rd` INT,\n" +
                "`rank4th` INT,\n" +
                "`rank5th` INT,\n" +
                "`rankmiss` INT,\n" +
                "`total_prize` INT NOT NULL,\n" +
                "`profit_rate` FLOAT NOT NULL,\n" +
                "PRIMARY KEY (`id`),\n" +
                "FOREIGN KEY (`win_lotto`)\n" +
                "REFERENCES `win_lotto` (`id`)\n" +
                "ON DELETE CASCADE);";
        INIT_SQL_TABLE_LOTTO =
                "CREATE TABLE IF NOT EXISTS `lotto` (\n" +
                "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                "`result` INT NOT NULL,\n" +
                "`first` INT NOT NULL,\n" +
                "`second` INT NOT NULL,\n" +
                "`third` INT NOT NULL,\n" +
                "`fourth` INT NOT NULL,\n" +
                "`fifth` INT NOT NULL,\n" +
                "`sixth` INT NOT NULL,\n" +
                "PRIMARY KEY (`id`),\n" +
                "FOREIGN KEY (`result`)\n" +
                "REFERENCES `result` (`id`)\n" +
                "ON DELETE CASCADE);";
        SQL_INSERT_FAILED = "DB에 데이터를 집어넣는 데 실패했습니다.";
        RANK_TO_SQL_ORDER = new HashMap<>();
        RANK_TO_SQL_ORDER.put(Rank.FIRST, 5);
        RANK_TO_SQL_ORDER.put(Rank.SECOND, 6);
        RANK_TO_SQL_ORDER.put(Rank.THIRD, 7);
        RANK_TO_SQL_ORDER.put(Rank.FOURTH, 8);
        RANK_TO_SQL_ORDER.put(Rank.FIFTH, 9);
        RANK_TO_SQL_ORDER.put(Rank.MISS, 10);
    }

    private Connection connection;

    LottoDAO(final DBConnection connection) {
        try {
            this.connection = connection.getConnection();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void initSqlTable() throws SQLException {
        connection.prepareStatement(INIT_SQL_TABLE_WIN_LOTTO).executeUpdate();
        connection.prepareStatement(INIT_SQL_TABLE_RESULT).executeUpdate();
        connection.prepareStatement(INIT_SQL_TABLE_LOTTO).executeUpdate();
    }

    void newLottos(final LottoGame game) throws SQLException {
        initSqlTable();
        final int winLottoID = addWinLotto(game.getWinLotto());
        final int resultID = addLottoResult(game, winLottoID);
        for (Lotto lotto : game.getLottos()) {
            addLotto(lotto, resultID);
        }
    }

    private int addWinLotto(final WinningLotto winningLotto) throws SQLException {
        final List<Integer> lottoNumbers = winningLotto.getLotto().getNumbers();
        final String query = "INSERT INTO win_lotto"
                + " (first, second, third, fourth, fifth, sixth, bonus)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        final PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < lottoNumbers.size(); i++) {
            statement.setInt(i + 1, lottoNumbers.get(i));
        }
        statement.setInt(7, winningLotto.getBonusNo());
        final int affectedRows = statement.executeUpdate(); // https://stackoverflow.com/a/1915197
        if (affectedRows == 0) {
            throw new SQLException(SQL_INSERT_FAILED);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }
        throw new SQLException(SQL_INSERT_FAILED);
    }

    private int addLottoResult(final LottoGame game, final int winLottoID) throws SQLException {
        final Iterator<Map.Entry<Rank, Integer>> iterator = game.getStat().iterator();
        final String query = "INSERT INTO result"
                + " (win_lotto, amount, auto_count, manual_count, rank1st, rank2nd, rank3rd, rank4th, rank5th, rankmiss, total_prize, profit_rate)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, winLottoID);
        statement.setInt(2, game.getAllPurchaseCount() * game.getPrice());
        statement.setInt(3, game.getAutoPurchaseCount());
        statement.setInt(4, game.getManualPurchaseCount());
        statement.setInt(11, game.getStat().getTotalPrizeMoney());
        statement.setFloat(12, (float) game.getStat().getProfitRate());
        while (iterator.hasNext()) {
                Map.Entry<Rank, Integer> entry = iterator.next();
                Rank key = entry.getKey();
                int value = entry.getValue();
                statement.setInt(RANK_TO_SQL_ORDER.get(key), value);
        }
        final int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException(SQL_INSERT_FAILED);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }
        throw new SQLException(SQL_INSERT_FAILED);
    }

    private void addLotto(final Lotto lotto, final int resultID) throws SQLException {
        final String query = "INSERT INTO lotto"
                + " (result, first, second, third, fourth, fifth, sixth)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        final PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, resultID);
        for (int i = 0; i < lotto.getNumbers().size(); i++) {
            statement.setInt(i + 2, lotto.getNumbers().get(i));
        }
        final int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException(SQL_INSERT_FAILED);
        }
    }

    List<PreviousWinLottoResultDTO> getPreviousResults() throws SQLException {
        initSqlTable();
        final List<PreviousWinLottoResultDTO> result = new ArrayList<>();
        final String query = "SELECT * FROM win_lotto";
        final PreparedStatement statement = connection.prepareStatement(query);
        final ResultSet set = statement.executeQuery();
        while (set.next()) {
            final int winLottoID = set.getInt("id");
            final PreviousWinLottoResultDTO winLottoResult = new PreviousWinLottoResultDTO()
                    .setWinLottoID(winLottoID)
                    .setWinLottoBonus(set.getInt("bonus"))
                    .setFirst(set.getInt("first"))
                    .setSecond(set.getInt("second"))
                    .setThird(set.getInt("third"))
                    .setFourth(set.getInt("fourth"))
                    .setFifth(set.getInt("fifth"))
                    .setSixth(set.getInt("sixth"))
                    .setPreviousPurchases(getPreviousPurchases(winLottoID));
            result.add(winLottoResult);
        }
        return result;
    }

    private List<PreviousPurchaseDTO> getPreviousPurchases(final int winLottoID) throws SQLException {
        final List<PreviousPurchaseDTO> result = new ArrayList<>();
        final String query = "SELECT * FROM result WHERE win_lotto = ?";
        final PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, winLottoID);
        final ResultSet set = statement.executeQuery();
        while (set.next()) {
            final int resultID = set.getInt("id");
            final List<PreviousLottoDTO> lottos = getLottos(resultID);
            final PreviousPurchaseDTO purchase = new PreviousPurchaseDTO()
                    .setAmount(set.getInt("amount"))
                    .setAutoCount(set.getInt("auto_count"))
                    .setManualCount(set.getInt("manual_count"))
                    .setTotalPrize(set.getInt("total_prize"))
                    .setProfitRate(set.getFloat("profit_rate"))
                    .setRank1st(set.getInt("rank1st"))
                    .setRank2nd(set.getInt("rank2nd"))
                    .setRank3rd(set.getInt("rank3rd"))
                    .setRank4th(set.getInt("rank4th"))
                    .setRank5th(set.getInt("rank5th"))
                    .setRankMiss(set.getInt("rankmiss"))
                    .setLottoList(lottos);
            result.add(purchase);
        }
        return result;
    }

    private List<PreviousLottoDTO> getLottos(final int resultID) throws SQLException {
        final List<PreviousLottoDTO> result = new ArrayList<>();
        final String query = "SELECT * FROM lotto WHERE result = ?";
        final PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, resultID);
        final ResultSet set = statement.executeQuery();
        while (set.next()) {
            final PreviousLottoDTO lottoNums = new PreviousLottoDTO();
            int first = set.getInt("first");
            lottoNums.addNumber(set.getInt("first"));
            lottoNums.addNumber(set.getInt("second"));
            lottoNums.addNumber(set.getInt("third"));
            lottoNums.addNumber(set.getInt("fourth"));
            lottoNums.addNumber(set.getInt("fifth"));
            lottoNums.addNumber(set.getInt("sixth"));
            result.add(lottoNums);
        }
        return result;
    }
}
