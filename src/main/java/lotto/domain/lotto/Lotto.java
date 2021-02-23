package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lottogenerator.LottoGenerator;

public class Lotto {

    public static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_LENGTH = 6;

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto of(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
            .map(number -> LottoNumber.valueOf(number))
            .collect(Collectors.toList());
        return Lotto.generatedBy(() -> lottoNumbers);
    }

    public static Lotto generatedBy(LottoGenerator lottoGenerator) {
        List<LottoNumber> lottoNumbers = lottoGenerator.generateLottoNumbers();
        validateLotto(lottoNumbers);
        return new Lotto(lottoNumbers);
    }

    private static void validateLotto(List<LottoNumber> lottoNumbers) {
        validateLottoLength(lottoNumbers);
        validateLottoNumberNotDuplicate(lottoNumbers);
    }

    private static void validateLottoLength(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException("로또 번호는 6개의 숫자로 이루어져야 합니다.");
        }
    }

    private static void validateLottoNumberNotDuplicate(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> distinctedLottoNumbers = lottoNumbers.stream()
            .distinct()
            .collect(Collectors.toList());

        if (distinctedLottoNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException("로또 번호는 중복된 숫자가 존재할 수 없습니다.");
        }
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public boolean isContains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int match(Lotto lotto) {
        return (int) numbers.stream()
            .filter(number -> lotto.isContains(number))
            .count();
    }
}