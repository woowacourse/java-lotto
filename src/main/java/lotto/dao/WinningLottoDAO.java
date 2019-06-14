package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.dto.WinningLottoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDAO {
    private final Connection conn;

    public WinningLottoDAO(Connection conn) {
        this.conn = conn;
    }

    public void addWinningLotto(WinningLottoDTO winningLottoDto) throws SQLException {
        String query = "INSERT INTO winninglotto VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, winningLottoDto.getRound());
        pstmt.setInt(2, winningLottoDto.getNumber1());
        pstmt.setInt(3, winningLottoDto.getNumber2());
        pstmt.setInt(4, winningLottoDto.getNumber3());
        pstmt.setInt(5, winningLottoDto.getNumber4());
        pstmt.setInt(6, winningLottoDto.getNumber5());
        pstmt.setInt(7, winningLottoDto.getNumber6());
        pstmt.setInt(8, winningLottoDto.getBonus());
        pstmt.executeUpdate();
    }

    public WinningLotto findByRound(int round) throws SQLException {
        String query = "SELECT * FROM winninglotto WHERE round = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return makeWinningLotto(rs);
    }

    private WinningLotto makeWinningLotto(ResultSet rs) throws SQLException {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumber(rs.getInt("number_1")));
        lottoNumbers.add(new LottoNumber(rs.getInt("number_2")));
        lottoNumbers.add(new LottoNumber(rs.getInt("number_3")));
        lottoNumbers.add(new LottoNumber(rs.getInt("number_4")));
        lottoNumbers.add(new LottoNumber(rs.getInt("number_5")));
        lottoNumbers.add(new LottoNumber(rs.getInt("number_6")));
        Lotto lotto = new Lotto(lottoNumbers);

        LottoNumber bonusBall = new LottoNumber(rs.getInt("bonus"));
        return new WinningLotto(lotto, bonusBall);
    }
}
