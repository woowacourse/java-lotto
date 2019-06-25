package lotto.dao;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Numbers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoPurchaseDAO {
    private final Connection connection;

    public LottoPurchaseDAO(Connection connection) {
        this.connection = connection;
    }

    public void savePurchaseLotto(List<Lotto> lottos, int round) throws SQLException {
        String query = "INSERT INTO PURCHASE VALUES(0, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (Lotto lotto : lottos) {
            preparedStatement.setString(1, lotto.getLottoNumbers().toString());
            preparedStatement.setInt(2, round);
            //TODO 수동 자동 나누기
            preparedStatement.setInt(3, 1);
            preparedStatement.addBatch();
            preparedStatement.clearParameters();
        }
        preparedStatement.executeBatch();
    }

    public List<Numbers> inquireLotto(int round) throws SQLException {
        String query = "SELECT lotto_number FROM PURCHASE WHERE round_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<Numbers> lottos = new ArrayList<>();

        while (resultSet.next()) {
            lottos.add(new Numbers(resultSet.getString("lotto_number")));
        }
        
        return lottos;
    }
}
