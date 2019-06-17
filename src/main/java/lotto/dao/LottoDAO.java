package lotto.dao;

import lotto.dao.sqls.LottoSQLs;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoDAO {
    private static LottoDAO lottoDAO = new LottoDAO();
    private static final int START_COLUMN_NUMBER_OF_LOTTO_TABLE = 2;

    private LottoDAO() {
    }

    public static LottoDAO getInstance() {
        return lottoDAO;
    }

    public void addLotto(Lotto lotto, int round) throws SQLException {
        String query = LottoSQLs.INSERT_LOTTO;

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            DBUtils.setLottoNumber(pstmt, lotto, START_COLUMN_NUMBER_OF_LOTTO_TABLE);
            pstmt.executeUpdate();
        }
    }

    public List<Lotto> selectByRound(int round) throws SQLException {
        List<Lotto> lottos = new ArrayList<>();
        String query = LottoSQLs.SELECT_LOTTO_BY_ROUND;

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(
                        rs.getInt("lotto_number_1"),
                        rs.getInt("lotto_number_2"),
                        rs.getInt("lotto_number_3"),
                        rs.getInt("lotto_number_4"),
                        rs.getInt("lotto_number_5"),
                        rs.getInt("lotto_number_6"))));
                lottos.add(lotto);
            }

            return Collections.unmodifiableList(lottos);
        }
    }
}
