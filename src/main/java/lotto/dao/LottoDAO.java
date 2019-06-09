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

    public void addLottos(String lottoId, Lottos lottos) throws SQLException {
        String query = "INSERT INTO lotto VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoId);
        StringBuilder stringLottos = new StringBuilder();
        List<String> strings = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            strings.add(lotto.toString());
        }
        stringLottos.append(String.join("\n", strings));
        pstmt.setString(2, stringLottos.toString());
        pstmt.executeUpdate();
    }

    public Lottos findByLottoId(String lottoId) throws SQLException {
        String query = "SELECT * FROM lotto WHERE lotto_id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        List<Lotto> lottos = new ArrayList<>();
        for (String lotto : rs.getString("lottos").split("\n")) {
            lotto = lotto.substring(1, lotto.lastIndexOf("]"));
            lottos.add(new Lotto(ConvertLottoNumber.run(lotto)));
        }
        return new Lottos(lottos);
    }
}
