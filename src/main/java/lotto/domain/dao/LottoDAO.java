package lotto.domain.dao;

import lotto.domain.dto.LottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.NumberSet;
import lotto.domain.utils.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO {

    public void addLotto(final LottoDTO lottoDTO) {
        try {
            Connection con = ConnectionGenerator.getConnection();
            String query = "INSERT INTO lotto " +
                    "(round, first_num, second_num, third_num, forth_num, fifth_num, sixth_num) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, lottoDTO.getRound());
            pstmt.setInt(2, lottoDTO.getFirstNum().getNumber());
            pstmt.setInt(3, lottoDTO.getSecondNum().getNumber());
            pstmt.setInt(4, lottoDTO.getThirdNum().getNumber());
            pstmt.setInt(5, lottoDTO.getForthNum().getNumber());
            pstmt.setInt(6, lottoDTO.getFifthNum().getNumber());
            pstmt.setInt(7, lottoDTO.getSixthNum().getNumber());

            pstmt.executeUpdate();
            ConnectionGenerator.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<LottoDTO> getPurchasedLotto(final int round) throws SQLException {
        String query = "SELECT * FROM lotto WHERE round = ?";
        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        List<LottoDTO> purchasedLottos = new ArrayList<>();
        while(rs.next()) {
            LottoDTO lottoDTO = new LottoDTO();
            lottoDTO.setRound(rs.getInt("round"));
            lottoDTO.setFirstNum(NumberSet.of(rs.getInt("first_num")));
            lottoDTO.setSecondNum(NumberSet.of(rs.getInt("second_num")));
            lottoDTO.setThirdNum(NumberSet.of(rs.getInt("third_num")));
            lottoDTO.setForthNum(NumberSet.of(rs.getInt("forth_num")));
            lottoDTO.setFifthNum(NumberSet.of(rs.getInt("fifth_num")));
            lottoDTO.setSixthNum(NumberSet.of(rs.getInt("sixth_num")));
            purchasedLottos.add(lottoDTO);
        }

        return purchasedLottos;
    }

    public void deleteLotto(final int round) throws SQLException {
        String query = "DELETE FROM lotto WHERE round = ?";
        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.executeUpdate();
    }
}
