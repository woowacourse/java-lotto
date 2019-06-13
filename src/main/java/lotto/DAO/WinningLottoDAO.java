package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WinningLottoDAO {
    private static WinningLottoDAO WinningLottoDAO;
    private Connection con;

    private WinningLottoDAO() {
        con = DAOConnector.getConnection();
    }

    public static WinningLottoDAO getInstance() {
        if (WinningLottoDAO == null) {
            WinningLottoDAO = new WinningLottoDAO();
        }
        return WinningLottoDAO;
    }


    public void insertWinningLotto(int round, List<Integer> lottoNumbers, int bonusNumber) {
        PreparedStatement pstmt = null;
        try {
            String query = "insert into winninglotto values(?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);
            for (int i = 0; i < lottoNumbers.size(); i++) {
                pstmt.setInt(i + 2, lottoNumbers.get(i));
            }
            pstmt.setInt(8, bonusNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
