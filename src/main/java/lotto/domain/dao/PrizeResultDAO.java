package lotto.domain.dao;

import lotto.domain.model.Money;
import lotto.domain.model.Rank;
import lotto.domain.utils.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrizeResultDAO {
    public void addPrizeResult(final List<Rank> ranks, int round, Money money) throws SQLException {
        Connection con = ConnectionGenerator.getConnection();
        String query = "INSERT INTO result " +
                "(first_prize, second_prize, third_prize, forth_prize, fifth_prize, income_rate, round) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        Map<Integer, Integer> ranking = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            ranking.put(i, 0);
        }

        int prizeSum = 0;
        for (Rank rank : ranks) {
            prizeSum += rank.getPrize();
            ranking.put(rank.getRanking(), ranking.get(rank.getRanking() + 1));
        }

        for (int i = 1; i <= 5; i++) {
            pstmt.setInt(i, ranking.get(i));
        }

        double profitRate = prizeSum / (double) money.getMoney();
        double income_rate = Math.round(profitRate * 1000) / 1000.0;
        pstmt.setDouble(6, income_rate);
        pstmt.setInt(7, round);
        pstmt.executeUpdate();
        ConnectionGenerator.closeConnection(con);

    }
}
