package lotto.domain;

import lotto.domain.exception.DuplicateExistException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WinningRule {
    private static final int WINNING_NUMBER_SIZE = 7;
    private static final int NON_DUPLICATE_SIZE = 12;

    private final Lotto winningNumbers;
    private final LottoNumber bonusBall;

    public WinningRule(List<Integer> winningNumbers, int bonusBall) {
        validateDuplicate(winningNumbers, bonusBall);
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusBall = LottoNumberGroup.getInstance(bonusBall);
    }

    private void validateDuplicate(List<Integer> winningNumbers, int bonusBall) {
        List<Integer> mergedNumbers = new ArrayList<>(winningNumbers);
        mergedNumbers.add(bonusBall);
        if (isDuplicate(mergedNumbers)) {
            throw new DuplicateExistException();
        }
    }

    private boolean isDuplicate(List<Integer> tempWinningNumbers) {
        return tempWinningNumbers.stream()
                .distinct()
                .count()
                != WINNING_NUMBER_SIZE;
    }

    public Optional<Rank> findRank(Lotto lotto) {
        return Rank.findRank(countCorrectLottoNumber(lotto), isCorrectBonusNumber(lotto));
    }

    private int countCorrectLottoNumber(Lotto lotto) {
        List<LottoNumber> mergedLottoNumbers = new ArrayList<>(lotto.getLottoNumbers());
        mergedLottoNumbers.addAll(winningNumbers.getLottoNumbers());
        return NON_DUPLICATE_SIZE - (int) mergedLottoNumbers.stream().
                map(LottoNumber::getLottoNumber).
                distinct().
                count();
    }

    private boolean isCorrectBonusNumber(Lotto lotto) {
        return lotto.getLottoNumbers()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .anyMatch(number -> number.equals(bonusBall.getLottoNumber()));
    }
}
