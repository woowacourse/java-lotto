package lotto.model.dao;

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
                PreparedStatementSetter pss = pstmt -> {
                        pstmt.setInt(1, roundId);
                        pstmt.setString(2, lottoNumbers);
                };
                JdbcTemplate template = new JdbcTemplate();
                template.executeUpdate(INSERT_LOTTO_QUERRY, pss);
        }

        public List<String> selectLottos(int id) {
                PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, id);

                RowMapper rm = rs -> {
                        List<String> lottos = new ArrayList<>();
                        while(rs.next()){
                                lottos.add(rs.getString("number"));
                        }
                        if (lottos.size() == 0) {
                                return new IllegalArgumentException("존재 하지 않는 id");
                        }
                        return lottos;
                };

                JdbcTemplate template = new JdbcTemplate();
                return (List<String>)template.executeQuery(SELECT_LOTTOS_QUERY, pss, rm);
        }
}
