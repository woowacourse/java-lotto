package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;
    public static final String ERROR_WRONG_LOTTO_SIZE = "[ERROR] 로또는 " + LOTTO_SIZE +"개의 숫자로 이루어져야 합니다.";
    public static final String ERROR_DUPLICATE_LOTTO_NUMBER = "[ERROR] 로또 번호는 서로 중복되지 않아야 합니다.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto() {
        this.lottoNumbers = createLotto();
        Collections.sort(lottoNumbers);
    }

    public Lotto(List<LottoNumber> numbers) {
        checkLottoSize(numbers);
        checkDuplicate(numbers);
        this.lottoNumbers = numbers;
        Collections.sort(lottoNumbers);
    }

    public List<LottoNumber> createLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = LOTTO_MIN_RANGE; i <= LOTTO_MAX_RANGE; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, LOTTO_SIZE);
    }

    private void checkLottoSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_WRONG_LOTTO_SIZE);
        }
    }

    private void checkDuplicate(List<LottoNumber> numbers) {
        boolean result = numbers.stream()
                .allMatch(number -> Collections.frequency(numbers, number) == 1);
        if (!result) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_LOTTO_NUMBER);
        }

    }

    public boolean contains(final LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }
}
