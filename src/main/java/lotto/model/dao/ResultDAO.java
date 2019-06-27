package lotto.model.dao;

import lotto.model.LottoRank;
import lotto.model.dto.ResultDTO;
import lotto.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResultDAO {
        private static final String INSERT_RESULT_QUERY = "INSERT INTO RESULT (round_id, FIRST, SECOND, THIRD, FOURTH, FIFTH, NONE, revenue, yield) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_RESULT_QUERY = "SELECT FIRST, SECOND, THIRD, FOURTH, FIFTH, NONE, revenue, yield FROM RESULT WHERE round_id = ?";

        private static final ResultDAO INSTANCE = new ResultDAO();

        private ResultDAO(){

        }

        public static ResultDAO getInstance() {
                return INSTANCE;
        }

        public void insertResult(int id, ResultDTO resultDTO) {
                try(Connection connection = DatabaseUtil.getConnection();
                    PreparedStatement psmt = connection.prepareStatement(INSERT_RESULT_QUERY)){
                        Map<LottoRank, Integer> result = resultDTO.getWinningResult();
                        psmt.setInt(1, id);
                        psmt.setInt(2, result.get(LottoRank.FIRST));
                        psmt.setInt(3, result.get(LottoRank.SECOND));
                        psmt.setInt(4, result.get(LottoRank.THIRD));
                        psmt.setInt(5, result.get(LottoRank.FOURTH));
                        psmt.setInt(6, result.get(LottoRank.FIFTH));
                        psmt.setInt(7, result.get(LottoRank.NONE));
                        psmt.setInt(8, resultDTO.getRevenue());
                        psmt.setDouble(9, resultDTO.getYield());

                        psmt.executeUpdate();
                }catch (SQLException e){
                        System.out.println(e.getMessage());
                }
        }

        public ResultDTO selectResult(int id) {
                ResultDTO resultDTO = null;
                try(Connection connection = DatabaseUtil.getConnection();
                    PreparedStatement psmt = createSelectResultQuery(connection, id);
                    ResultSet rs = psmt.executeQuery()) {
                        while(rs.next()){
                                Map<LottoRank, Integer> result = createResultMap(rs);
                                int revenue = rs.getInt("revenue");
                                double yield = rs.getInt("yield");

                                resultDTO = new ResultDTO(result, revenue, yield);
                        }
                }catch (SQLException e){
                        System.out.println(e.getMessage());
                }
                return resultDTO;
        }

        private PreparedStatement createSelectResultQuery(Connection connection, int id) throws SQLException {
                PreparedStatement psmt = connection.prepareStatement(SELECT_RESULT_QUERY);
                psmt.setInt(1, id);
                return psmt;
        }

        private Map<LottoRank, Integer> createResultMap(ResultSet rs) throws SQLException {
                int first = rs.getInt("FIRST");
                int second = rs.getInt("SECOND");
                int third = rs.getInt("THIRD");
                int fourth = rs.getInt("FOURTH");
                int fifth = rs.getInt("FIFTH");
                int none = rs.getInt("NONE");

                Map<LottoRank, Integer> result = new HashMap<LottoRank, Integer>(){{
                   put(LottoRank.FIRST, first);
                   put(LottoRank.SECOND, second);
                   put(LottoRank.THIRD, third);
                   put(LottoRank.FOURTH, fourth);
                   put(LottoRank.FIFTH, fifth);
                   put(LottoRank.NONE, none);
                }};

                return result;
        }

}
