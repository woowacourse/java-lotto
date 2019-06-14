package lotto.domain.lotto;

import lotto.utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoDAO {
    public void addLotto(Lotto lotto, int round) throws SQLException {
        List<Integer> lottoNumbers = Arrays.stream(lotto.toString()
                .replace("[", "")
                .replace("]", "")
                .split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String query = "INSERT INTO lotto(round, lotto_number_1,lotto_number_2,lotto_number_3," +
                "lotto_number_4,lotto_number_5,lotto_number_6) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = DBUtils.getConnection().prepareStatement(query);

        pstmt.setInt(1, round);
        pstmt.setInt(2, lottoNumbers.get(0));
        pstmt.setInt(3, lottoNumbers.get(1));
        pstmt.setInt(4, lottoNumbers.get(2));
        pstmt.setInt(5, lottoNumbers.get(3));
        pstmt.setInt(6, lottoNumbers.get(4));
        pstmt.setInt(7, lottoNumbers.get(5));
        pstmt.executeUpdate();
    }
}
