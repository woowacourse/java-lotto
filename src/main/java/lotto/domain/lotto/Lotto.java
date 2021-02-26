package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.lottogenerator.LottoGenerator;

public class Lotto {

    private static final String NUM_LOTTO_ERROR_MESSAGE = "로또 번호는 %d개의 숫자로 이루어져야 합니다.";
    private static final String DUPLICATION_LOTTO_ERROR_MESSAGE = "로또 번호는 중복된 숫자가 존재할 수 없습니다.";
    private static final String ERROR_COLLECT_NUMBER = "올바른 숫자를 입력하여 주세요";

    private static final String REGEX = ", ";

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto manual(String numbers) {
        List<Integer> lottoNumbers;
        try {
            lottoNumbers = Arrays
                .stream(numbers.split(REGEX))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_COLLECT_NUMBER);
        }
        return generate(lottoNumbers);
    }

    public static Lotto generate(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
        return Lotto.generate(() -> lottoNumbers);
    }

    public static Lotto generate(LottoGenerator lottoGenerator) {
        List<LottoNumber> lottoNumbers = lottoGenerator.generateLottoNumbers();
        validateLotto(lottoNumbers);
        return new Lotto(lottoNumbers);
    }

    private static void validateLotto(List<LottoNumber> lottoNumbers) {
        validateLottoLength(lottoNumbers);
        validateLottoNumberNotDuplicate(lottoNumbers);
    }

    private static void validateLottoLength(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LottoNumber.NUM_LOTTO_LIMIT) {
            throw new IllegalArgumentException(
                String.format(NUM_LOTTO_ERROR_MESSAGE, LottoNumber.NUM_LOTTO_LIMIT));
        }
    }

    private static void validateLottoNumberNotDuplicate(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> distinctedLottoNumbers = lottoNumbers.stream()
            .distinct()
            .collect(Collectors.toList());

        if (distinctedLottoNumbers.size() != LottoNumber.NUM_LOTTO_LIMIT) {
            throw new IllegalArgumentException(DUPLICATION_LOTTO_ERROR_MESSAGE);
        }
    }

    public LottoRank check(WinningLotto winningLotto) {
        boolean bonus = this.numbers.contains(winningLotto.getBonus());
        int count = numMatches(winningLotto.getLotto());
        return findRank(count, bonus);
    }

    public LottoRank findRank(int count, boolean bonus) {
        return LottoRank.checkRank(count, bonus);
    }

    private int numMatches(Lotto lotto) {
        return (int) this.numbers.stream().
            filter(number -> lotto.getNumbers().contains(number))
            .count();
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }
}