package lotto.domain;

import java.util.*;

public class Lotto {
    public static final int LOTTO_NUMBERS_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(this.lottoNumbers);
    }

    Lotto() {
        this(createLottoNumbers());
    }

    public static Lotto from(String[] numbers) {
        validateEmptyValue(numbers);
        validateNumbersCount(numbers);
        List<LottoNumber> lottoNumbers = createLottoNumbers(numbers);
        validateDuplicatedNumbers(lottoNumbers);
        return new Lotto(lottoNumbers);
    }

    private static List<LottoNumber> createLottoNumbers() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() != LOTTO_NUMBERS_COUNT) {
            lottoNumbers.add(LottoNumber.randomBy());
        }
        return new ArrayList<>(lottoNumbers);
    }

    private static void validateEmptyValue(String[] number) {
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

    private static void validateDuplicatedNumbers(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> numbers = new HashSet<>(lottoNumbers);
        if (numbers.size() != LOTTO_NUMBERS_COUNT) {
            throw new RuntimeException("중복된 숫자가 입력되었습니다.");
        }
    }

    public MatchResult createResult(Lotto winningLotto, LottoNumber bonusNumber) {
        int sameNumbersCount = countSameNumbers(winningLotto);
        boolean containsBonusNumber = lottoNumbers.contains(bonusNumber);
        return MatchResult.of(sameNumbersCount, containsBonusNumber);
    }

    public int countSameNumbers(Lotto winningLotto) {
        Set<LottoNumber> numbers = new HashSet<>(this.lottoNumbers);
        return (int) winningLotto.get().stream()
                .filter(numbers::contains)
                .count();
    }

    public List<LottoNumber> get() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
