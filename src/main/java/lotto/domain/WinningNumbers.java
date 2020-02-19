package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {
    private final List<LottoNumberGroup> winningNumbers;
    private final LottoNumberGroup bonusBall;

    public WinningNumbers(List<Integer> winningNumbers, int bonusBall) {
        this.winningNumbers = winningNumbers.stream()
                .map(LottoNumberGroup::parseLottoNumberGroup)
                .collect(Collectors.toList());
        this.bonusBall = LottoNumberGroup.parseLottoNumberGroup(bonusBall);
    }
}
