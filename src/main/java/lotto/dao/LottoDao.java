package lotto.dao;

import lotto.domain.lotto.Lotto;
import lotto.dto.LottoDto;
import lotto.utils.LottoNoParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {
    private Connection con;

    public LottoDao(Connection con) {
        this.con = con;
    }

    public void addLotto(LottoDto lottoDto) throws SQLException {
        String query = "INSERT INTO 구매로또 (회차, 로또번호) VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, findRoundNo() + 1);
        pstmt.setString(2, lottoDto.getLottoNo());
        pstmt.executeUpdate();
    }

    public int findRoundNo() throws SQLException {
        String query = "SELECT 회차 FROM 당첨로또 ORDER BY 회차 DESC LIMIT 1";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return 0;
        return rs.getInt("회차");
    }

    public List<LottoDto> getLottosInThisRound() throws SQLException {
        String query = "SELECT 로또번호 FROM 구매로또 WHERE 회차 = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, findRoundNo());
        ResultSet rs = pstmt.executeQuery();

        return getLottoDtos(rs);
    }

    private List<LottoDto> getLottoDtos(ResultSet rs) throws SQLException {
        List<LottoDto> lottoDtos = new ArrayList<>();
        if (!rs.next())
            return lottoDtos;
        do {
            lottoDtos.add(
                    Lotto.of(LottoNoParser.parseToLottoNos(rs.getString("로또번호")))
                            .createLottoDto());
        } while (rs.next());

        return lottoDtos;
    }

    public List<LottoDto> getLottos(int round) throws SQLException {
        List<LottoDto> lottoDtos = new ArrayList<>();
        String query = "SELECT * FROM 구매로또 WHERE 회차 = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return lottoDtos;
        do {
            LottoDto lottoDto = new LottoDto(rs.getInt(2),
                    rs.getString(3));
            lottoDtos.add(lottoDto);
        } while (rs.next());
        return lottoDtos;
    }
}
