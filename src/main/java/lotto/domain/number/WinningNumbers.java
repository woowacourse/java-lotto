package lotto.domain.number;

import lotto.domain.exception.DuplicateLottoNumberException;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {
    private static final int WINNING_NUMBER_SIZE = 7;
    private static final String DUPLICATE_LOTTO_NUMBER_EXCEPTION_MESSAGE = "중복이 존재합니다.";

    private final LottoRound winningNumbers;
    private final LottoNumber bonusBall;

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusBall) {
        validateDuplicate(winningNumbers, bonusBall);
        this.winningNumbers = new LottoRound(winningNumbers);
        this.bonusBall = bonusBall;
    }

    private void validateDuplicate(List<LottoNumber> winningNumbers, LottoNumber bonusBall) {
        List<LottoNumber> tempWinningNumbers = new ArrayList<>(winningNumbers);
        tempWinningNumbers.add(bonusBall);
        boolean isDuplicate = tempWinningNumbers.stream()
                .distinct()
                .count() != WINNING_NUMBER_SIZE;
        if (isDuplicate) {
            throw new DuplicateLottoNumberException(DUPLICATE_LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    int calculateCollectNumberCount(LottoRound lottoRound) {
        return winningNumbers.calculateCorrectNumberCount(lottoRound);
    }

    boolean hasBonusNumber(LottoRound lottoNumbers) {
        return lottoNumbers.has(bonusBall);
    }
}
