package lotto.dao;

import lotto.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO {

    public void addWinningLotto(WinningLotto winningLotto, int round) throws SQLException {
        String query = "INSERT INTO winninglotto (numbers,bonus_no,round_no) VALUES (?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        try {
            pstmt.setString(1, winningLotto.getNumbers());
            pstmt.setString(2, winningLotto.getBonusBall().toString());
            pstmt.setInt(3, round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
        }
    }

    public WinningLotto findAllByRound(int round) throws SQLException {
        String query = "SELECT numbers,bonus_no FROM winninglotto WHERE round_no=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);

        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return WinningLottoParser.parseWinningLotto(rs.getString("numbers"), rs.getString("bonus_no"));
        }
        DBConnection.closeConnection(con);
        return null;
    }
}
