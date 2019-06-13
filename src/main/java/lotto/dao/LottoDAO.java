package lotto.dao;

import lotto.dto.LottoDTO;
import lotto.dto.WinningLottoDTO;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static lotto.db.DBConnection.getConnection;

public class LottoDAO {
    private static final Connection conn = getConnection();

    public void addLottoTicket(LottoDTO lotto) throws SQLException {
        int autoInsertedKey = addLotto();
        addLottoNumbers(autoInsertedKey, lotto.getNumbers());
    }

    private int addLotto() throws SQLException {
        // 일반로또의 로또 타입은 0
        String query = "INSERT INTO lotto.lotto (type) VALUES (0)";
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

    public LottoDTO findByLottoId(String lottoId) throws SQLException {
        String query = "SELECT l.id, l.type, GROUP_CONCAT(ln.number SEPARATOR  ',') as numbers " +
                "FROM lotto.lotto as l " +
                "JOIN lotto.lottonumber as ln ON l.id = ln.lotto_id " +
                "WHERE l.id = ? " +
                "GROUP BY l.id";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, lottoId);
        ResultSet rs = pstmt.executeQuery();

        return getLottoDTO(rs);
    }

    private LottoDTO getLottoDTO(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        try {
            int id = rs.getInt("id");
            List<String> numbers = Arrays.asList(rs.getString("numbers").split(","));

            // TODO 나중에 빌더 패턴으로 바꿔보기
            return new LottoDTO(id,
                    Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)), Integer.parseInt(numbers.get(2)),
                    Integer.parseInt(numbers.get(3)), Integer.parseInt(numbers.get(4)), Integer.parseInt(numbers.get(5)));
        } catch (Exception e) {
            return null;
        }
    }

}
