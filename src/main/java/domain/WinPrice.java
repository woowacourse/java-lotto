package domain;

import java.util.Arrays;

public enum WinPrice {
    FIRST(6, 2000000000),// 2000000000 (6개)
    SECOND(7, 30000000),// 보너스 2등 30000000 (5개 + 보너스)
    THIRD(5, 1500000),// 3등 1500000(5개 일치)
    FOURTH(4, 50000),// 4등 50000 (4개 일치)
    FIFTH(3, 5000);// 5등 5000 (3개 일치)

    private final int winCount;
    private final int winPrice;

    WinPrice(final int winCount, final int winPrice) {
        this.winCount = winCount;
        this.winPrice = winPrice;
    }

    public static WinPrice findByCount(final int winCount) {
        return Arrays.stream(WinPrice.values())
                .filter(winPrice -> winPrice.winCount == winCount)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("일치하는 값이 없습니다."));
    }

    public int getCount() {
        return winCount;
    }

    public int getPrice() {
        return winPrice;
    }
}
