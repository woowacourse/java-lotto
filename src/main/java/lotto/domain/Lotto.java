package lotto.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstants;

public class Lotto {
    private static final int INCREASE = 1;
    private static final int MAINTENANCE = 0;
    private static final String DELIMITER = ",";
    private final List<LottoNumber> lottoNumbers;
    private RandomNumber randomNumber;

    public Lotto(RandomNumber randomNumber) {
        this.lottoNumbers = new ArrayList<>();
        this.randomNumber = randomNumber;
        generateLottoNumbers();
    }

    private void generateLottoNumbers() {
        while (lottoNumbers.size() < LottoConstants.LENGTH.getNumber()) {
            LottoNumber lottoNumber = new LottoNumber(randomNumber.generateRandomNumber(
                    LottoConstants.LOTTO_MINIMUM_NUMBER.getNumber(),
                    LottoConstants.LOTTO_MAXIMUM_NUMBER.getNumber()));
            judgeAddLotto(lottoNumber);
        }
        lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
    }

    private void judgeAddLotto(LottoNumber lottoNumber) {
        if (checkDuplicate(lottoNumber)) {
            lottoNumbers.add(lottoNumber);
        }
    }

    private boolean checkDuplicate(LottoNumber lottoNumber) {
        return lottoNumbers.stream().noneMatch(number -> number.getNumber() == lottoNumber.getNumber());
    }

    public Lotto(String winningLottoInput) {
        this.lottoNumbers = validate(winningLottoInput);
    }

    private List<LottoNumber> validate(String winningLottoInput) {
        List<String> splittedLotto = validateLength(winningLottoInput);
        List<LottoNumber> winningLotto = validateIsNumber(splittedLotto);
        findDuplicateWinningNumber(winningLotto);
        return winningLotto;
    }

    private void findDuplicateWinningNumber(List<LottoNumber> winningLotto) {
        Set<Integer> winningNumberSet = new HashSet<>();

        for (LottoNumber lottoNumber : winningLotto) {
            winningNumberSet.add(lottoNumber.getNumber());
        }

        validateWinningLength(winningNumberSet);
    }

    private void validateWinningLength(Set<Integer> winningNumberSet) {
        if (winningNumberSet.size() != LottoConstants.LENGTH.getNumber()) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

    private List<LottoNumber> validateIsNumber(List<String> splittedLotto) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String lottoNumber : splittedLotto) {
            lottoNumbers.add(new LottoNumber(lottoNumber));
        }
        return lottoNumbers;
    }

    private int increaseIfMatch(LottoNumber number) {
        if (isMatchExist(number)) {
            return INCREASE;
        }
        return MAINTENANCE;
    }

    private boolean isMatchExist(LottoNumber number) {
        return lottoNumbers.stream().anyMatch(lotto -> isMatch(lotto, number));
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

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public boolean checkBonusNumberMatch(LottoNumber bonusNumber) {
        return lottoNumbers.stream().anyMatch(lotto -> isMatch(lotto, bonusNumber));
    }

    private boolean isMatch(LottoNumber lottoNumber, LottoNumber target) {
        return lottoNumber.getNumber() == target.getNumber();
    }

    public int match(Lotto winningLottoNumber) {
        int count = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            count += winningLottoNumber.increaseIfMatch(lottoNumber);
        }
        return count;
    }

    public void checkBonusDuplicate(LottoNumber bonusNumber) {
        if (!checkBonusContain(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

    private boolean checkBonusContain(LottoNumber lottoNumber) {
        return lottoNumbers.stream().noneMatch(number -> number.getNumber() == lottoNumber.getNumber());
    }

}
