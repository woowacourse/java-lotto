package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {
    private static final int WINNING_NUMBER_SIZE = 7;
    private final LottoNumbers winningNumbers;
    private final LottoNumberGroup bonusBall;

    public WinningNumbers(List<Integer> winningNumbers, int bonusBall) {
        validateDuplicate(winningNumbers, bonusBall);
        this.winningNumbers = new LottoNumbers(winningNumbers.stream()
                .map(LottoNumberGroup::parseLottoNumberGroup)
                .collect(Collectors.toList()));
        this.bonusBall = LottoNumberGroup.parseLottoNumberGroup(bonusBall);
    }

    private void validateDuplicate(List<Integer> winningNumbers, int bonusBall) {
        List<Integer> tempWinningNumbers = new ArrayList<>(winningNumbers);
        tempWinningNumbers.add(bonusBall);
        boolean isDuplicate = tempWinningNumbers.stream()
                .distinct()
                .count() != WINNING_NUMBER_SIZE;
        if (isDuplicate) {
            throw new IllegalArgumentException("중복이 존재합니다.");
        }
    }
}
