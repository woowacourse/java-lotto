package lotto.domain;

import static java.util.stream.Collectors.toList;
import static lotto.domain.LottoNumber.MAX_BOUND;
import static lotto.domain.LottoNumber.MIN_BOUND;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Lotto {
    private static final List<Integer> numbers = IntStream.rangeClosed(MIN_BOUND, MAX_BOUND).boxed().collect(toList());
    private static final int CHOICE_NUMBER_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto() {
        Collections.shuffle(numbers);
        lotto = collectRightSizeOfNumber();
        Collections.sort(lotto);
    }

    public Lotto(List<Integer> inputValues) {
        List<LottoNumber> lottoNumbers = inputValues.stream()
                .map(inputValue -> new LottoNumber(inputValue))
                .collect(toList());
        checkSize(lottoNumbers);
        checkDuplicateNumbers(lottoNumbers);
        this.lotto = lottoNumbers;
    }

    private void checkSize(List<LottoNumber> inputValues) {
        if (inputValues.size() != CHOICE_NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요");
        }
    }

    private void checkDuplicateNumbers(List<LottoNumber> inputValues) {
        HashSet<LottoNumber> lottoSet = new HashSet<>(inputValues);
        if (lottoSet.size() != inputValues.size()) {
            throw new IllegalArgumentException("[ERROR] 선택한 번호중에 중복되는 값이 있습니다.");
        }
    }

    public int countSameNumber(Lotto numbers) {
        return (int) lotto.stream()
                .filter(numbers.getLotto()::contains)
                .count();
    }

    private List<LottoNumber> collectRightSizeOfNumber() {
        return numbers.stream()
                .limit(CHOICE_NUMBER_SIZE)
                .map(number -> new LottoNumber(number))
                .collect(toList());
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
