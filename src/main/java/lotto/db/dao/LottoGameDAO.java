package lotto.db.dao;

import lotto.db.dto.LottoGameResultDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lotto.db.DBConnection.getConnection;

public class LottoGameDAO {
    private static final Connection conn = getConnection();

    public static LottoGameResultDTO findLastLottoGame() throws SQLException {
        String query = "SELECT l.id, lg.money, GROUP_CONCAT(ln.number SEPARATOR  ',') as numbers, w.bonusBall " +
                "FROM lotto.lotto as l " +
                "JOIN lotto.winninglotto as w ON w.lotto_id = l.id " +
                "JOIN lotto.lottonumber as ln ON l.id = ln.lotto_id " +
                "JOIN lotto.lottogame as lg ON w.id = lg.winning_id " +
                "WHERE l.type = 1 GROUP BY l.id " +
                "ORDER BY l.id DESC limit 1";
        PreparedStatement pstmt = conn.prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        return new LottoGameResultDTO(
                new LottoGameResultDTO.Builder(rs.getInt("num1"), rs.getInt("num2"), rs.getInt("num3"),
                        rs.getInt("num4"), rs.getInt("num5"), rs.getInt("num6"), rs.getInt("bonusBall"))
        .winningLottoId(rs.getInt("id")).money(rs.getInt("money")));
    }

    /*public static LottoGameResultDTO findByWinningLottoId(String winningLottoId) throws SQLException {
        String query = "SELECT id as week, money FROM lotto.lottogame WHERE winning_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, winningLottoId);

        ResultSet rs = pstmt.executeQuery();

        return getWinningLottoDTO(rs);
    }*/
}
