package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.LottoConstants;
import lotto.util.Parser;

public class Lotto {

    private static final String LOTTO_NUMBER_RANGE_ERROR = "1과 45 사이의 수를 입력하셔야 합니다.";
    private static final String LOTTO_NUMBER_FORMAT_ERROR = "당첨 번호는 숫자를 입력하셔야 합니다.";
    private static final String LOTTO_NUMBER_DUPLICATED_ERROR = "로또 숫자는 중복될 수 없습니다.";
    private static final String LOTTO_NUMBER_LENGTH_ERROR = "6자리를 입력하셔야 합니다.";

    private List<Integer> lottoNumber;

    public Lotto(LottoGenerator lottoGenerator) {
        this.lottoNumber = lottoGenerator.generateLotto();
    }

    public Lotto(String winningLottoInput) {
        List<Integer> parsedLotto = parseLotto(winningLottoInput);
        validateLotto(parsedLotto);
        this.lottoNumber = parsedLotto;
    }

    public int getMatchCount(Lotto winningLottoNumber) {
        List<Integer> copiedLottoNumber = new ArrayList<>(lottoNumber);
        copiedLottoNumber.retainAll(winningLottoNumber.lottoNumber);
        return copiedLottoNumber.size();
    }

    public boolean containsNumber(int bonusNumber) {
        return lottoNumber.contains(bonusNumber);
    }

    private List<Integer> parseLotto(String winningLottoInput) {
        List<String> splittedLotto = Parser.splitByComma(winningLottoInput);

        List<Integer> parsedLotto = new ArrayList<>();
        for (String lottoNumber : splittedLotto) {
            parsedLotto.add(Parser.parseToNumber(lottoNumber, LOTTO_NUMBER_FORMAT_ERROR));
        }
        return parsedLotto;
    }

    private void validateLotto(List<Integer> parsedLotto) {
        validateLength(parsedLotto);
        validateRange(parsedLotto);
        validateDuplicate(parsedLotto);
    }

    private void validateLength(List<Integer> parsedLotto) {
        if (parsedLotto.size() != LottoConstants.LENGTH.getNumber()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_LENGTH_ERROR);
        }
    }

    private void validateRange(List<Integer> parsedLotto) {
        for (Integer number : parsedLotto) {
            checkRange(number);
        }
    }

    private void checkRange(int number) {
        if (number <= LottoConstants.ZERO.getNumber() || number > LottoConstants.LOTTO_MAXIMUM_NUMBER.getNumber()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR);
        }
    }

    private void validateDuplicate(List<Integer> parsedLotto) {
        Set<Integer> unDuplicatedLotto = new HashSet<>(parsedLotto);
        if (unDuplicatedLotto.size() != LottoConstants.LENGTH.getNumber()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED_ERROR);
        }
    }

    public List<Integer> getLottoNumber() {
        return new ArrayList<>(lottoNumber);
    }

    @Override
    public String toString() {
        return lottoNumber.toString() + System.lineSeparator();
    }
}
