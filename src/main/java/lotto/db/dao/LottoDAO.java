package lotto.db.dao;

import lotto.db.dto.LottoDTO;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static lotto.db.DBConnection.getConnection;

public class LottoDAO {
    private static final Connection conn = getConnection();

    public static void addLottoTicket(LottoTickets inputLottoTickets) throws SQLException {
        List<LottoTicket> lottoTickets = inputLottoTickets.getLottoTickets();
        for (LottoTicket lottoTicket : lottoTickets) {
            int lotto_id = addLotto();
            addLottoNumbers(lotto_id, lottoTicket.getLottoNumbers());
            addResult(lotto_id);
        }
    }

    private static int addLotto() throws SQLException {
        // 일반로또의 로또 타입은 0
        String query = "INSERT INTO lotto.lotto (type) VALUES (0)";
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        return (rs.next()) ? rs.getInt(1) : 0;
    }

    private static void addLottoNumbers(int autoInsertedKey, List<LottoNumber> numbers) throws SQLException {
        String query = "INSERT INTO lotto.lottonumber (lotto_id, number) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);

        for (LottoNumber number : numbers) {
            pstmt.setInt(1, autoInsertedKey);
            pstmt.setInt(2, number.getNumber());
            pstmt.addBatch();
            pstmt.clearParameters();
        }
        pstmt.executeBatch();
    }

    private static void addResult(int lotto_id) throws SQLException {
        String query = "INSERT INTO lotto.result (lotto_id) VALUES (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.setInt(1, lotto_id);
        pstmt.executeUpdate();
    }

    public static LottoDTO findByLottoId(String lottoId) throws SQLException {
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

    private static LottoDTO getLottoDTO(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        try {
            int id = rs.getInt("id");
            List<String> numbers = Arrays.asList(rs.getString("numbers").split(","));

            // TODO 나중에 빌더 패턴으로 바꿔보기
            return new LottoDTO(
                    Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)), Integer.parseInt(numbers.get(2)),
                    Integer.parseInt(numbers.get(3)), Integer.parseInt(numbers.get(4)), Integer.parseInt(numbers.get(5)));
        } catch (Exception e) {
            return null;
        }
    }

}
