package lotto.domain.number;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers extends LottoRound {
    private static final int WINNING_NUMBER_SIZE = 7;
    private final LottoNumber bonusBall;

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusBall) {
        super(winningNumbers);
        validateDuplicate(winningNumbers, bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateDuplicate(List<LottoNumber> winningNumbers, LottoNumber bonusBall) {
        List<LottoNumber> tempWinningNumbers = new ArrayList<>(winningNumbers);
        tempWinningNumbers.add(bonusBall);
        boolean isDuplicate = tempWinningNumbers.stream()
                .distinct()
                .count() != WINNING_NUMBER_SIZE;
        if (isDuplicate) {
            throw new IllegalArgumentException("중복이 존재합니다.");
        }
    }

    public int calculateCollectNumberSize(LottoNumbers lottoNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(lottoNumbers::isHave)
                .count();
    }

    public boolean isCorrectBonusNumber(LottoNumbers lottoNumb2ers) {
        return lottoNumb2ers.isHave(bonusBall);
    }
}
