package lotto.domain;

import java.util.*;

public class Lotto {
    public static final int LOTTO_NUMBERS_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(this.lottoNumbers);
    }

    public static Lotto from(String[] numbers) {
        validateNullAndEmptyValue(numbers);
        validateNumbersCount(numbers);
        List<LottoNumber> lottoNumbers = createLottoNumbers(numbers);
        validateDuplicatedInNumbers(lottoNumbers);
        return new Lotto(lottoNumbers);
    }

    public static Lotto create() {
        return new Lotto(LottoFactory.createLottoNumbers());
    }

    private static void validateNullAndEmptyValue(String[] number) {
        if (number == null || number.length == 0) {
            throw new RuntimeException("번호를 입력하지 않으셨습니다.");
        }
    }

    private static void validateNumbersCount(String[] number) {
        if (number.length != LOTTO_NUMBERS_COUNT) {
            throw new RuntimeException(String.format("번호는 %d개를 입력하셔야 합니다.", LOTTO_NUMBERS_COUNT));
        }
    }

    private static List<LottoNumber> createLottoNumbers(String[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String number : numbers) {
            lottoNumbers.add(LottoNumber.of(number.trim()));
        }
        return lottoNumbers;
    }

    private static void validateDuplicatedInNumbers(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> numbers = new HashSet<>(lottoNumbers);
        if (numbers.size() != LOTTO_NUMBERS_COUNT) {
            throw new RuntimeException("중복된 숫자가 입력되었습니다.");
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    public List<LottoNumber> get() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
