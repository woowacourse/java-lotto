package lotto.dao;

import lotto.db.DBUtils;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottosDao {
    private static final String INSERT_SQL = "INSERT INTO lotto(num_1,num_2,num_3,num_4,num_5,num_6,round_id) values(?,?,?,?,?,?,?)";
    private static final String SELECT_SQL = "SELECT num_1,num_1,num_2,num_3,num_4,num_5,num_6 FROM lotto WHERE round_id = ?";
    private static final String COL_NAME = "num";

    private final Connection conn;

    public LottosDao(final Connection conn) {
        this.conn = conn;
    }

    public void addLottos(int round, Lottos lottos) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
        for (Lotto lotto : lottos.getLottos()) {
            DBUtils.setLottoInDB(pstmt, lotto);
            pstmt.setInt(7, round);
            pstmt.executeUpdate();
        }
    }

    public Lottos findLottoByRound(int round) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(SELECT_SQL);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();
        while (rs.next()) {
            lottos.add(DBUtils.getLottoInDB(rs, COL_NAME));
        }

        return new Lottos(lottos);
    }

}
