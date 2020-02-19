package lotto.controller;

import lotto.model.AutoNumbers;
import lotto.model.WinNumber;

public class Controller {

    public static int isInWinNumber(AutoNumbers autonumbers) {
        int count = 0;
        for (int i : autonumbers.getAutoNumbers()) {
            count = checkInWinNumber(count, i);
        }
        return count;
    }

    private static int checkInWinNumber(int count, int i) {
        if (WinNumber.winNumbers.contains(i)) {
            count += 1;
        }
        return count;
    }
}
