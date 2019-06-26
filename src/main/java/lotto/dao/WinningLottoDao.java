package lotto.dao;

import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLottoDao {
    private static WinningLottoDao WinningLottoDAO;

    private WinningLottoDao() {
    }

    public static WinningLottoDao getInstance() {
        if (WinningLottoDAO == null) {
            WinningLottoDAO = new WinningLottoDao();
        }
        return WinningLottoDAO;
    }

    public void insertWinningLotto(int round, List<Integer> lottoNumbers, int bonusNumber) {
        UpdateJdbcTemplate template = new UpdateJdbcTemplate();
        String query = "insert into winninglotto values(?,?,?,?,?,?,?,?)";
        List<Integer> parameters = new ArrayList<>();
        parameters.add(round);
        parameters.addAll(lottoNumbers);
        parameters.add(bonusNumber);

        try {
            template.updateQuery(query, parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> findWinningLottoByRound(int round) {
        SelectJdbcTemplate<Map<String, String>> template = new SelectJdbcTemplate<Map<String, String>>() {
            @Override
            public Map<String, String> getResult(ResultSet resultSet) throws SQLException {
                Map<String, String> winningLotto = new HashMap<>();

                while (resultSet.next()) {
                    List<String> lotto = new ArrayList<>();
                    for (int i = 1; i < 7; i++) {
                        lotto.add(resultSet.getString(i + 1));
                    }
                    winningLotto.put("winningLotto", StringUtils.join(lotto, ","));
                    winningLotto.put("bonusNumber", resultSet.getString(8));
                }
                return winningLotto;
            }
        };
        String query = "select * from winninglotto where round = ?";
        List<Integer> parameters = new ArrayList<>();
        parameters.add(round);

        try {
            return template.executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
