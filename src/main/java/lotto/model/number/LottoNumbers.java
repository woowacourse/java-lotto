package lotto.model.number;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 6개의 로또 번호를 한 묶음으로 저장하는 일급 컬렉션 Class
 */
public class LottoNumbers {
    private static final String ERROR_SIZE = "[ERROR] 로또 번호는 6개여야 합니다";
    private static final String ERROR_DUPLICATE = "[ERROR] 로또 번호는 중복되면 안됩니다";
    private static final int NUMBERS_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    public static LottoNumbers ofRandomNumbers() {
        return new LottoNumbers(LottoWheel.draw(NUMBERS_SIZE));
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        checkSize(lottoNumbers);
        checkDuplicate(lottoNumbers);
    }

    private void checkSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException(ERROR_SIZE);
        }
    }

    private void checkDuplicate(List<LottoNumber> lottoNumbers) {
        if (getDistinctCount(lottoNumbers) != lottoNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    private int getDistinctCount(List<LottoNumber> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .distinct()
                .count();
    }

    public static LottoNumbers from(List<String> inputs) {
        List<LottoNumber> lottoNumbers = inputs.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
        return new LottoNumbers(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(number));
    }

    public int match(LottoNumbers comparingNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(comparingNumbers::contains)
                .count();
    }

    public List<Integer> getValues() {
        return this.lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
    }
}
