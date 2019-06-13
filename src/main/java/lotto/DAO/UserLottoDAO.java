package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserLottoDAO {
    private static UserLottoDAO userLottoDAO;
    private Connection con;

    private UserLottoDAO() {
        con = DAOConnector.getConnection();
    }

    public static UserLottoDAO getInstance() {
        if (userLottoDAO == null) {
            userLottoDAO = new UserLottoDAO();
        }
        return userLottoDAO;
    }

    public void insertLotto(int round, List<Integer> lottoNumbers) {
        PreparedStatement pstmt = null;
        try {
            String query = "insert into userlotto values(?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);
            for (int i = 0; i < lottoNumbers.size(); i++) {
                pstmt.setInt(i + 2, lottoNumbers.get(i));
            }
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
