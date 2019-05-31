package lotto.controller;

import lotto.domain.WinnerNumber;

public class WinnerNumberController {
    public WinnerNumber makeWinnerNumber(String input) {
        return WinnerNumber.create(input);
    }
}
