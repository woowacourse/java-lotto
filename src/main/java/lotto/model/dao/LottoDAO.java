package lotto.model.dao;

import lotto.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO {
        private static final String INSERT_LOTTO_QUERRY = "INSERT INTO LOTTO (round_id, number) values (?, ?)";
        private static final String SELECT_LOTTOS_QUERY = "SELECT number FROM lotto where round_id = ?";

        private static final LottoDAO INSTANCE = new LottoDAO();

        private LottoDAO(){

        }

        public static LottoDAO getInstance() {
                return INSTANCE;
        }

        public void insertLotto(int roundId, String lottoNumbers) {
                try(Connection connection = DatabaseUtil.getConnection();
                    PreparedStatement psmt = connection.prepareStatement(INSERT_LOTTO_QUERRY)){
                        psmt.setInt(1, roundId);
                        psmt.setString(2, lottoNumbers);

                        psmt.executeUpdate();
                }catch (SQLException e){
                        System.out.println(e.getMessage());
                }
        }

        public List<String> selectLottos(int id) {
                List<String> lottos = new ArrayList<>();
                try(Connection connection = DatabaseUtil.getConnection();
                    PreparedStatement psmt = createSelectLottosQuery(connection, id);
                    ResultSet rs = psmt.executeQuery()) {
                        while(rs.next()){
                                lottos.add(rs.getString("number"));
                        }
                }catch (SQLException e){
                        System.out.println(e.getMessage());

                }
                return lottos;
        }

        private PreparedStatement createSelectLottosQuery(Connection connection, int id) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOTTOS_QUERY);
                preparedStatement.setInt(1, id);
                return preparedStatement;
        }
}
