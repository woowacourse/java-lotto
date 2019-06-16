package lotto.dao;

import lotto.dao.Exception.LottoTicketDAOException;

import lotto.dto.LottoTicketDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoTicketDAO {
    private static LottoTicketDAO lottoTicketDAO;
    private static Connection con;

    private LottoTicketDAO() {
        con = LottoDAOConnector.getConnection();
    }

    public static LottoTicketDAO getInstance() {
        return lottoTicketDAO == null ? new LottoTicketDAO() : lottoTicketDAO;
    }

    public int addLotto(LottoTicketDTO lottoTicketDTO) {
        String query = "INSERT INTO lottos (round,lotto) VALUE(?,?)";
        return (int) lottoTicketDTO.getLottos().stream()
                .map(lotto -> {
                    try {
                        PreparedStatement pstmt = con.prepareStatement(query);
                        pstmt.setInt(1, lottoTicketDTO.getRound());
                        pstmt.setString(2, lotto);
                        return pstmt.executeUpdate();
                    } catch (SQLException e) {
                        throw new LottoTicketDAOException(e);
                    }
                }).count();
    }

    public static List<String> findByLottoTicket(LottoTicketDTO lottoTicketDTO) {
        try {
            String query = "SELECT * FROM lottos WHERE round = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, lottoTicketDTO.getRound());

            ResultSet rs = pstmt.executeQuery();
            List<String> lottos = new ArrayList<>();
            while (rs.next()) {
                lottos.add(rs.getString("lotto"));
            }
            return lottos;
        } catch (SQLException e) {
            throw new LottoTicketDAOException(e);
        }
    }

}
