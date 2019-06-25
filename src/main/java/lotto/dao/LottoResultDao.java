package lotto.dao;

import lotto.domain.Rank;
import lotto.domain.exceptions.LottoException;
import lotto.dto.LottoResultDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoResultDao {
    private static LottoResultDao dao;

    private LottoResultDao() {

    }

    public static LottoResultDao getDao() {
        if (dao == null) {
            dao = new LottoResultDao();
            return dao;
        }
        return dao;
    }

    public void insertResult(int round, LottoResultDto dto) {
        Connection conn = DBManager.getConnection();
        String sql = "INSERT INTO result(first,second,third,fourth,fifth,lose,round_id) " +
                "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            setStatement(stmt, dto.getResults());
            stmt.setInt(7, round);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertResult(LottoResultDto dto) {
        insertResult(DBManager.lastRound(), dto);
    }

    private void setStatement(PreparedStatement stmt, Map<Rank, Integer> results) throws SQLException {
        List<Integer> resultCount = new ArrayList<>(results.values());
        for (int i = 1; i < 7; i++) {
            stmt.setInt(i, resultCount.get(i - 1));
        }
    }

    public List<Rank> currentResult() {
        return selectResult(DBManager.lastRound());
    }

    public List<Rank> selectResult(int round) {
        Connection conn = DBManager.getConnection();
        String sql = "SELECT first, second, third, fourth, fifth, lose FROM result where round_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, round);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return iterateResultSet(rs);
        } catch (SQLException e) {
            throw new LottoException();
        }
    }

    private List<Rank> iterateResultSet(ResultSet rs) throws SQLException {
        List<Rank> ranks = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            ranks.addAll(rankByColumn(i - 1, rs.getInt(i)));
        }
        return ranks;
    }

    private List<Rank> rankByColumn(int columnIndex, int count) {
        boolean bonus = false;
        if (columnIndex == 1) {
            bonus = true;
        }
        return Stream.of(Rank.rank(6 - columnIndex, bonus)).limit(count).collect(Collectors.toList());
    }
}
