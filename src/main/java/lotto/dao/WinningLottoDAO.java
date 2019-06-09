package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.util.ConvertLottoNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public WinningLotto findByLottoId(String lottoId) throws SQLException {
        String query = "SELECT * FROM winninglotto WHERE lotto_id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        String lastWinningLotto = rs.getString("winning_lotto");
        lastWinningLotto = lastWinningLotto.substring(1, lastWinningLotto.lastIndexOf("]"));
        return new WinningLotto(new Lotto(ConvertLottoNumber.run(lastWinningLotto)),
                LottoNumber.getInstance(Integer.parseInt(rs.getString("bonus_ball"))));
    }
}
