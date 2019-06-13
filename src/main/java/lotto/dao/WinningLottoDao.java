package lotto.dao;

import lotto.dto.WinningLottoDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDao {
    private Connection con;

    public WinningLottoDao(Connection con) {
        this.con = con;
    }

    public void addWinningLotto(WinningLottoDto lottoDto) throws SQLException {
        String query = "INSERT INTO 당첨로또 (당첨번호, 보너스번호) VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoDto.getLottoNo());
        pstmt.setString(2, lottoDto.getBonusNo());
        pstmt.executeUpdate();
    }

    public int findRoundNo() throws SQLException {
        String query = "SELECT 회차 FROM 당첨로또 ORDER BY 회차 DESC LIMIT 1";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return 0;
        return rs.getInt("회차");
    }
}
