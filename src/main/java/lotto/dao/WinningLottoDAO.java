package lotto.dao;

import lotto.dto.WinningLottoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static lotto.db.DBConnection.getConnection;

public class WinningLottoDAO {
    public WinningLottoDTO findByWinningLottoId(String winningLottoId) throws SQLException {
        String query = "SELECT w.id, w.bonusBall, l.number " +
                        "FROM lotto.winninglotto as w " +
                        "JOIN (SELECT lotto.id as lotto_id, lotto.type, ln.number " +
                                "FROM lotto.lotto " +
                                "JOIN lotto.lottonumber as ln ON lotto.id = ln.lotto_id) as l " +
                        "ON l.lotto_id = w.lotto_id " +
                        "WHERE w.id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, winningLottoId);
        ResultSet rs = pstmt.executeQuery();

        return getWinningLottoDTO(rs);
    }

    private WinningLottoDTO getWinningLottoDTO(ResultSet rs) throws SQLException {
        int id = 1, bonusBall = 1;
        List<Integer> numbers = new ArrayList<>();

        while (rs.next()) {
            id = rs.getInt("id");
            bonusBall = rs.getInt("bonusBall");
            numbers.add(rs.getInt("number"));
        }

        try {
            return new WinningLottoDTO(
                    id, bonusBall, numbers.get(0),numbers.get(1),numbers.get(2),numbers.get(3),numbers.get(4),numbers.get(5));
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
