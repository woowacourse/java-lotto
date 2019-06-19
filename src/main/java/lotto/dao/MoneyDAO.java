package lotto.dao;

import lotto.domain.Money;

import java.sql.SQLException;

public interface MoneyDAO {
    Money findByRound(int round) throws SQLException;

    int addMoney(Money money, int round) throws SQLException;

    int deleteMoney(int round) throws SQLException;
}
