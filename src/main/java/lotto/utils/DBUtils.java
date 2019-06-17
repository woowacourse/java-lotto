package lotto.utils;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DBUtils {
    private DBUtils() {
    }

    public static Connection getConnection() {
        Connection conn = null;
        String server = "localhost";
        String database = "lotto_game";
        String userName = "done";
        String password = "1234";

        loadDriver();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
        }

        return conn;
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setLottoNumber(PreparedStatement pstmt, Lotto lotto, int startIndex) throws SQLException {
        List<Integer> lottoNumbers = parseLottoNumbers(lotto);

        int index = 0;
        for (int i = startIndex; i < startIndex + Lotto.LOTTO_NUMBER_SIZE; i++) {
            pstmt.setInt(i, lottoNumbers.get(index));
            index++;
        }
    }

    private static List<Integer> parseLottoNumbers(Lotto lotto) {
        return lotto.getLottoNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
