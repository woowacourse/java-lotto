package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.LottoConstants;
import lotto.util.Parser;

public class Lotto {

    private static final String DELIMITER = ",";
    private static final String RANGE_ERROR = "1과 45 사이의 수를 입력하셔야 합니다.";
    private static final String NUMBER_FORMAT_ERROR = "당첨 번호는 숫자를 입력하셔야 합니다.";
    private static final String NUMBER_DUPLICATED_ERROR = "로또 숫자는 중복될 수 없습니다.";

    private static final String NUMBER_LENGTH_ERROR = "6자리를 입력하셔야 합니다.";

    private List<Integer> lottoNumber;
    private RandomLottoGenerator randomLottoGenerator;

    public Lotto(RandomLottoGenerator randomLottoGenerator) {
        this.randomLottoGenerator = randomLottoGenerator;
        this.lottoNumber = generateLotto();
    }

    public Lotto(String winningLottoInput) {
        validate(winningLottoInput);
    }

    public int match(Lotto winningLottoNumber) {
        List<Integer> copiedLottoNumber = new ArrayList<>(lottoNumber);
        copiedLottoNumber.retainAll(winningLottoNumber.lottoNumber);
        return copiedLottoNumber.size();
    }

    public boolean contains(int bonusNumber) {
        return lottoNumber.contains(bonusNumber);
    }

    private void validate(String winningLottoInput) {
        List<String> splittedLotto = validateLength(winningLottoInput);
        List<Integer> parsedLotto = validateIsNumber(splittedLotto);
        validateRange(parsedLotto);
        validateDuplicate(parsedLotto);
        this.lottoNumber = parsedLotto;
    }

    private List<String> validateLength(String winningLottoInput) {
        List<String> winningNumbers = List.of(winningLottoInput
                .replaceAll(" ", "")
                .split(DELIMITER));
        if (winningNumbers.size() != LottoConstants.LENGTH.getNumber()) {
            throw new IllegalArgumentException(NUMBER_LENGTH_ERROR);
        }
        return winningNumbers;
    }

    private List<Integer> validateIsNumber(List<String> splitedLotto) {
        List<Integer> parsedLotto = new ArrayList<>();
        for (String lottoNumber : splitedLotto) {
            parsedLotto.add(Parser.validateNumber(lottoNumber, NUMBER_FORMAT_ERROR));
        }
        return parsedLotto;
    }

    private void validateRange(List<Integer> parsedLotto) {
        for (Integer number : parsedLotto) {
            checkRange(number);
        }
    }

    private void checkRange(int number) {
        if (number <= LottoConstants.ZERO.getNumber() || number > LottoConstants.LOTTO_MAXIMUM_NUMBER.getNumber()) {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    private List<Integer> generateLotto() {
        return randomLottoGenerator.generateLotto();
    }

    private void validateDuplicate(List<Integer> parsedLotto) {
        Set<Integer> unDuplicatedLotto = new HashSet<>(parsedLotto);
        if (unDuplicatedLotto.size() != LottoConstants.LENGTH.getNumber()) {
            throw new IllegalArgumentException(NUMBER_DUPLICATED_ERROR);
        }
    }

    @Override
    public String toString() {
        return lottoNumber.toString() + System.lineSeparator();
    }
}
