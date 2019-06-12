package lotto.utils;

import lotto.domain.Lotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static Lotto getLottoInDB(ResultSet rs, String colName) throws SQLException {
        List<Integer> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(rs.getInt(colName + "_1"));
        lottoNumbers.add(rs.getInt(colName + "_2"));
        lottoNumbers.add(rs.getInt(colName + "_3"));
        lottoNumbers.add(rs.getInt(colName + "_4"));
        lottoNumbers.add(rs.getInt(colName + "_5"));
        lottoNumbers.add(rs.getInt(colName + "_6"));

        return new Lotto(lottoNumbers);
    }

    public static void setLottoInDB(PreparedStatement pstmt, Lotto lotto) throws SQLException {
        pstmt.setInt(1, lotto.getLottoNumber(0));
        pstmt.setInt(2, lotto.getLottoNumber(1));
        pstmt.setInt(3, lotto.getLottoNumber(2));
        pstmt.setInt(4, lotto.getLottoNumber(3));
        pstmt.setInt(5, lotto.getLottoNumber(4));
        pstmt.setInt(6, lotto.getLottoNumber(5));
    }
}
