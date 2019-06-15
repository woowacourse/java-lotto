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

    public WinningLottoDto getWinningLotto(int round) throws SQLException {
        String query = "SELECT * FROM 당첨로또 WHERE 회차 = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            throw new SQLException("해당하는 회차의 당첨로또가 존재하지 않습니다.");
        return new WinningLottoDto(rs.getInt(1),
                rs.getString(2), rs.getString(3));
    }
}
