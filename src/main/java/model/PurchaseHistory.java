package model;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PurchaseHistory {
    private int round = 0;
    private Lottos lottos = null;

    public static void writeLog(Lottos lottos, int round, LottoResult result) {
        try {
            final DB db = DB.getInstance();
            PreparedStatement pstmt = db.connect().prepareStatement(
                    "INSERT INTO purchase_history (round, lottos) VALUES (?, ?)"
            );
            pstmt.setInt(1, round);
            pstmt.setString(2, lottos.encodeToDB());
            pstmt.executeUpdate();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Timestamp> retrieveDatesFromLog() {
        try {
            final DB db = DB.getInstance();
            final PreparedStatement pstmt = db.connect().prepareStatement("SELECT date FROM purchase_history");
            List<Timestamp> fetched = new ArrayList<>();
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                fetched.add(result.getTimestamp(1));
            }
            db.close();
            return fetched;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public PurchaseHistory(String date) throws SQLException {
        final DB db = DB.getInstance();
        final PreparedStatement pstmt = db.connect().prepareStatement("SELECT round, lottos FROM purchase_history WHERE date=?");
        pstmt.setTimestamp(1, Timestamp.from(Instant.parse(date)));
        ResultSet result = pstmt.executeQuery();
        if (result.next()) {
            this.round = result.getInt(1);
            this.lottos = new Lottos(result.getString(2));
        }
        db.close();
    }

    public int round() {
        return this.round;
    }

    public Lottos lottos() {
        return this.lottos;
    }
}