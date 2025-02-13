package repository;

import domain.WinningNumber;
import java.util.List;

public class WinningNumberRepository {
    private WinningNumber winningNumber;

    public void saveWinningNumber(WinningNumber winningNumber) {
        this.winningNumber = winningNumber;
    }

    public WinningNumber getWinningNumber() {
        return winningNumber;
    }
}
