package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.dto.LottoDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SERVER = "localhost";
    private static final String DATABASE = "lotto";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final String URL_FORMAT = "jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC";
    private static final int BOUGHT_LOTTO_NUMBER_FROM_INDEX = 0;
    private static final int BOUGHT_LOTTO_NUMBER_TO_INDEX = 6;

    public Connection getConnection() {
        loadDriver();
        String url = String.format(URL_FORMAT, SERVER, DATABASE);
        Connection con = connectDriver(url);
        return con;
    }

    private void loadDriver() {
        try {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connectDriver(final String url) {
        try {
            return DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lotto> findAllBoughtLottoByRound(int round) throws SQLException {
        String sql = "SELECT * FROM bought_lotto WHERE round_id = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = getConnection().prepareStatement(sql);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();
        while (rs.next()) {
            lottos.add(new Lotto(getLottoNumbers(rs)));
        }
        closeConnection(con);
        return lottos;
    }

    private List<LottoNumber> getLottoNumbers(final ResultSet rs) throws SQLException {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = BOUGHT_LOTTO_NUMBER_FROM_INDEX + 2; i < BOUGHT_LOTTO_NUMBER_TO_INDEX + 2; i++) {
            int lottoNumber = rs.getInt(i);
            lottoNumbers.add(LottoNumber.getLottoNumber(lottoNumber));
        }
        return lottoNumbers;
    }

    public void addAllLotto(final List<LottoDto> lottoDtos, int round) throws SQLException {
        String sql = "INSERT INTO bought_lotto VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        lottoQueryExcute(lottoDtos, round, pstmt);
        closeConnection(con);
    }

    private void lottoQueryExcute(final List<LottoDto> lottoDtos, final int round,
                                  final PreparedStatement pstmt) throws SQLException {
        for (int i = 0; i < lottoDtos.size(); i++) {
            setIntFrom(lottoDtos, pstmt, i);
            pstmt.setInt(7, round);
            pstmt.executeUpdate();
        }
    }

    private void setIntFrom(final List<LottoDto> lottoDtos, final PreparedStatement pstmt,
                            final int dtoIndex) throws SQLException {
        for (int i = BOUGHT_LOTTO_NUMBER_FROM_INDEX; i < BOUGHT_LOTTO_NUMBER_TO_INDEX; i++) {
            pstmt.setInt(i + 1, lottoDtos.get(dtoIndex).getLottoNumbers().get(i));
        }
    }

    public void removeAllLotto(final List<LottoDto> lottoDtos, int round) throws SQLException {
        String sql = "DELETE FROM bought_lotto WHERE num1 = ? AND num2 = ? AND num3 = ? " +
                "AND num4 = ? AND num5 = ? AND num6 = ? AND round_id = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        lottoQueryExcute(lottoDtos, round, pstmt);
    }
}
