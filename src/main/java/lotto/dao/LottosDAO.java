package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottosDAO {
    private static final String INSERT_SQL = "INSERT INTO lotto(num_1,num_2,num_3,num_4,num_5,num_6,round_id) values(?,?,?,?,?,?,?)";
    private static final String SELECT_SQL = "SELECT * FROM lotto WHERE round_id = ?";

    private final Connection conn;

    public LottosDAO(final Connection conn) {
        this.conn = conn;
    }

    public void addLottos(int round, Lottos lottos) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
        for (Lotto lotto : lottos.getLottos()) {
            DBUtils.setLottoInDB(pstmt, lotto);
            pstmt.setInt(7, round);
        }
        pstmt.executeUpdate();
    }

    public Lottos findLottoByRound(int round) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(SELECT_SQL);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();
        while (rs.next()) {
            lottos.add(DBUtils.getLottoInDB(rs, "num"));
        }

        return new Lottos(lottos);
    }

}
