package lotto.model;

import java.util.Map;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class WinningLotto {

    private static final String DUPLICATED_NUMBER_ERROR_MESSAGE = "[ERROR] 보너스 번호가 중복되었습니다.";

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(final LottoNumbers winningNumbers, final LottoNumber bonusNumber) throws RuntimeException {
        this.winningNumbers = winningNumbers;
        checkDuplicateNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public void checkDuplicateNumber(LottoNumber bonusNumber) throws RuntimeException {
        if (winningNumbers.containNumber(bonusNumber)) {
            throw new RuntimeException(DUPLICATED_NUMBER_ERROR_MESSAGE);
        }
    }

    public Map<Rank, Integer> checkRank(Lottos lottos) {
        return lottos.calculateRanks(winningNumbers, bonusNumber);
    }
}
