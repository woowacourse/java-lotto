package lotto;

import lotto.domain.lottomanager.LottoNumber;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DBUtils {
    private static final String GET_ROUND_QUERY = "select round from lottoGame order by round desc limit 1";
    private static final int ROUND_COLUMN = 1;
    private static final int FALSE_VALUE = -1;
    private static final int NUMBER_START_COLUMN = 2;

    public static Connection getConnection() {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "ike_TechCourseDB"; // MySQL DATABASE 이름
        String userName = "ike"; //  MySQL 서버 아이디
        String password = "754813as!@"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public static int getRound(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(GET_ROUND_QUERY);
        ResultSet resultSet = pstmt.executeQuery();

        int round = FALSE_VALUE;
        if (resultSet.next()) {
            round = resultSet.getInt(ROUND_COLUMN);
        }

        return round;
    }

    public static List<String> getLottoNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .map(Objects::toString)
                .collect(Collectors.toList());
    }

    public static void setLottoNumbers(PreparedStatement pstmt, List<String> lottoNumbers) throws SQLException {
        for (int i = 0; i < lottoNumbers.size(); i++) {
            pstmt.setString(i + NUMBER_START_COLUMN, lottoNumbers.get(i));
        }
    }
}
