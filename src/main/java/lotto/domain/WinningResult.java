package lotto.domain;

import java.util.List;

public class WinningResult {

    public static int checkCorrectNumber(List<Integer> lottoNumbers, List<Integer> winningLottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningLottoNumbers::contains)
                .count();
    }
}
