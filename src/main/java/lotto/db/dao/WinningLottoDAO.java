package lotto.db.dao;

import lotto.db.dto.LottoGameResultDTO;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static lotto.db.DBConnection.getConnection;

public class WinningLottoDAO {
    private static final Connection conn = getConnection();

    public static void addWinningLottoTicket(WinningLotto winningLotto) throws SQLException {
        List<LottoNumber> lottoNumbers = winningLotto.getWinningNumbers().getLottoNumbers();

        int lotto_id = addLotto();
        addLottoNumbers(lotto_id, lottoNumbers);
        int winning_id = addWinningLotto(winningLotto, lotto_id);
        addWinningLottoIdIntoLottoGame(winning_id, lotto_id);
    }

    private static int addLotto() throws SQLException {
        // 당첨로또의 로또 타입은 1
        String query = "INSERT INTO lotto.lotto (type) VALUES (1)";
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

    private static int addWinningLotto(WinningLotto winningLotto, int autoInsertedKey) throws SQLException {
        String query = "INSERT INTO lotto.winninglotto (lotto_id, bonusBall) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pstmt.setInt(1, autoInsertedKey);
        pstmt.setInt(2, winningLotto.getBonusBall().getNumber());

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        return (rs.next()) ? rs.getInt(1) : 0;
    }

    private static void addWinningLottoIdIntoLottoGame(int winning_id, int lotto_id) throws SQLException {
        String query = "UPDATE lotto.lottogame SET winning_id = ? WHERE (lotto_id = ?)";
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pstmt.setInt(1, winning_id);
        pstmt.setInt(2, lotto_id);
        pstmt.executeUpdate();
    }

    public static LottoGameResultDTO findLatestWinningLotto() throws SQLException {
        String query = "SELECT l.id, GROUP_CONCAT(ln.number SEPARATOR  ',') as numbers, w.bonusBall " +
                "FROM lotto.lotto as l " +
                "JOIN lotto.winninglotto as w ON w.lotto_id = l.id " +
                "JOIN lotto.lottonumber as ln ON l.id = ln.lotto_id " +
                "WHERE l.type = 1 GROUP BY l.id " +
                "ORDER BY l.id DESC limit 1";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        return getWinningLottoDTO(rs);
    }

    public static LottoGameResultDTO findByWinningLottoId(String winningLottoId) throws SQLException {
        String query = "SELECT l.id, l.type, GROUP_CONCAT(ln.number SEPARATOR ',') as numbers, w.bonusBall " +
                "FROM lotto.lotto as l " +
                "JOIN lotto.winninglotto as w ON w.lotto_id = l.id " +
                "JOIN lotto.lottonumber as ln ON l.id = ln.lotto_id " +
                "WHERE l.type = 1 AND w.id = ? " +
                "GROUP BY l.id;";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, winningLottoId);
        ResultSet rs = pstmt.executeQuery();

        return getWinningLottoDTO(rs);
    }

    private static LottoGameResultDTO getWinningLottoDTO(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        try {
            int id = rs.getInt("id");
            int bonusBall = rs.getInt("bonusBall");
            List<String> numbers = Arrays.asList(rs.getString("numbers").split(","));

            return new LottoGameResultDTO(
                    new LottoGameResultDTO.Builder(Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)), Integer.parseInt(numbers.get(2)),
                            Integer.parseInt(numbers.get(3)), Integer.parseInt(numbers.get(4)), Integer.parseInt(numbers.get(5)), bonusBall).winningLottoId(id));
        } catch (Exception e) {
            return null;
        }
    }
}
