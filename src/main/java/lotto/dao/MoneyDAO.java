package lotto.dao;

import lotto.domain.Money;

public interface MoneyDAO {
    Money findByRound(int round);

    int addMoney(Money money, int round);

    int deleteMoney(int round);
}
