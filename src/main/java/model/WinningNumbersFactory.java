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
        } catch (SQLException | NoWinningNumbersInDBException e) {
            WinningNumbersWeb fetched = new WinningNumbersWeb(round);
            WinningNumbersDAO.register(fetched);
            return fetched;
        }
    }

    public static WinningNumbers ofRecent() {
        return of(0);
    }

    public static WinningNumbers ofManual(String input) {
        return new WinningNumbersManual(input);
    }

    private WinningNumbersFactory() {};
}