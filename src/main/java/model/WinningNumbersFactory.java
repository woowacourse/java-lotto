package model;

import java.sql.SQLException;

public class WinningNumbersFactory {

    public static WinningNumbers of(int round) {
        if (round < 0) {
            throw new IllegalArgumentException();
        }
        round = Math.min(round, Lotto.recentRound());
        try {
            return new WinningNumbersFromDB(round);
        } catch (SQLException e) {
            WinningNumbers fetched = new WinningNumbersFromWeb(round);
            WinningNumbersFromDB.register(fetched, round);
            return fetched;
        }
    }

    public static WinningNumbers of() {
        return of(0);
    }

    public static WinningNumbers of(String input) {
        return new WinningNumbersManual(input);
    }

    private WinningNumbersFactory() {};
}