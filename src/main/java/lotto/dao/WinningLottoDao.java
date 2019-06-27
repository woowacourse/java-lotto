package lotto.dao;

import lotto.dto.WinningLottoDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WinningLottoDao {
    private static WinningLottoDao dao;

    private WinningLottoDao() {

    }

    public static WinningLottoDao getDao() {
        if (dao == null) {
            dao = new WinningLottoDao();
            return dao;
        }
        return dao;
    }

    public WinningLottoDto selectWinningLotto(int round) {
        String sql = "SELECT num_1,num_2,num_3,num_4,num_5,num_6,num_bonus FROM winning_lotto where round_id = ?";
        QueryManager manager = QueryManager.getManager();
        List<Map<String, Integer>> result = manager.executeQuery(sql, Arrays.asList(round));
        List<Integer> numbers = new ArrayList<>(result.get(0).values());
        int bonus = numbers.get(6);
        numbers = numbers.subList(0, 6);
        return new WinningLottoDto(numbers, bonus);
    }

    public void insertWinningLotto(int round, WinningLottoDto dto) {
        String sql = "INSERT INTO winning_lotto(num_1,num_2,num_3,num_4,num_5,num_6,num_bonus,round_id) VALUES(?,?,?,?,?,?,?,?)";
        QueryManager manager = QueryManager.getManager();
        List<Integer> params = new ArrayList<>(dto.getNumbers());
        params.add(dto.getBonus());
        params.add(round);
        manager.executeUpdate(sql, params);
    }

    public WinningLottoDto currentWinningLotto() {
        return selectWinningLotto(QueryManager.lastRound());
    }
}
