package model;

import java.util.List;

public interface WinningNumbers {
    List<LottoNumber> mainNumbers();
    LottoNumber bonusNumber();

    static int recentRound() {
        return WinningNumbersRecentRound.getRecentRound();
    }
}