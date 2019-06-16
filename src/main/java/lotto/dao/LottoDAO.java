package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.util.ConvertLottoNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO {
    private Connection con;

    public LottoDAO(Connection con) {
        this.con = con;
    }

    public void addLottos(String round, Lottos lottos) throws SQLException {
        for (Lotto lotto : lottos.getLottos()) {
            addLotto(round, lotto);
        }
    }

    public void addLotto(String round, Lotto lotto) throws SQLException {
        String query = "INSERT INTO lotto VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, round);
        pstmt.setString(2, lotto.toString());
        pstmt.executeUpdate();
    }

    public Lottos findByLottoRound(String lottoId) throws SQLException {
        String query = "SELECT * FROM lotto WHERE lotto_round = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        List<Lotto> lottos = new ArrayList<>();
        lottos.add(makeLotto(rs.getString("lotto")));
        while (rs.next()) {
            lottos.add(makeLotto(rs.getString("lotto")));
        }

        return new Lottos(lottos);
    }

    private Lotto makeLotto(String stringLotto) {
        return new Lotto(ConvertLottoNumber.run(
                stringLotto.substring(1, stringLotto.lastIndexOf("]")))
        );
    }
}
