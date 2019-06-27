package lotto.domain.dao;

import lotto.DBUtils;
import lotto.domain.lottomanager.LottoNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class LottoDaoUtils {
    private static final int ROUND_COLUMN = 1;
    private static final int FALSE_VALUE = -1;
    private static final int NUMBER_START_COLUMN = 2;
    private static final String GET_ROUND_QUERY = "select round from lottoGame order by round desc limit 1";

    int getRound() {
        try {
            ResultSet resultSet = getDBRound();
            int round = FALSE_VALUE;
            if (resultSet.next()) {
                round = resultSet.getInt(ROUND_COLUMN);
            }
            return round;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet getDBRound() {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(GET_ROUND_QUERY);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<String> getLottoNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .map(Objects::toString)
                .collect(Collectors.toList());
    }

    void setLottoNumbers(PreparedStatement pstmt, List<String> lottoNumbers) {
        try {
            for (int i = 0; i < lottoNumbers.size(); i++) {
                pstmt.setString(i + NUMBER_START_COLUMN, lottoNumbers.get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException("setLottoNumbers() : " + e.getMessage());
        }
    }
}
