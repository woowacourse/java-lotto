package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.ErrorMessage;
import lotto.util.Parser;

public class Lotto {
    private List<Integer> lottoNumber;
    private RandomNumber randomNumber;

    public Lotto(RandomNumber randomNumber) {
        this.randomNumber = randomNumber;
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
        if (number <= 0 || number > 45) {
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
        if (unDuplicatedLotto.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }
    public void checkDuplicate(int bonusNumber) {
        if (lottoNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

    private List<Integer> generateLotto() {
        return randomNumber.generateLotto();
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
            return 1;
        }
        return 0;
    }

    public boolean checkBonusNumberMatch(int bonusNumber) {
        return lottoNumber.contains(bonusNumber);
    }

    private List<String> validateLength(String winningLottoInput) {
        List<String> winningNumbers = List.of(winningLottoInput
                .replaceAll(" ", "")
                .split(","));
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_LENGTH_ERROR.getMessage());
        }
        return winningNumbers;
    }

}
