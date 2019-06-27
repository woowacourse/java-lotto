package lotto.domain.dao;

import lotto.domain.dto.ResultDTO;

import java.util.*;

public class ResultDAOImpl implements ResultDAO {

    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();
    private static final ResultDAOImpl INSTANCE = new ResultDAOImpl();

    private ResultDAOImpl() {
    }

    public static ResultDAOImpl getInstance() {
        return INSTANCE;
    }

    public void setResult(final ResultDTO resultDTO) {
        String query = "INSERT INTO result " +
                "(round, first_prize, second_prize, third_prize, forth_prize, fifth_prize, profit_rate, money) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        List<String> args = new ArrayList<>(Arrays.asList(
                String.valueOf(resultDTO.getRound()),
                String.valueOf(resultDTO.getFirstPrize()),
                String.valueOf(resultDTO.getSecondPrize()),
                String.valueOf(resultDTO.getThirdPrize()),
                String.valueOf(resultDTO.getForthPrize()),
                String.valueOf(resultDTO.getFifthPrize()),
                String.valueOf(resultDTO.getProfitRate()),
                String.valueOf(resultDTO.getMoney())
        ));
        JDBC_TEMPLATE.updateQuery(query, args);
    }

    public ResultDTO getResult(final int newRound) {
        ResultDTO resultDTO = new ResultDTO();

        String query = "SELECT * FROM result WHERE round = ?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(newRound)));
        List<Map<String, String>> results = JDBC_TEMPLATE.selectQuery(query, args);

        for (Map<String, String> result : results) {
            resultDTO.setRound(Integer.valueOf(result.get("round")));
            resultDTO.setFirstPrize(Integer.valueOf(result.get("first_prize")));
            resultDTO.setSecondPrize(Integer.valueOf(result.get("second_prize")));
            resultDTO.setThirdPrize(Integer.valueOf(result.get("third_prize")));
            resultDTO.setForthPrize(Integer.valueOf(result.get("forth_prize")));
            resultDTO.setFifthPrize(Integer.valueOf(result.get("fifth_prize")));
            resultDTO.setProfitRate(Double.valueOf(result.get("profit_rate")));
            resultDTO.setMoney(Integer.valueOf(result.get("money")));
        }

        return resultDTO;
    }

    public void deleteResult(final int round) {
        String query = "DELETE FROM result WHERE round = ?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(round)));
        JDBC_TEMPLATE.updateQuery(query, args);
    }
}
