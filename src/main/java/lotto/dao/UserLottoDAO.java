package lotto.dao;

import lotto.domain.Lotteries;
import lotto.domain.Lotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author heebg
 * @version 1.0 2019-06-12
 */
public class UserLottoDAO {
    private static final String table = "user_lotto";

    public static Connection getConnection() {
        String server = "localhost";
        String database = "db_lotto";
        String userName = "heebong";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(" JDBC Driver load error");
            e.printStackTrace();
            return null;
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
        } catch (SQLException e) {
            System.out.println("connection error : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void addUserLotto(Lotto lotto, int turn) throws SQLException {
        String queryString = "insert into " + table + "(turn, lotto_one, lotto_two, lotto_three, lotto_four, lotto_five, lotto_six) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = getConnection().prepareStatement(queryString);
        pstmt.setInt(1, turn);
        pstmt.setString(2, lotto.get(0).toString());
        pstmt.setString(3, lotto.get(1).toString());
        pstmt.setString(4, lotto.get(2).toString());
        pstmt.setString(5, lotto.get(3).toString());
        pstmt.setString(6, lotto.get(4).toString());
        pstmt.setString(7, lotto.get(5).toString());

        pstmt.execute();
    }

    public static void addUserLotteries(Lotteries lotteries, int turn) throws SQLException {
        for (Lotto lottery : lotteries) {
            addUserLotto(lottery, turn);
        }
    }
}
