package domain;

import java.util.List;

public class Matcher {

    private final int winningNumberCount;
    private final boolean hasBonusNumber;

    public static Matcher count(WinningLotto winningLotto, Lotto lotto) {
        List<Integer> winningNumbers = winningLotto.getWinningNumbers();
        List<Integer> lottoNumbers = lotto.getBallNumbers();

        int winningNumberCount = lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .mapToInt(e -> 1)
                .sum();
        boolean hasBonusNumber = lottoNumbers.contains(winningLotto.getBonusNumber());

        return new Matcher(winningNumberCount, hasBonusNumber);
    }

    private Matcher(int winningNumberCount, boolean hasBonusNumber) {
        this.winningNumberCount = winningNumberCount;
        this.hasBonusNumber = hasBonusNumber;
    }

    public int getWinningNumberCount() {
        return winningNumberCount;
    }

    public boolean isHasBonusNumber() {
        return hasBonusNumber;
    }
}
