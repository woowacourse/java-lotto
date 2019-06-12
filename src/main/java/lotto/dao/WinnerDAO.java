package lotto.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lotto.domain.Rank;
import lotto.domain.RankResult;
import lotto.dto.WinnerDTO;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * @author heebg
 * @version 1.0 2019-06-12
 */
public class WinnerDAO {
    private static final String table = "winner";

    public static Connection getConnection() {
        String server = "localhost";
        String database = "db_lotto";
        String userName = "heebong";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver load error");
            e.printStackTrace();
            return null;
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
        } catch (SQLException e) {
            System.out.println("connectin error : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.out.println("connection error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void addWinner(WinnerDTO winnerDTO) throws SQLException {
        String query = "insert into " + table + "(profit, lotto_one, lotto_two, lotto_three, lotto_four, lotto_five, lotto_six, lotto_bonus, " +
                "first_rank_cnt, second_rank_cnt, third_rank_cnt, fourth_rank_cnt, fifth_rank_cnt, miss_rank_cnt) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        pstmt.setFloat(1, winnerDTO.getProfit());

        pstmt.setString(2, winnerDTO.winLotto(0).toString());
        pstmt.setString(3, winnerDTO.winLotto(1).toString());
        pstmt.setString(4, winnerDTO.winLotto(2).toString());
        pstmt.setString(5, winnerDTO.winLotto(3).toString());
        pstmt.setString(6, winnerDTO.winLotto(4).toString());
        pstmt.setString(7, winnerDTO.winLotto(5).toString());
        pstmt.setString(8, winnerDTO.winBonus().toString());

        pstmt.setInt(9, winnerDTO.matchRankCount(Rank.FIRST));
        pstmt.setInt(10, winnerDTO.matchRankCount(Rank.SECOND));
        pstmt.setInt(11, winnerDTO.matchRankCount(Rank.THIRD));
        pstmt.setInt(12, winnerDTO.matchRankCount(Rank.FOURTH));
        pstmt.setInt(13, winnerDTO.matchRankCount(Rank.FIFTH));
        pstmt.setInt(14, winnerDTO.matchRankCount(Rank.MISS));

        pstmt.execute();
    }

    public static JsonObject findWinnerByTurn(int turn) throws SQLException {
        String query = "select * from " + table + " where turn = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, turn);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) return null;

        return generateResult(resultSet);
    }

    private static JsonObject generateResult(ResultSet resultSet) throws SQLException {
        int resultSetColumn = 1;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("turn", resultSet.getInt(resultSetColumn++));
        jsonObject.addProperty("profit", resultSet.getInt(resultSetColumn++));

        JsonObject winLotto = new JsonObject();
        JsonArray Lotto = new JsonArray();

        for (int i = 0; i < 6; i++) {
            Lotto.add(resultSet.getInt(resultSetColumn++));
        }
        winLotto.add("lotto", Lotto);
        winLotto.addProperty("bonus", resultSet.getInt(resultSetColumn++));
        jsonObject.add("winLotto", winLotto);

        JsonArray rank = new JsonArray();
        for (int i = 1; i <= 5; i++) {
            JsonObject rankMatchCount = new JsonObject();
            rankMatchCount.addProperty(String.valueOf(i), resultSet.getInt(resultSetColumn++));
            rank.add(rankMatchCount);
        }
        JsonObject rankMatchCount = new JsonObject();
        rankMatchCount.addProperty("miss", resultSet.getInt(resultSetColumn++));
        rank.add(rankMatchCount);
        jsonObject.add("rank", rank);

        return jsonObject;
    }

    public static int findRecentTurn() throws SQLException {
        int resultSetFirstColumn = 1;
        String query = "select MAX(turn) from " + table;
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) throw new IllegalArgumentException("테이블에 데이터가 없습니다.");
        return resultSet.getInt(resultSetFirstColumn);
    }
}
