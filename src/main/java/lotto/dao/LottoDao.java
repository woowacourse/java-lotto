package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.dto.LottoDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.dao.DataConnection.closeConnection;
import static lotto.dao.DataConnection.getConnection;

public class LottoDao {
    static final int BOUGHT_LOTTO_NUMBER_FROM_INDEX = 0;
    static final int BOUGHT_LOTTO_NUMBER_TO_INDEX = 6;

    public List<Lotto> findAllBoughtLottoByRound(int round) throws SQLException {
        String sql = "SELECT * FROM bought_lotto WHERE round_id = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = getConnection().prepareStatement(sql);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();
        while (rs.next()) {
            lottos.add(new Lotto(getLottoNumbers(rs)));
        }
        closeConnection(con);
        return lottos;
    }

    private List<LottoNumber> getLottoNumbers(final ResultSet rs) throws SQLException {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = BOUGHT_LOTTO_NUMBER_FROM_INDEX + 2; i < BOUGHT_LOTTO_NUMBER_TO_INDEX + 2; i++) {
            int lottoNumber = rs.getInt(i);
            lottoNumbers.add(LottoNumber.getLottoNumber(lottoNumber));
        }
        return lottoNumbers;
    }

    public void addAllLotto(final List<LottoDto> lottoDtos, int round) throws SQLException {
        String sql = "INSERT INTO bought_lotto VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        lottoQueryExcute(lottoDtos, round, pstmt);
        closeConnection(con);
    }

    private void lottoQueryExcute(final List<LottoDto> lottoDtos, final int round,
                                  final PreparedStatement pstmt) throws SQLException {
        for (int i = 0; i < lottoDtos.size(); i++) {
            setIntFrom(lottoDtos, pstmt, i);
            pstmt.setInt(7, round);
            pstmt.executeUpdate();
        }
    }

    private void setIntFrom(final List<LottoDto> lottoDtos, final PreparedStatement pstmt,
                            final int dtoIndex) throws SQLException {
        for (int i = BOUGHT_LOTTO_NUMBER_FROM_INDEX; i < BOUGHT_LOTTO_NUMBER_TO_INDEX; i++) {
            pstmt.setInt(i + 1, lottoDtos.get(dtoIndex).getLottoNumbers().get(i));
        }
    }

    public void removeAllLotto(final List<LottoDto> lottoDtos, int round) throws SQLException {
        String sql = "DELETE FROM bought_lotto WHERE num1 = ? AND num2 = ? AND num3 = ? " +
                "AND num4 = ? AND num5 = ? AND num6 = ? AND round_id = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        lottoQueryExcute(lottoDtos, round, pstmt);
        closeConnection(con);
    }
}
