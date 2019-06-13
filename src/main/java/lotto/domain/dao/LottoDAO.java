package lotto.domain.dao;

import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LottoDAO {
    private static final Connection connection = ConnectionDB.getConnection();
    private static final int ROUND_COLUMN = 1;
    private static final int ONE_NUMBER_COLUMN = 2;


    public static void addTotalLottos(Lottos lottos) throws SQLException {
        int round = RoundDAO.searchMaxCount();
        List<Lotto> totalLottos = lottos.getTotalLottos();
        for (Lotto lotto : totalLottos) {
            addLottoNumbers(round, lotto);
        }
    }

    public static void addLottoNumbers(int round, Lotto lotto) throws SQLException {
        String query = "INSERT INTO number VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(ROUND_COLUMN, round);
        int firstCount = ONE_NUMBER_COLUMN;
        for (Integer lottoNumber : lotto.getLotto()) {
            preparedStatement.setInt(firstCount, lottoNumber);
            firstCount++;
        }
        preparedStatement.executeUpdate();
    }
}
