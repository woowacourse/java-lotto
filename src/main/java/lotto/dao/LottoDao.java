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

public class LottoDao {

    private static final String queryForAddLottos = "INSERT INTO lotto (numbers,round_no) values (?,?)";
    private static final String queryForFindLotts = "SELECT numbers FROM lotto WHERE round_no = ?";

    private Connection con = null;

    private LottoDao() {
    }

    private static class LottoDaoHolder {
        private static final LottoDao INSTANCE = new LottoDao();
    }

    public static LottoDao getInstance() {
        return LottoDaoHolder.INSTANCE;
    }

    public void addLottos(UserLottos userLottos, int round) throws SQLException {
        for (Lotto lotto : userLottos.getUserLottos()) {
            PreparedStatement pstmt = ConnectionManager.prepareStatement(DBConnection.getConnection(), queryForAddLottos);
            pstmt.setString(1, lotto.toString());
            pstmt.setInt(2, round);
            pstmt.executeUpdate();
        }
        DBConnection.closeConnection(con);
    }

    public UserLottos findAllByRound(int round) throws SQLException {
        PreparedStatement pstmt = ConnectionManager.prepareStatement(DBConnection.getConnection(), queryForFindLotts);

        List<Lotto> lottos = new ArrayList<>();
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            lottos.add(new Lotto(LottoParser.parseLottoNumbers(rs.getString("numbers"))));
        }
        DBConnection.closeConnection(con);
        return new UserLottos(lottos);
    }
}

