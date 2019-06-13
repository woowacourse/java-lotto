package lotto.dao;

import lotto.dto.WinningLottoDTO;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static lotto.db.DBConnection.getConnection;

public class WinningLottoDAO {
    private static final Connection conn = getConnection();

    public void addWinningLottoTicket(WinningLottoDTO winningLotto) throws SQLException {
        int autoInsertedKey = addLotto();
        addLottoNumbers(autoInsertedKey, winningLotto.getNumbers());
        addWinningLotto(winningLotto, autoInsertedKey);
    }

    private int addLotto() throws SQLException {
        // 당첨로또의 로또 타입은 1
        String query = "INSERT INTO lotto.lotto (type) VALUES (1)";
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        return (rs.next()) ? rs.getInt(1) : 0;
    }

    private void addLottoNumbers(int autoInsertedKey, List<Integer> numbers) throws SQLException {
        String query = "INSERT INTO lotto.lottonumber (lotto_id, number) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);

        for (Integer number : numbers) {
            pstmt.setInt(1, autoInsertedKey);
            pstmt.setInt(2, number);
            pstmt.addBatch();
            pstmt.clearParameters();
        }
        pstmt.executeBatch();
    }

    private void addWinningLotto(WinningLottoDTO winningLotto, int autoInsertedKey) throws SQLException {
        String query = "INSERT INTO lotto.winninglotto (lotto_id, bonusBall) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.setInt(1, autoInsertedKey);
        pstmt.setInt(2, winningLotto.getBonusBall());

        pstmt.executeUpdate();
    }

    public WinningLottoDTO findByWinningLottoId(String winningLottoId) throws SQLException {
        String query = "SELECT l.id, l.type, GROUP_CONCAT(ln.number SEPARATOR ',') as numbers, w.bonusBall " +
                "FROM lotto.lotto as l " +
                "JOIN lotto.winninglotto as w ON w.lotto_id = l.id " +
                "JOIN lotto.lottonumber as ln ON l.id = ln.lotto_id " +
                "WHERE w.id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, winningLottoId);
        ResultSet rs = pstmt.executeQuery();

        return getWinningLottoDTO(rs);
    }

    private WinningLottoDTO getWinningLottoDTO(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        try {
            int id = rs.getInt("id");
            int bonusBall = rs.getInt("bonusBall");
            List<String> numbers = Arrays.asList(rs.getString("numbers").split(","));

            // TODO 나중에 빌더 패턴으로 바꿔보기
            return new WinningLottoDTO(id, bonusBall,
                    Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)), Integer.parseInt(numbers.get(2)),
                    Integer.parseInt(numbers.get(3)), Integer.parseInt(numbers.get(4)), Integer.parseInt(numbers.get(5)));
        } catch (Exception e) {
            return null;
        }
    }
}
