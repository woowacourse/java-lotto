package lotto.dao;

import lotto.dto.WinningLottoDTO;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static lotto.db.DBConnection.getConnection;

public class WinningLottoDAO {
    public WinningLottoDTO findByWinningLottoId(String winningLottoId) throws SQLException {
        String query = "SELECT l.id, l.type, GROUP_CONCAT(ln.number SEPARATOR ',') as numbers, w.bonusBall " +
                "FROM lotto.lotto as l " +
                "JOIN lotto.winninglotto as w ON w.lotto_id = l.id " +
                "JOIN lotto.lottonumber as ln ON l.id = ln.lotto_id " +
                "WHERE w.id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, winningLottoId);
        ResultSet rs = pstmt.executeQuery();

        return getWinningLottoDTO(rs);
    }

    private WinningLottoDTO getWinningLottoDTO(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        try {
            int id = rs.getInt("id");
            int bonusBall = rs.getInt("bonusBall");
            List<String> numbers = Arrays.asList(rs.getString("numbers").split(","));

            // TODO 나중에 빌더 패턴으로 바꿔보기
            return new WinningLottoDTO(id, bonusBall,
                    Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)), Integer.parseInt(numbers.get(2)),
                    Integer.parseInt(numbers.get(3)), Integer.parseInt(numbers.get(4)), Integer.parseInt(numbers.get(5)));
        } catch (Exception e) {
            return null;
        }
    }
}
