package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDAO {
    private final Connection con;

    public WinningLottoDAO(Connection connection) {
        this.con = connection;
    }

    public void addWinningLotto(String lottoId, Lotto winningLotto, LottoNumber bonusBall) throws SQLException {
        String query = "INSERT INTO winninglotto VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoId);
        pstmt.setString(2, winningLotto.toString());
        pstmt.setString(3, bonusBall.toString());
        pstmt.executeUpdate();
    }
}
