package lotto.dao;

import lotto.dao.Exception.LottoTicketDAOException;
import lotto.domain.Lotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LottoTicketDAO {
    private static LottoTicketDAO lottoDAO;
    private Connection con;

    private LottoTicketDAO() {
        con = LottoDAOConnector.getConnection();
    }

    public static LottoTicketDAO getInstance() {
        return lottoDAO == null ? new LottoTicketDAO() : lottoDAO;
    }

    public void addLotto(int round, Lotto lotto) {
        try {
            String query = "INSERT INTO lotto VALUE(?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, round);
            pstmt.setString(2, lotto.toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new LottoTicketDAOException(e);
        }
    }


}
