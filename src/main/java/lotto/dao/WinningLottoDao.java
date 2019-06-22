package lotto.dao;

import lotto.domain.CustomLottoGenerator;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.dto.WinningLottoDto;
import lotto.utils.NumberUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDao extends Dao {

    public WinningLotto selectWinningLotto(String round) throws SQLException {
        String query = "SELECT round, numbers, bonus FROM winningLotto WHERE round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet re = pstmt.executeQuery();
        re.next();

        Lotto lotto = new CustomLottoGenerator(NumberUtil.parsingNumber(re.getString("numbers").split(","))).makeLotto();
        LottoNumber lottoNumber = LottoNumber.generateNumber(Integer.parseInt(re.getString("bonus")));

        return new WinningLotto(lotto, lottoNumber);
    }

    public void insertWinningLotto(WinningLottoDto winningLottoDto) throws SQLException {
        String query = "INSERT INTO winningLotto (round, numbers, bonus) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, winningLottoDto.getRound());
        pstmt.setString(2, winningLottoDto.getNumbers());
        pstmt.setString(3, winningLottoDto.getBonusNumber());

        pstmt.executeUpdate();
    }
}
