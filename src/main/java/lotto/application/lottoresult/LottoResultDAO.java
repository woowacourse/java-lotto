package lotto.application.lottoresult;

import lotto.application.LottoJDBCTemplate;
import lotto.domain.lottoresult.dto.LottoStatisticsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoResultDAO {
    private static LottoResultDAO lottoResultDAO = null;

    private LottoResultDAO() {
    }

    public static LottoResultDAO getInstance() {
        if (lottoResultDAO == null) {
            lottoResultDAO = new LottoResultDAO();
        }
        return lottoResultDAO;
    }

    public void createNextRound() {
        String query = "INSERT INTO lotto_result(round) values(?)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(0);

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        lottoJDBCTemplate.executeUpdate(query, queryValues);
    }

    public int getLatestRoundNum() {
        String query = "SELECT round FROM lotto_result ORDER BY round DESC LIMIT 1";
        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();

        List<Map<String, Object>> results = lottoJDBCTemplate.executeQuery(query);
        if (results.isEmpty()) {
            return 0;
        }
        Map<String, Object> resultRow = results.get(0);
        return (int) resultRow.get("round");
    }

    public void deleteRound(int round) {
        String query = "delete from lotto_result where round = ?";
        String queryForIncrement = "ALTER TABLE lotto_result AUTO_INCREMENT = ?";

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        lottoJDBCTemplate.executeUpdate(query, queryValues);
        lottoJDBCTemplate.executeUpdate(queryForIncrement, queryValues);
    }

    public void saveLottoStatistics(int round, LottoStatisticsDTO lottoStatisticsDTO) {
        String query = "update lotto_result set"
                + " counts_of_first_rank = ?, counts_of_second_rank = ?, counts_of_third_rank = ?,"
                + " counts_of_fourth_rank = ?, counts_of_fifth_rank = ?, profit_ratio = ?"
                + " WHERE round = ?";

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(lottoStatisticsDTO.getCountsOfFirstRank());
        queryValues.add(lottoStatisticsDTO.getCountsOfSecondRank());
        queryValues.add(lottoStatisticsDTO.getCountsOfThirdRank());
        queryValues.add(lottoStatisticsDTO.getCountsOfFourthRank());
        queryValues.add(lottoStatisticsDTO.getCountsOfFifthRank());
        queryValues.add(lottoStatisticsDTO.getProfitRatio());
        queryValues.add(round);

        lottoJDBCTemplate.executeUpdate(query, queryValues);
    }

    public LottoStatisticsDTO fetchLottoStatisticsDto(int round) {
        String query = "SELECT * FROM lotto_result WHERE round = ?";
        LottoStatisticsDTO lottoStatisticsDTO = new LottoStatisticsDTO();

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(round);

        List<Map<String, Object>> results = lottoJDBCTemplate.executeQuery(query, queryValues);
        Map<String, Object> resultRow = results.get(0);
        lottoStatisticsDTO.setCountsOfFirstRank((int) resultRow.get("counts_of_first_rank"));
        lottoStatisticsDTO.setCountsOfSecondRank((int) resultRow.get("counts_of_second_rank"));
        lottoStatisticsDTO.setCountsOfThirdRank((int) resultRow.get("counts_of_third_rank"));
        lottoStatisticsDTO.setCountsOfFourthRank((int) resultRow.get("counts_of_fourth_rank"));
        lottoStatisticsDTO.setCountsOfFifthRank((int) resultRow.get("counts_of_fifth_rank"));
        lottoStatisticsDTO.setProfitRatio((double) resultRow.get("profit_ratio"));
        return lottoStatisticsDTO;
    }
}

