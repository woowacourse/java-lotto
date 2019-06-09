package lotto.dao;

import lotto.domain.LottoResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static lotto.domain.Rank.*;

public class LottoResultDAO {
    private final Connection con;

    public LottoResultDAO(Connection connection) {
        this.con = connection;
    }

    public void addLottoResult(String lottoId, LottoResult lottoResult) throws SQLException {
        String query = "INSERT INTO lottoresult VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoId);
        pstmt.setString(2, formatLottoResult(lottoResult));
        pstmt.setString(3, lottoResult.getEarning().toString());
        pstmt.setString(4, lottoResult.getEarningsRate().toString());
        pstmt.executeUpdate();
    }

    private String formatLottoResult(LottoResult lottoResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("3개 일치 (5,000원) - " + lottoResult.getCountOfRank(FIFTH) + "개\n");
        stringBuilder.append("4개 일치 (50,000원) - " + lottoResult.getCountOfRank(FOURTH) + "개\n");
        stringBuilder.append("5개 일치 (1,500,000원) - " + lottoResult.getCountOfRank(THIRD) + "개\n");
        stringBuilder.append("5개 일치,보너스 볼 일치 (30,000,000원) - " + lottoResult.getCountOfRank(SECOND) + "개\n");
        stringBuilder.append("6개 일치 (2,000,000,000원) - " + lottoResult.getCountOfRank(FIRST) + "개");
        return stringBuilder.toString();
    }
}
