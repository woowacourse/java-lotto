package lotto.model.dao;

import lotto.model.LottoRank;
import lotto.model.dto.ResultDTO;

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
                PreparedStatementSetter pss = pstmt -> {
                        Map<LottoRank, Integer> result = resultDTO.getWinningResult();
                        pstmt.setInt(1, id);
                        pstmt.setInt(2, result.get(LottoRank.FIRST));
                        pstmt.setInt(3, result.get(LottoRank.SECOND));
                        pstmt.setInt(4, result.get(LottoRank.THIRD));
                        pstmt.setInt(5, result.get(LottoRank.FOURTH));
                        pstmt.setInt(6, result.get(LottoRank.FIFTH));
                        pstmt.setInt(7, result.get(LottoRank.NONE));
                        pstmt.setInt(8, resultDTO.getRevenue());
                        pstmt.setDouble(9, resultDTO.getYield());
                };
                JdbcTemplate template = new JdbcTemplate();
                template.executeUpdate(INSERT_RESULT_QUERY, pss);
        }

        public ResultDTO selectResultDTO(int id) {
                PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, id);

                RowMapper rm = rs -> {
                        if(rs.next()){
                                int revenue = rs.getInt("revenue");
                                double yield = rs.getInt("yield");

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
                                return new ResultDTO(result, revenue, yield);
                        }
                        return null;
                };
                JdbcTemplate template = new JdbcTemplate();
                return (ResultDTO)template.executeQuery(SELECT_RESULT_QUERY, pss, rm);
        }
}
