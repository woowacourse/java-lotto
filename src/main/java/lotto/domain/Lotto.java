package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int SIZE_OF_LOTTO_NUMBERS = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto() {
        List<LottoNumber> lottoNumbers = LottoNumber.getAll();
        Collections.shuffle(lottoNumbers);
        lottoNumbers = lottoNumbers.subList(0, SIZE_OF_LOTTO_NUMBERS + 1);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(List<Integer> numbers) {
        if (numbers == null || hasNull(numbers)) {
            throw new NullArgumentException("생성자의 인자로 null 을 넘길 수 없습니다.");
        }
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(number -> LottoNumber.get(number)).collect(Collectors.toList());
        validateLottoNumbers(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
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

    private boolean hasNull(List lottoNumbers) {
        return lottoNumbers.contains(null);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }

    public int countMatches(Lotto another) {
        int count = 0;
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            count += another.lottoNumbers.contains(lottoNumber) ? 1 : 0;
        }
        return count;
    }
}
