package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstants;
import lotto.util.Parser;

public class Lotto {
    private static final int INCREASE = 1;
    private static final int MAINTENANCE = 0;
    private static final String DELIMITER = ",";
    private List<Integer> lottoNumber;
    private LottoGenerator lottoGenerator;

    public Lotto(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
        this.lottoNumber = generateLotto();
    }

    public Lotto(String winningLottoInput) {
        validate(winningLottoInput);
    }

    private void validate(String winningLottoInput) {
        List<String> splittedLotto = validateLength(winningLottoInput);
        List<Integer> parsedLotto = validateIsNumber(splittedLotto);
        validateRange(parsedLotto);
        validateDuplicate(parsedLotto);
        this.lottoNumber = parsedLotto;
    }
    private void validateRange(List<Integer> parsedLotto) {
        for (Integer number : parsedLotto) {
            checkRange(number);
        }
    }

    private void checkRange(int number) {
        if (number <= LottoConstants.ZERO.getNumber() || number > LottoConstants.LOTTO_MAXIMUM_NUMBER.getNumber()) {
            throw new IllegalArgumentException(ErrorMessage.RANGE_ERROR.getMessage());
        }
    }
    private List<Integer> validateIsNumber(List<String> splitedLotto) {
        List<Integer> parsedLotto = new ArrayList<>();
        for (String lottoNumber : splitedLotto) {
            parsedLotto.add(Parser.validateNumber(lottoNumber, ErrorMessage.NUMBER_FORMAT_ERROR.getMessage()));
        }
        return parsedLotto;
    }

    private void validateDuplicate(List<Integer> parsedLotto) {
        Set<Integer> unDuplicatedLotto = new HashSet<>(parsedLotto);
        if (unDuplicatedLotto.size() != LottoConstants.LENGTH.getNumber()) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }
    public void checkDuplicate(int bonusNumber) {
        if (lottoNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

    private List<Integer> generateLotto() {
        return lottoGenerator.generateLotto();
    }

    @Override
    public String toString() {
        return lottoNumber.toString() + System.lineSeparator();
    }

    public int match(Lotto winningLottoNumber) {
        int count = 0;
        for (int number : lottoNumber) {
            count += winningLottoNumber.contain(number);
        }
        return count;
    }
    public int contain(int number) {
        if (lottoNumber.contains(number)) {
            return INCREASE;
        }
        return MAINTENANCE;
    }

    public boolean checkBonusNumberMatch(int bonusNumber) {
        return lottoNumber.contains(bonusNumber);
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

}
