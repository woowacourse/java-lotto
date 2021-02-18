package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import lotto.exception.LottoCustomException;

public enum WinningResult {
    EIGHTH(0, 0, ""),
    SEVENTH(1, 0, ""),
    SIXTH(2, 0, ""),
    FIFTH(3, 5000, "3개 일치"),
    FOURTH(4, 50000, "4개 일치"),
    THIRD(5, 1500000, "5개 일치"),
    SECOND(6, 30000000, "5개 일치, 보너스 볼 일치"),
    FIRST(7, 2000000000, "6개 일치");

    public static final String NO_REWARD_ERROR_MESSAGE = "해당 등수에 해당하는 보상이 존재하지 않습니다.";
    public static final String NO_RESULT_ERROR_MESSAGE = "해당하는 결과값을 찾을 수 없습니다.";

    private final int hitCount;
    private final int reward;
    private final String message;

    WinningResult(int hitCount, int reward, String message) {
        this.hitCount = hitCount;
        this.reward = reward;
        this.message = message;
    }

    public static int calculateTotalReward(List<Integer> hitCounts) {
        int totalReward = 0;
        for (int i = FIFTH.hitCount; i <= FIRST.hitCount; i++) {
            totalReward += hitCounts.get(i) * findReward(i);
        }
        return totalReward;
    }

    private static int findReward(int count) {
        return Arrays.stream(values())
            .filter(value -> count == value.hitCount)
            .findFirst()
            .orElseThrow(()->new LottoCustomException(NO_REWARD_ERROR_MESSAGE))
            .reward;
    }

    public static String toString(List<Integer> hitCounts) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.rangeClosed(FIFTH.hitCount, FIRST.hitCount).forEach(index -> {
            WinningResult winningResult = find(index);
            stringBuilder.append(winningResult.message + " (" + winningResult.reward + ")원 - " +
                hitCounts.get(index) + "개" + System.lineSeparator());

        });
        return stringBuilder.toString();
    }

    private static WinningResult find(int count) {
        return Arrays.stream(values())
            .filter(value -> count == value.hitCount)
            .findFirst()
            .orElseThrow(()->new LottoCustomException(NO_RESULT_ERROR_MESSAGE));
    }
}
