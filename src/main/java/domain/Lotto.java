package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;


public class Lotto {

    public static final int LOTTO_PRICE = 1000;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MAX_LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto issueByNumberGenerator(NumberGenerator numberGenerator) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        while (lottoNumbers.size() < MAX_LOTTO_SIZE) {
            int number = numberGenerator.generate();
            LottoNumber lottoNumber = LottoNumber.of(number);

            addNumberIfUnique(lottoNumbers, lottoNumber);
        }

        lottoNumbers.sort(Comparator.naturalOrder());

        return new Lotto(lottoNumbers);
    }

    public static Lotto issueByNumbers(List<LottoNumber> numbers) {
        return new Lotto(numbers);
    }

    private static void addNumberIfUnique(List<LottoNumber> lottoNumbers, LottoNumber number) {
        if (!lottoNumbers.contains(number)) {
            lottoNumbers.add(number);
        }
    }

    public boolean hasNumber(LottoNumber value) {
        return numbers.contains(value);
    }

    public int calculateMatchCount(Lotto lotto) {
        return (int) IntStream.range(0, numbers.size())
                .filter(i -> lotto.numbers.contains(numbers.get(i)))
                .count();
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
