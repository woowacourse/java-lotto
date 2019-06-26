package lotto.dao;

import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserLottoDao {
    private static UserLottoDao userLottoDAO;

    private UserLottoDao() {
    }

    public static UserLottoDao getInstance() {
        if (userLottoDAO == null) {
            userLottoDAO = new UserLottoDao();
        }
        return userLottoDAO;
    }

    public void insertLotto(int round, List<Integer> lottoNumbers) {
        UpdateJdbcTemplate template = new UpdateJdbcTemplate();
        String query = "insert into userlotto values(?,?,?,?,?,?,?)";
        List<Integer> parameters = new ArrayList<>();
        parameters.add(round);
        parameters.addAll(lottoNumbers);

        try {
            template.updateQuery(query, parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> findLottosByRound(int round) {
        SelectJdbcTemplate<List<String>> template = new SelectJdbcTemplate<List<String>>() {
            @Override
            public List<String> getResult(ResultSet resultSet) throws SQLException {
                List<String> lottoNumbers = new ArrayList<>();

                while (resultSet.next()) {
                    List<String> lotto = new ArrayList<>();
                    for (int i = 1; i < 7; i++) {
                        lotto.add(resultSet.getString(i + 1));
                    }
                    lottoNumbers.add(StringUtils.join(lotto, ","));
                }
                return lottoNumbers;
            }
        };

        String query = "select * from userlotto where round = ?";
        List<Integer> parameters = new ArrayList<>();
        parameters.add(round);
        try {
            return template.executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
