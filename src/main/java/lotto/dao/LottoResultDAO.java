package lotto.dao;

import lotto.domain.LottoResult;
import lotto.dto.LottoResultDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lotto.domain.Rank.*;

public class LottoResultDAO {
    private final Connection conn;

    public LottoResultDAO(Connection conn) {
        this.conn = conn;
    }

    public void addLottoResult(LottoResultDTO lottoResultDto) throws SQLException {
        String query = "INSERT INTO result VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, lottoResultDto.getRound());
        pstmt.setInt(2, lottoResultDto.getFirst());
        pstmt.setInt(3, lottoResultDto.getSecond());
        pstmt.setInt(4, lottoResultDto.getThird());
        pstmt.setInt(5, lottoResultDto.getFourth());
        pstmt.setInt(6, lottoResultDto.getFifth());
        pstmt.setInt(7, lottoResultDto.getMiss());
        pstmt.executeUpdate();
    }

    public LottoResult findByRound(int round) throws SQLException {
        String query = "SELECT * FROM result WHERE round = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return makeLottoResult(rs);
    }

    private LottoResult makeLottoResult(ResultSet rs) throws SQLException {
        LottoResult lottoResult = new LottoResult();
        lottoResult.add(FIRST, rs.getInt("first"));
        lottoResult.add(SECOND, rs.getInt("second"));
        lottoResult.add(THIRD, rs.getInt("third"));
        lottoResult.add(FOURTH, rs.getInt("fourth"));
        lottoResult.add(FIFTH, rs.getInt("fifth"));
        lottoResult.add(MISS, rs.getInt("miss"));

        return lottoResult;
    }
}
