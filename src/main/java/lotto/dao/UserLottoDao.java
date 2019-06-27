package lotto.dao;

import lotto.dto.UserLottoDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserLottoDao {
    private static UserLottoDao dao;

    private UserLottoDao() {
    }

    public static UserLottoDao getDao() {
        if (dao == null) {
            dao = new UserLottoDao();
            return dao;
        }
        return dao;
    }

    public void insertUserLottos(UserLottoDto dto) {
        increaseRound();
        String sql = "INSERT INTO user_lotto(num_1,num_2,num_3,num_4,num_5,num_6) VALUES(?,?,?,?,?,?)";
        QueryManager manager = QueryManager.getManager();
        for (List<Integer> number : dto.getNumbers()) {
            manager.executeUpdate(sql, number);
        }
    }

    private void increaseRound() {
        String sql = "INSERT INTO lotto_game(round) VALUES(SELECT MAX(round) + 1 FROM lotto_game)";
        QueryManager manager = QueryManager.getManager();
        manager.executeQuery(sql);
    }

    public void insertUserLottos(int round, UserLottoDto dto) {
        String sql = "INSERT INTO user_lotto(num_1,num_2,num_3,num_4,num_5,num_6,round_id) VALUES(?,?,?,?,?,?,?)";
        QueryManager manager = QueryManager.getManager();
        for (List<Integer> number : dto.getNumbers()) {
            number.add(round);
            manager.executeUpdate(sql, number);
        }
    }

    public UserLottoDto selectUserLottos(int round) {
        String sql = "SELECT * FROM user_lotto WHERE round_id = ?";
        QueryManager manager = QueryManager.getManager();
        List<List<Integer>> numbers = new ArrayList<>();
        for (Map<String, Integer> map : manager.executeQuery(sql, Arrays.asList(round))) {
            numbers.add(new ArrayList<>(map.values()));
        }
        UserLottoDto dto = new UserLottoDto();
        dto.setNumbers(numbers);
        return dto;
    }

    public UserLottoDto currentUserLottos() {
        return selectUserLottos(QueryManager.lastRound());
    }
}
