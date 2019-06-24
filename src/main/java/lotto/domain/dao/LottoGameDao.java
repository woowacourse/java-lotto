package lotto.domain.dao;

import lotto.DBUtils;
import lotto.domain.user.PurchaseAmount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LottoGameDao {
    private static final String INSERT_LOTTO_GAME_QUERY = "INSERT INTO lottoGame (lotto_amount) VALUES(?)";
    private static final int LOTTO_AMOUNT = 1;

    private static LottoGameDao lottoGameDao = new LottoGameDao();

    public static LottoGameDao getInstance() {
        return lottoGameDao;
    }

    public void addLottoAmount(PurchaseAmount purchaseAmount) throws SQLException {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(INSERT_LOTTO_GAME_QUERY);
            pstmt.setInt(LOTTO_AMOUNT, purchaseAmount.getLottoAmount());
            pstmt.executeUpdate();
        }
    }
}