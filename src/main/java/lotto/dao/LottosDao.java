package lotto.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lotto.DataBase;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottosDao {
    private final DataBase dataBase;

    public LottosDao(final DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public int[] addLottos(Lottos lottos, int times) throws SQLException {
        String query = "INSERT INTO lotto (numbers, times) VALUES (?, ?)";
        PreparedStatement pstmt = dataBase.getConnection().prepareStatement(query);
        Gson gson = new GsonBuilder().create();

        for (Lotto lotto : lottos.getLottos()) {
            pstmt.setString(1, gson.toJson(lotto));
            pstmt.setInt(2, times);
            pstmt.addBatch();
        }

        return pstmt.executeBatch();
    }

    public int deleteLottos(int times) throws SQLException {
        String query = "DELETE FROM lotto WHERE times = ?";
        PreparedStatement pstmt = dataBase.getConnection().prepareStatement(query);
        pstmt.setInt(1, times);

        return pstmt.executeUpdate();
    }

    public Lottos findByTimes(int times) throws SQLException {
        String query = "SELECT * FROM lotto WHERE times = ?";
        PreparedStatement pstmt = dataBase.getConnection().prepareStatement(query);
        pstmt.setInt(1, times);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();

        Gson gson = new Gson();

        while (rs.next()) {
            Lotto lotto = gson.fromJson(rs.getString("numbers"), Lotto.class);
            lottos.add(lotto);
        }
        return new Lottos(lottos);
    }

}
