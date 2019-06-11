package model;

import java.sql.SQLException;

public class WinningNumbersFactory {

    public static WinningNumbers of(int round) {
        if (round < 0) {
            throw new IllegalArgumentException();
        }
        round = Math.min(round, Lotto.recentRound());
        try {
            return new WinningNumbersDB(round);
        } catch (SQLException e) {
            WinningNumbers fetched = new WinningNumbersWeb(round);
            WinningNumbersDB.register(fetched, round);
            return fetched;
        }
    }

    public static WinningNumbers of() {
        return of(0);
    }

    public static WinningNumbers of(String input) {
        return of(Integer.parseInt(input.trim()));
    }
}