package lotto.dao;

import lotto.DataBase;
import lotto.domain.Money;

import java.sql.PreparedStatement;

public class MoneyDao {
    private final DataBase dataBase;

    public MoneyDao(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public int addMoney(Money money, int times) throws Exception {
        String query = "INSERT INTO money (times, money) VALUES (?, ?) ON DUPLICATE KEY UPDATE times = ?, money = money + ?";
        PreparedStatement pstmt = dataBase.getConnection().prepareStatement(query);

        pstmt.setInt(1, times + 1);
        pstmt.setInt(2, money.getMoney());
        pstmt.setInt(3, times + 1);
        pstmt.setInt(4, money.getMoney());

        return pstmt.executeUpdate();
    }
}
