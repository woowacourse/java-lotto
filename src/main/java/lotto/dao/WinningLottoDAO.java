package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;

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

    public void addWinningLotto(String lottoId, WinningLotto winningLotto) throws SQLException {
        String query = "INSERT INTO winninglotto VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoId);
        pstmt.setString(2, winningLotto.getWinningLotto().toString());
        pstmt.setString(3, winningLotto.getBonusNumber().toString());
        pstmt.executeUpdate();
    }
}
