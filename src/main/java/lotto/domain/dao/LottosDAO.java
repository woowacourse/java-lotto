package lotto.domain.dao;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottosDAO {
    private static final String SQL = "INSERT INTO lotto(num_1,num_2,num_3,num_4,num_5,num_6,round_id) values(?,?,?,?,?,?,?)";
    private final Connection databaseConnection;

    public LottosDAO(final Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void addLottos(int round, Lottos lottos) throws SQLException {
        PreparedStatement pstmt = databaseConnection.prepareStatement(SQL);
        for (Lotto lotto : lottos.getLottos()) {
            pstmt.setInt(1, lotto.getLottoNumber(0));
            pstmt.setInt(2, lotto.getLottoNumber(1));
            pstmt.setInt(3, lotto.getLottoNumber(2));
            pstmt.setInt(4, lotto.getLottoNumber(3));
            pstmt.setInt(5, lotto.getLottoNumber(4));
            pstmt.setInt(6, lotto.getLottoNumber(5));
            pstmt.setInt(7, round);
        }
        pstmt.executeUpdate();
    }

    public Lottos findLottoByRound(int round) throws SQLException {
        String query = "SELECT * FROM lotto WHERE round_id = ?";
        PreparedStatement pstmt = databaseConnection.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();
        while (rs.next()) {
            List<Integer> lottoNumbers = new ArrayList<>();
            lottoNumbers.add(rs.getInt("num_1"));
            lottoNumbers.add(rs.getInt("num_2"));
            lottoNumbers.add(rs.getInt("num_3"));
            lottoNumbers.add(rs.getInt("num_4"));
            lottoNumbers.add(rs.getInt("num_5"));
            lottoNumbers.add(rs.getInt("num_6"));
            lottos.add(new Lotto(lottoNumbers));
        }

        return new Lottos(lottos);
    }

//    public User findByUserId(String userId) throws SQLException {
//        String query = "SELECT * FROM user WHERE user_id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, userId);
//        ResultSet rs = pstmt.executeQuery();
//
//        if (!rs.next()) return null;
//
//        return new User(
//                rs.getString("user_id"),
//                rs.getString("name"));
//    }
}
