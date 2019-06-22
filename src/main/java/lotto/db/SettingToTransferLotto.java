package lotto.db;

import lotto.domain.Lotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingToTransferLotto {

    public static Lotto getLottoInDB(ResultSet rs, String colName) throws SQLException {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(rs.getInt(colName + "_" + i));
        }

        return new Lotto(lottoNumbers);
    }

    public static void setLottoInDB(PreparedStatement pstmt, Lotto lotto) throws SQLException {
        for (int i = 1; i <= 6; i++) {
            pstmt.setInt(i, lotto.getLottoNumber(i - 1));
        }
    }
}
