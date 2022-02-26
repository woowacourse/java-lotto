package lotto.model.lotto;

import java.util.stream.Collectors;
import lotto.model.number.BonusNumber;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class WinningLotto {

    private static final String DUPLICATED_NUMBER_ERROR_MESSAGE = "[ERROR] 중복된 번호가 존재합니다.";

    private final LottoNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(LottoNumbers winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        checkDuplicateNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public void checkDuplicateNumber(BonusNumber number) {
        if ((winningNumbers.getLottoNumbers().stream()
                .mapToInt(LottoNumber::getLottoNumber)
                .boxed()
                .collect(Collectors.toList()))
                .contains(number.getLottoNumber())) {
            throw new RuntimeException(DUPLICATED_NUMBER_ERROR_MESSAGE);
        }
    }

    public void checkRank(Lottos lottos) {
        lottos.calculateRanks(winningNumbers, bonusNumber);
    }
}
