package lotto.model;

import java.util.stream.Collectors;

public class WinningLotto {

    private static final String LOTTO_ERROR_MESSAGE = "[ERROR] 잘못된 로또 번호입니다.";
    private static final String BONUS_NUMBER_ERROR_MESSAGE = "[ERROR] 잘못된 보너스 번호입니다.";

    private final LottoNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(LottoNumbers winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        hasDuplicateNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public void hasDuplicateNumber(BonusNumber number) {
        if ((winningNumbers.getLottoNumbers().stream()
                .mapToInt(LottoNumber::getLottoNumber)
                .boxed()
                .collect(Collectors.toList()))
                .contains(number.getLottoNumber())) {
            throw new RuntimeException(BONUS_NUMBER_ERROR_MESSAGE);
        }
    }

    public void checkRank(Lottos lottos) {
        lottos.calculateRanks(winningNumbers, bonusNumber);
    }
}
