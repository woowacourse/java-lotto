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

    public void add(final LottoDto lotto, final int turn) {
        final Connection conn = DBManager.getConnection();
        final String query = "INSERT INTO lottos(numbers, turn) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, stringify(lotto));
            pstmt.setInt(2, turn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    public void addAll(final List<LottoDto> lottos, final int turn) {
        for (LottoDto lotto : lottos) {
            add(lotto, turn);
        }
    }

    private String stringify(final LottoDto dto) {
        return String.join(DELIMITER, dto.getNumbers());
    }

    public List<LottoDto> findAllByTurn(final int turn) {
        Connection conn = DBManager.getConnection();
        List<LottoDto> lottos = new ArrayList<>();
        try {
            String query = "select numbers from lottos where turn = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, turn);
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
