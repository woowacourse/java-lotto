package lotto.domain;

import java.util.List;

public class WinningRankResult {

    public static int checkCorrectNumber(List<Integer> lottoNumbers, List<Integer> winningLottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningLottoNumbers::contains)
                .count();
    }

    public static boolean isBonusNumberContain(List<Integer> lottoNumbers, int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}
