package lotto.dao;

import lotto.domain.Rank;
import lotto.dto.LottoResultDto;

import java.math.BigDecimal;
import java.util.*;

public class LottoResultDao {

    private static LottoResultDao dao;

    private LottoResultDao() {

    }

    public static LottoResultDao getDao() {
        if (dao == null) {
            dao = new LottoResultDao();
            return dao;
        }
        return dao;
    }

    public void insertResult(int round, LottoResultDto dto) {
        String sql = "INSERT INTO result(first,second,third,fourth,fifth,lose,round_id) " +
                "VALUES(?,?,?,?,?,?,?)";
        QueryManager manager = QueryManager.getManager();
        List<Integer> params = new ArrayList<>(dto.getResults().values());
        params.add(round);
        manager.executeUpdate(sql, params);
    }

    public LottoResultDto selectResult(int round) {
        String sql = "SELECT first, second, third, fourth, fifth, lose, summary FROM result where round_id = ?";
        QueryManager manager = QueryManager.getManager();

        Map<Rank, Integer> rankMap = new TreeMap<>();
        Map<String, Integer> result = manager.executeQuery(sql, Arrays.asList(round)).get(0);
        rankMap.put(Rank.FIRST, result.get("first"));
        rankMap.put(Rank.SECOND, result.get("second"));
        rankMap.put(Rank.THIRD, result.get("third"));
        rankMap.put(Rank.FOURTH, result.get("fourth"));
        rankMap.put(Rank.FIFTH, result.get("fifth"));
        rankMap.put(Rank.FIFTH, result.get("lose"));
        return new LottoResultDto(rankMap, BigDecimal.valueOf(result.get("summary")));
    }
}
