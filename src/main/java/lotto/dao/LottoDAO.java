package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoParser;
import lotto.domain.UserLottos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO {

    public void addLottos(UserLottos userLottos, int round) throws SQLException {
        String query = "INSERT INTO lotto (numbers,round_no) values (?,?)";
        Connection con = DBConnection.getConnection();

        for (Lotto lotto : userLottos.getUserLottos()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, lotto.toString());
            pstmt.setInt(2, round);
            pstmt.executeUpdate();
        }
    }

    public UserLottos findAllByRound(int round) throws SQLException {
        String query = "SELECT numbers FROM lotto WHERE round_no = ?";
        List<Lotto> lottos = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            lottos.add(new Lotto(LottoParser.parseLottoNumbers(rs.getString("numbers"))));
        }
        DBConnection.closeConnection(con);
        return new UserLottos(lottos);
    }
}

