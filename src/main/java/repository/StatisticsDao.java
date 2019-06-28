package repository;

import domain.Rank;
import domain.Statistics;
import domain.WinningLotto;

import javax.xml.transform.Templates;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static domain.Rank.*;

public class StatisticsDao {
    private static StatisticsDao statisticsDao = null;

    private StatisticsDao() {
    }

    public static StatisticsDao getInstance() {
        if (statisticsDao == null) {
            statisticsDao = new StatisticsDao();
        }
        return statisticsDao;
    }

    public int fetchLastTrial() {
        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        String query = "SELECT trial_number FROM statistics ORDER BY trial_number DESC LIMIT 1";

        List<Map<String, Object>> results = lottoJDBCTemplate.executeQuery(query);
        if (results.isEmpty()) {
            return 0;
        }
        Map<String, Object> resultRow = results.get(0);
        return (int) resultRow.get("trial_number");
    }

    public void save(Statistics statistics, WinningLotto winningLotto, double calculateEarningRates) {
        String query = "INSERT INTO statistics(winning_numbers, bonus_number, counts_of_first, counts_of_second, counts_of_third"
                + ", counts_of_fourth, counts_of_fifth, counts_of_miss, earning_rates)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        LottoJDBCTemplate lottoJDBCTemplate = LottoJDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(winningLotto.toString());
        queryValues.add(winningLotto.getBonusNumber().toString());
        queryValues.add(statistics.countsOf(FIRST));
        queryValues.add(statistics.countsOf(SECOND));
        queryValues.add(statistics.countsOf(THIRD));
        queryValues.add(statistics.countsOf(FOURTH));
        queryValues.add(statistics.countsOf(FIFTH));
        queryValues.add(statistics.countsOf(MISS));
        queryValues.add(calculateEarningRates);

        lottoJDBCTemplate.executeUpdate(query, queryValues);
    }
}
