package lotto.domain.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static lotto.domain.DAO.DBUtil.getConnection;

public class UserLottoDAO {
    public static void addUserLottoNumbers(String userLottoNumbers, int lottoRound) throws SQLException {
        String query = "INSERT INTO user_lotto_numbers(userLottoNumbers,lottoRound) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userLottoNumbers);
        pstmt.setInt(2, lottoRound);
        pstmt.executeUpdate();
    }

    public static int getCurrentLottoRound() throws SQLException {
        String query = "SELECT MAX(lottoRound) FROM user_lotto_numbers";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return 0;
        return rs.getInt("MAX(lottoRound)");
    }

    public static void deleteUserLottoNumbersByLottoRound(int lottoRound) throws SQLException {
        String query = "DELETE FROM user_lotto_numbers where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, lottoRound);
        pstmt.executeUpdate();
    }

    public static void selectUserLottoNumbersByCurrentRound(Map<String, Object> model, int inquiredRound) throws SQLException {
        Connection con = DBUtil.getConnection();
        String query = "SELECT * FROM user_lotto_numbers where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, inquiredRound);
        ResultSet rs = pstmt.executeQuery();
        List<String> userLottoNumbers = new ArrayList<>();
        if (!rs.next()) return;
        while (rs.next()) {
            userLottoNumbers.add(rs.getString("userLottoNumbers"));
        }
        model.put("userLottoNumbers", userLottoNumbers);
        DBUtil.closeConnection(con);
    }

}
