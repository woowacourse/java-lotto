package lotto.database;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.generator.ManualLottoNumbersGenerator;

import java.sql.*;
import java.util.ArrayList;
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
        List<Integer> lottoNumbers = lotto.getLottoNumbers();
        for (int i = 0; i < lottoNumbers.size(); i++) {
            pstmt.setInt(NUMBER_START_INDEX + i, lottoNumbers.get(i));
        }
        pstmt.executeUpdate();
    }

    public Lottos findLottosByRound(int round) throws SQLException {
        String query = "SELECT * FROM lotto WHERE round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(ROUND_INDEX, round);
        ResultSet resultSet = pstmt.executeQuery();

        Lottos lottos = new Lottos();
        while (resultSet.next()) {
            List<Integer> lottoNumbers = new ArrayList<>();
            for (int i = 1; i < 7; i++) {
                lottoNumbers.add(resultSet.getInt(NUMBER_START_INDEX + i));
            }
            lottos.add(new Lotto(ManualLottoNumbersGenerator.getInstance(lottoNumbers).generate()));
        }
        return lottos;
    }
}
