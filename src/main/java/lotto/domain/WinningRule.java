package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningRule {
    private static final int WINNING_NUMBER_SIZE = 7;
    private static final int NON_DUPLICATE_SIZE = 12;
    private static final String DUPLICATE_EXIST_EXCEPTION_MESSAGE = "Duplicate exist.";

    private final Lotto winningNumbers;
    private final LottoNumber bonusBall;

    public WinningRule(List<Integer> winningNumbers, int bonusBall) {
        validateDuplicate(winningNumbers, bonusBall);
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusBall = LottoNumberGroup.getInstance(bonusBall);
    }

    private void validateDuplicate(List<Integer> winningNumbers, int bonusBall) {
        List<Integer> tempWinningNumbers = new ArrayList<>(winningNumbers);
        tempWinningNumbers.add(bonusBall);
        boolean isDuplicate = tempWinningNumbers.stream()
                .distinct()
                .count() != WINNING_NUMBER_SIZE;
        if (isDuplicate) {
            throw new IllegalArgumentException(DUPLICATE_EXIST_EXCEPTION_MESSAGE);
        }
    }

    public void calculateWinningResult(PurchaseLottos purchaseLottos) {
        for (Lotto lotto : purchaseLottos.getPurchaseLottos()) {
            calculateWinningResult(lotto);
        }
    }

    private void calculateWinningResult(Lotto lotto) {
        List<LottoNumber> mergedLottoNumbers = new ArrayList<>(lotto.getLottoNumbers());
        mergedLottoNumbers.addAll(winningNumbers.getLottoNumbers());
        ResultCalculator.calculateRank(countCorrectLottoNumber(mergedLottoNumbers), isCorrectBonusNumber(lotto));
    }

    private int countCorrectLottoNumber(List<LottoNumber> mergedLottoNumbers) {
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
