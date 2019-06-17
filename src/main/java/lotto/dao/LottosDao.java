package lotto.dao;

import lotto.dto.LottoDto;
import lotto.util.LottoParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottosDao {
    private static final String DELIMITER = ",";
    private static final LottosDao INSTANCE = new LottosDao();

    private LottosDao() {

    }

    public static LottosDao getInstance() {
        return INSTANCE;
    }

    public void add(final LottoDto lotto, final int round) {
        final Connection conn = DBManager.getConnection();
        final String query = "INSERT INTO lottos(numbers, round) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, stringify(lotto));
            pstmt.setInt(2, round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    public void addAll(final List<LottoDto> lottos, final int round) {
        for (LottoDto lotto : lottos) {
            add(lotto, round);
        }
    }

    private String stringify(final LottoDto dto) {
        return String.join(DELIMITER, dto.getNumbers());
    }

    public List<LottoDto> findAllByRound(final int round) {
        Connection conn = DBManager.getConnection();
        List<LottoDto> lottos = new ArrayList<>();
        try {
            String query = "select numbers from lottos where round = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LottoDto dto = new LottoParser().parseLottoDto(rs.getString(1));
                lottos.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
            return lottos;
        }
    }

    public void deleteAll() {
        Connection conn = DBManager.getConnection();
        String query = "DELETE FROM lottos";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }
}
