package lotto.domain;

import java.util.List;
import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstants;

public class Lotto {
    private static final int INCREASE = 1;
    private static final int MAINTENANCE = 0;
    private static final String DELIMITER = ",";
    private final LottoNumbers lottoNumbers;

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(String winningLottoInput) {
        this.lottoNumbers = validate(winningLottoInput);
    }

    private LottoNumbers validate(String winningLottoInput) {
        List<String> splittedLotto = validateLength(winningLottoInput);
        LottoNumbers winningLotto = validateIsNumber(splittedLotto);
        winningLotto.findDuplicateWinningNumber();
        return winningLotto;
    }

    private LottoNumbers validateIsNumber(List<String> splittedLotto) {
        LottoNumbers lottoNumbers = new LottoNumbers();
        for (String lottoNumber : splittedLotto) {
            lottoNumbers.addLotto(new LottoNumber(lottoNumber));
        }
        return lottoNumbers;
    }

    public int increaseIfMatch(LottoNumber number) {
        if (isMatchExist(number)) {
            return INCREASE;
        }
        return MAINTENANCE;
    }

    private boolean isMatchExist(LottoNumber number) {
        return lottoNumbers.isMatchExist(number);
    }

    private List<String> validateLength(String winningLottoInput) {
        List<String> winningNumbers = List.of(winningLottoInput
                .replaceAll(" ", "")
                .split(DELIMITER));
        if (winningNumbers.size() != LottoConstants.LENGTH.getNumber()) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_LENGTH_ERROR.getMessage());
        }
        return winningNumbers;
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public boolean checkBonusNumberMatch(LottoNumber bonusNumber) {
        return lottoNumbers.isBonusNumberMatchExists(bonusNumber);
    }

    public int match(Lotto winningLottoNumber) {
        return lottoNumbers.match(winningLottoNumber);
    }

    public void checkBonusDuplicate(LottoNumber bonusNumber) {
        if (!lottoNumbers.checkDuplicate(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

}
