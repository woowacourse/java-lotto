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

}
