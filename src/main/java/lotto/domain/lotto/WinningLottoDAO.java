package lotto.domain.lotto;

import lotto.utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoDAO {
    public int addWinningLotto(WinningLotto winningLotto) throws SQLException {
        List<Integer> winningLottoNumbers = Arrays.stream(splitLottoNumbers(winningLotto))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String query = "INSERT INTO winning_lotto(winning_number_1, winning_number_2,winning_number_3," +
                "winning_number_4, winning_number_5, winning_number_6, bonus_number) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = DBUtils.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, winningLottoNumbers.get(0));
        pstmt.setInt(2, winningLottoNumbers.get(1));
        pstmt.setInt(3, winningLottoNumbers.get(2));
        pstmt.setInt(4, winningLottoNumbers.get(3));
        pstmt.setInt(5, winningLottoNumbers.get(4));
        pstmt.setInt(6, winningLottoNumbers.get(5));
        pstmt.setInt(7, Integer.parseInt(winningLotto.getBonusNumber().toString()));
        pstmt.executeUpdate();

        ResultSet resultSet = pstmt.getGeneratedKeys();

        if (!resultSet.next()) {
            return -1;
        }

        return resultSet.getInt(1);
    }

    private String[] splitLottoNumbers(WinningLotto winningLotto) {
        return winningLotto.getWinningLotto()
                .toString()
                .replace("[", "")
                .replace("]", "")
                .split(",");
    }
}
