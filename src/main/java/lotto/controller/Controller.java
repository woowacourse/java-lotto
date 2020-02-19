package lotto.controller;

import lotto.model.AutoNumbers;
import lotto.model.BonusBall;
import lotto.model.WinNumber;

public class Controller {

    public static int isInWinNumber(AutoNumbers autonumbers) {
        int count = 0;
        for (int autoNumber : autonumbers.getAutoNumbers()) {
            count = checkInWinNumber(count, autoNumber);
        }
        return count;
    }

    private static int checkInWinNumber(int count, int autoNumber) {
        if (WinNumber.winNumbers.contains(autoNumber)) {
            count += 1;
        }
        return count;
    }

    public static boolean hasBonusBall(AutoNumbers autonumbers) {
        return autonumbers.getAutoNumbers().contains(BonusBall.bonusNo);
    }
}
