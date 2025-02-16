package lotto.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstants;

public class LottoNumbers {
    private final List<LottoNumber> lottoNumbers;
    private RandomNumber randomNumber;

    public LottoNumbers(RandomNumber randomNumber) {
        this.lottoNumbers = new ArrayList<>();
        this.randomNumber = randomNumber;
        generateLottoNumbers();
    }

    public LottoNumbers() {
        this.lottoNumbers = new ArrayList<>();
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

    public boolean checkDuplicate(LottoNumber lottoNumber) {
        return lottoNumbers.stream().noneMatch(number -> number.getNumber() == lottoNumber.getNumber());
    }

    public void addLotto(LottoNumber lottoNumber) {
        this.lottoNumbers.add(lottoNumber);
    }

    public void findDuplicateWinningNumber() {
        Set<Integer> winningNumberSet = new HashSet<>();

        for (LottoNumber lottoNumber : lottoNumbers) {
            winningNumberSet.add(lottoNumber.getNumber());
        }

        validateLength(winningNumberSet);
    }

    public int match(Lotto winningLottoNumber) {
        int count = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            count += winningLottoNumber.increaseIfMatch(lottoNumber);
        }
        return count;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validateLength(Set<Integer> winningNumberSet) {
        if (winningNumberSet.size() != LottoConstants.LENGTH.getNumber()) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

    public boolean isMatchExist(LottoNumber number) {
        return lottoNumbers.stream().anyMatch(lotto -> isMatch(lotto, number));
    }

    public boolean isBonusNumberMatchExists(LottoNumber bonusNumber) {
        return lottoNumbers.stream().anyMatch(lotto -> isMatch(lotto, bonusNumber));
    }

    private boolean isMatch(LottoNumber lottoNumber, LottoNumber target) {
        return lottoNumber.getNumber() == target.getNumber();
    }

}
