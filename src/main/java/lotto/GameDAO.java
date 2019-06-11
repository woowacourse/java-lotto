package lotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.utils.InputParser;

public class GameDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost";
        String database = "lotto";
        String userName = "luffy";
        String password = "159456";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Dirver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&serverTimezone=UTC", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addGameInformation(GameDTO gameDTO) throws SQLException {
        String query = "INSERT INTO games(winning_numbers, bonus, result, return_amount, return_rate) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameDTO.getWinningNumbers().toString());
        pstmt.setString(2, gameDTO.getBonusNumber().toString());
        pstmt.setString(3, gameDTO.getResult());
        pstmt.setString(4, gameDTO.getReturnAmount());
        pstmt.setString(5, gameDTO.getReturnRate());
        pstmt.executeUpdate();
    }

    public void addLottoNumbers(List<Lotto> lottoNumbers) throws SQLException {
        int maxId = getMaxId();
        String query = "INSERT INTO lottos(games_id, numbers) VALUES(?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        for (Lotto lotto : lottoNumbers) {
            pstmt.setInt(1, maxId);
            pstmt.setString(2, lotto.toString());
            pstmt.executeUpdate();
        }
    }

    public int getCount() throws SQLException {
        String query = "SELECT COUNT(id) FROM games";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("COUNT(id)");
        }
        return 1;
    }

    public int getMaxId() throws SQLException {
        String query = "SELECT MAX(id) FROM games";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("MAX(id)");
        }
        return 1;
    }

    public int getGamesTotalColumns() throws SQLException {
        String qeury = "SELECT COUNT(*) FROM games";
        PreparedStatement pstmt = getConnection().prepareStatement(qeury);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("COUNT(*)");
        }
        return 0;
    }

    public List<String> getLottosOfGame(int games_id) throws SQLException {
        String query = "SELECT * FROM lottos WHERE games_id=" + games_id;
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<String> lottos = new ArrayList<>();
        while(rs.next()) {
            lottos.add(rs.getString("numbers"));
        }
        return lottos;
    }

    public GameDTO getGameInformation(int id) throws SQLException {
        String query = "SELECT * FROM games WHERE id=" + id;
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        GameDTO gameDTO = new GameDTO();
        if (rs.next()) {
            gameDTO.setWinningNumbers(new Lotto(InputParser.parseLotto(rs.getString("winning_numbers"))));
            System.out.println(gameDTO.getWinningNumbers().toString());
            gameDTO.setBonusNumber(Number.of(rs.getInt("bonus")));
            System.out.println(gameDTO.getBonusNumber().toString());
            gameDTO.setResult(rs.getString("result"));
            System.out.println(gameDTO.getResult());
            gameDTO.setReturnAmount(rs.getString("return_amount"));
            System.out.println(gameDTO.getReturnAmount());
            gameDTO.setReturnRate(rs.getString("return_rate"));
            System.out.println(gameDTO.getReturnRate());
        }
        return gameDTO;
    }
}
