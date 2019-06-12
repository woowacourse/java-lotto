package lotto.database;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.ManualLottoNumbersGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoDAO {
    private final Connection con;

    public LottoDAO(Connection con) {
        this.con = con;
    }

    public void addLotto(int roundId, Lottos lottos) throws SQLException {
        String query = "INSERT INTO lotto (ro_id, no1, no2, no3, no4, no5, no6) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(roundId));

        for (Lotto lotto : lottos.getLottos()) {
            List<Integer> numbers = lotto.getLottoNumbers().stream().map(n -> n.getNumber()).collect(Collectors.toList());
            for (int i = 0; i < numbers.size(); i++) {
                pstmt.setString(2+i, String.valueOf(i));
            }
            pstmt.executeUpdate();
        }
    }

    public Lottos findLottosByRoundId(int roundId) throws SQLException {
        String query = "SELECT * FROM lotto WHERE ro_id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(roundId));
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();
        if (!rs.next()) {
            throw new SQLException(String.format("데이터베이스에서 회차 번호가 %d인 로또를 찾는 데 실패했습니다.", roundId));
        }
        do {
            lottos.add(Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(
                    rs.getInt("no1"),
                    rs.getInt("no2"),
                    rs.getInt("no3"),
                    rs.getInt("no4"),
                    rs.getInt("no5"),
                    rs.getInt("no6")
            ))));
        } while (rs.next());

        return new Lottos(lottos);
    }
}
