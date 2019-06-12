package lotto.database;

import lotto.domain.WinningLotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoDAO {
    private final Connection con;

    public WinningLottoDAO(Connection con) {
        this.con = con;
    }

    public void addWinningLotto(int roundId, WinningLotto winningLotto) throws SQLException {
        String query = "INSERT INTO winningLotto (ro_id, no1, no2, no3, no4, no5, no6, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(roundId));

        List<Integer> numbers = winningLotto.getLotto().getLottoNumbers().stream().map(n -> n.getNumber()).collect(Collectors.toList());
        for (int i = 0; i < numbers.size(); i++) {
            pstmt.setString(2+i, String.valueOf(numbers.get(i)));
        }
        pstmt.setString(8, String.valueOf(winningLotto.getBonus().getNumber()));
        pstmt.executeUpdate();
    }

    public WinningLotto findWinningLottoByRoundId(int roundId) throws SQLException {
        String query = "SELECT * FROM winningLotto WHERE ro_id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(roundId));
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            throw new SQLException(String.format("데이터베이스에서 회차 번호가 %d인 당첨 로또를 찾는 데 실패했습니다.", roundId));
        }

        return new WinningLotto(Arrays.asList(
                rs.getInt("no1"),
                rs.getInt("no2"),
                rs.getInt("no3"),
                rs.getInt("no4"),
                rs.getInt("no5"),
                rs.getInt("no6")
        ), rs.getInt("bonus"));
    }
}
