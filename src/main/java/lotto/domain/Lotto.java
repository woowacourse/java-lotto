package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            throw new IllegalArgumentException("1과 45 사이의 수를 입력하세요.");
        }
    }
    private List<Integer> validateIsNumber(List<String> splitedLotto) {
        List<Integer> parsedLotto = new ArrayList<>();
        for (String lottoNumber : splitedLotto) {
            parsedLotto.add(Parser.validateNumber(lottoNumber, "당첨 번호는 숫자를 입력해야 합니다."));
        }
        return parsedLotto;
    }

    private void validateDuplicate(List<Integer> parsedLotto) {
        Set<Integer> unDuplicatedLotto = new HashSet<>(parsedLotto);
        if (unDuplicatedLotto.size() != 6) {
            throw new IllegalArgumentException("로또 6개의 숫자를 입력하셔야 합니다.");
        }
    }
    public void checkDuplicate(int bonusNumber) {
        if (lottoNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 숫자는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private List<Integer> generateLotto() {
        List<Integer> lotto = new ArrayList<>();
        while(lotto.size() < 6) {
            int number = randomNumber.generateRandomNumber();
            checkDuplicate(lotto, number);
        }
        Collections.sort(lotto);
        return lotto;
    }

    private void checkDuplicate(List<Integer> lotto, int number) {
        if (!lotto.contains(number)) {
            lotto.add(number);
        }
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
            throw new IllegalArgumentException("6자리를 입력하세요.");
        }
        return winningNumbers;
    }

}
