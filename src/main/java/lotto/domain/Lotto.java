package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int SIZE_OF_LOTTO_NUMBERS = 6;
    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto of(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(number -> LottoNumber.from(number)).collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    public static Lotto random(LottoNumberGenerator lottoNumberGenerator) {
        List<Integer> lottoNumbers = lottoNumberGenerator.generateNumbers();
        return of(lottoNumbers);
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (hasDuplication(lottoNumbers)) {
            throw new DuplicatedLottoNumbersException("로또 번호가 중복되면 안 됩니다.");
        }
        if (lottoNumbers.size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new InvalidSizeOfLottoNumbersException("로또 번호는 6개여야 합니다.");
        }
    }

    private boolean hasDuplication(List<LottoNumber> lottoNumbers) {
        if ((new HashSet<>(lottoNumbers)).size() != lottoNumbers.size()) {
            return true;
        }
        return false;
    }

    public int countMatches(Lotto another) {
        int count = 0;
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            count += another.lottoNumbers.contains(lottoNumber) ? 1 : 0;
        }
        return count;
    }

    public static int getSizeOfLottoNumbers() {
        return SIZE_OF_LOTTO_NUMBERS;
    }

    public boolean contains(LottoNumber another) {
        return lottoNumbers.contains(another);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }
}
