package lotto.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstants;
import lotto.util.NumberGenerator;

public class Lotto {
    private static final int INCREASE = 1;
    private static final int MAINTENANCE = 0;
    private static final String DELIMITER = ",";
    private final List<LottoNumber> lottoNumbers;

    public Lotto(NumberGenerator numberGenerator) {
        this.lottoNumbers = new ArrayList<>();
        generateLottoNumbers(numberGenerator);
    }

    public Lotto(String winningLottoInput) {
        this.lottoNumbers = validate(winningLottoInput);
    }

    private void generateLottoNumbers(NumberGenerator numberGenerator) {
        while (lottoNumbers.size() < LottoConstants.LENGTH.getNumber()) {
            LottoNumber lottoNumber = new LottoNumber(numberGenerator.generate(
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

    private List<LottoNumber> validate(String winningLottoInput) {
        List<String> splittedLotto = validateLength(winningLottoInput);
        List<LottoNumber> winningLotto = validateIsNumber(splittedLotto);
        findDuplicateWinningNumber(winningLotto);
        return winningLotto;
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

    private List<LottoNumber> validateIsNumber(List<String> splittedLotto) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String lottoNumber : splittedLotto) {
            lottoNumbers.add(new LottoNumber(lottoNumber));
        }
        return lottoNumbers;
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

    public int match(Lotto winningLottoNumber) {
        int count = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            count += winningLottoNumber.increaseIfMatch(lottoNumber);
        }
        return count;
    }

    private int increaseIfMatch(LottoNumber number) {
        if (isMatchExist(number)) {
            return INCREASE;
        }
        return MAINTENANCE;
    }

    public boolean isMatchExist(LottoNumber number) {
        return lottoNumbers.stream().anyMatch(lotto -> isMatch(lotto, number));
    }

    private boolean isMatch(LottoNumber lottoNumber, LottoNumber target) {
        return lottoNumber.getNumber() == target.getNumber();
    }

    public void checkBonusDuplicate(LottoNumber bonusNumber) {
        if (!checkBonusContain(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

    private boolean checkBonusContain(LottoNumber lottoNumber) {
        return lottoNumbers.stream().noneMatch(number -> number.getNumber() == lottoNumber.getNumber());
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

}
