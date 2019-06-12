package lotto.database;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.sql.*;
import java.util.List;

public class LottoDAO {
    private static final int ROUND_INDEX = 1;
    private static final int NUMBER_START_INDEX = 2;
    private static final String INSERT_LOTTO_QUERY =
            "INSERT INTO lotto(round, number1, number2, number3, number4, number5, number6)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static LottoDAO lottoDAO;
    private static Connection connection;

    private LottoDAO() {
    }

    public static LottoDAO getInstance(Connection connection) {
        if (lottoDAO == null) {
            lottoDAO = new LottoDAO();
        }
        LottoDAO.connection = connection;
        return lottoDAO;
    }

    public void addAllLottos(Lottos lottos, int round) throws SQLException {
        for (Lotto lotto : lottos.getLottos()) {
            lottoDAO.addLotto(lotto, round);
        }
    }

    public void addLotto(Lotto lotto, int round) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(INSERT_LOTTO_QUERY);
        pstmt.setInt(ROUND_INDEX, round);
        List<Integer> lottos = lotto.getLottoNumbers();
        for (int i = 0; i < lottos.size(); i++) {
            pstmt.setInt(i + NUMBER_START_INDEX, lottos.get(i));
        }
        pstmt.executeUpdate();
    }
}
