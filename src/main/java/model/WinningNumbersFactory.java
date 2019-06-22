package model;

import java.sql.SQLException;

public class WinningNumbersFactory {
    public static WinningNumbers of(int round) {
        if (round < 0) {
            throw new IllegalArgumentException();
        }
        if (round == 0) {
            return new WinningNumbersWeb();
        }
        try {
            return new WinningNumbersDB(round);
        } catch (SQLException e) {
            WinningNumbersWeb fetched = new WinningNumbersWeb(round);
            WinningNumbersDAO.register(fetched);
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