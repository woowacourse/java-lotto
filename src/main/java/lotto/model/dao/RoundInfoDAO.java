package lotto.model.dao;

import lotto.model.BonusBall;
import lotto.model.Lotto;
import lotto.model.WinningInfo;
import lotto.model.dto.RoundInfoDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundInfoDAO {
        public static final String SELECT_ROUND_ID_QUERY = "SELECT MAX(id) FROM ROUND_INFO";
        public static final String SELECT_ID_QUERY = "SELECT id FROM ROUND_INFO";
        private static final String INSERT_ROUND_INFO_QUERRY = "INSERT INTO ROUND_INFO (payment, manualPaymentNumber, winningLotto, bonusball) values (?, ?, ?, ?)";
        private static final String SELECT_WINNING_INFO_QUERY = "SELECT winningLotto, bonusball FROM ROUND_INFO WHERE id = ?";

        private static final RoundInfoDAO INSTANCE = new RoundInfoDAO();

        private RoundInfoDAO() {
        }

        public static RoundInfoDAO getInstance() {
                return INSTANCE;
        }

        public void insertRoundInfo(RoundInfoDTO roundInfoDTO) {
                PreparedStatementSetter pss = pstmt -> {
                        pstmt.setInt(1, roundInfoDTO.getPayment());
                        pstmt.setInt(2, roundInfoDTO.getManualPurchaseNumber());
                        pstmt.setString(3, roundInfoDTO.getWinningLotto());
                        pstmt.setInt(4, roundInfoDTO.getBonusBall());
                };
                JdbcTemplate template = new JdbcTemplate();
                template.executeUpdate(INSERT_ROUND_INFO_QUERRY, pss);
        }

        public int selectRoundId() {
                PreparedStatementSetter pss= pstmt -> {
                };

                RowMapper rm = rs -> {
                        if (rs.next()) {
                                return rs.getInt(1);
                        }
                        return null;
                };

                JdbcTemplate template = new JdbcTemplate();
                return (int)template.executeQuery(SELECT_ROUND_ID_QUERY, pss, rm);
        }

        public List<Integer> selectIds() {
                PreparedStatementSetter pss = pstmt -> {
                };

                RowMapper rm = new RowMapper() {
                        @Override
                        public Object mapRow(ResultSet rs) throws SQLException {
                                List<Integer> ids = new ArrayList<>();
                                while (rs.next()) {
                                        ids.add(rs.getInt("id"));
                                }
                                return ids;
                        }
                };

                JdbcTemplate template = new JdbcTemplate();
                return (List<Integer>)template.executeQuery(SELECT_ID_QUERY, pss, rm);
        }

        public WinningInfo selectWinningInfo(int id) {
                PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, id);

                RowMapper rm = rs -> {
                        if (rs.next()) {
                                Lotto winningLotto = new Lotto(rs.getString("winningLotto").split(", "));
                                BonusBall bonusBall = new BonusBall(rs.getInt("bonusball"));
                                return new WinningInfo(winningLotto, bonusBall);
                        }
                        return null;
                };

                JdbcTemplate template = new JdbcTemplate();
                return (WinningInfo)template.executeQuery(SELECT_WINNING_INFO_QUERY, pss, rm);
        }
}
