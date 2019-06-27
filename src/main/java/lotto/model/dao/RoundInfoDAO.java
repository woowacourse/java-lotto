package lotto.model.dao;

import lotto.model.BonusBall;
import lotto.model.Lotto;
import lotto.model.WinningInfo;
import lotto.model.dto.RoundInfoDTO;
import lotto.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                try (Connection connection = DatabaseUtil.getConnection();
                     PreparedStatement psmt = connection.prepareStatement(INSERT_ROUND_INFO_QUERRY)) {
                        psmt.setInt(1, roundInfoDTO.getPayment());
                        psmt.setInt(2, roundInfoDTO.getManualPurchaseNumber());
                        psmt.setString(3, roundInfoDTO.getWinningLotto());
                        psmt.setInt(4, roundInfoDTO.getBonusBall());

                        psmt.executeUpdate();
                } catch (SQLException e) {
                        System.err.println(e.getMessage());
                }
        }

        public int selectRoundId() {
                try (Connection connection = DatabaseUtil.getConnection();
                     PreparedStatement psmt = connection.prepareStatement(SELECT_ROUND_ID_QUERY);
                     ResultSet rs = psmt.executeQuery()) {
                        if (rs.next()) {
                                return rs.getInt(1);
                        }
                } catch (SQLException e) {
                        System.out.println(e.getMessage());
                }
                return -1;
        }

        public List<Integer> selectIds() {
                List<Integer> ids = new ArrayList<>();
                try (Connection connection = DatabaseUtil.getConnection();
                     PreparedStatement psmt = connection.prepareStatement(SELECT_ID_QUERY);
                     ResultSet rs = psmt.executeQuery()) {
                        while (rs.next()) {
                                ids.add(rs.getInt("id"));
                        }
                } catch (SQLException e) {
                        System.out.println(e.getMessage());
                }
                return ids;
        }

        public WinningInfo selectWinningInfo(int id) {
                try (Connection connection = DatabaseUtil.getConnection();
                     PreparedStatement psmt = createSelectWinningInfoQuery(connection, id);
                     ResultSet rs = psmt.executeQuery()) {
                        if (rs.next()) {
                                Lotto winningLotto = new Lotto(rs.getString("winningLotto").split(", "));
                                BonusBall bonusBall = new BonusBall(rs.getInt("bonusball"));
                                return new WinningInfo(winningLotto, bonusBall);
                        }
                } catch (SQLException e) {
                        System.out.println(e.getMessage());
                }
                return null;
        }

        private PreparedStatement createSelectWinningInfoQuery(Connection connection, int id) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WINNING_INFO_QUERY);
                preparedStatement.setInt(1, id);
                return preparedStatement;
        }
}
