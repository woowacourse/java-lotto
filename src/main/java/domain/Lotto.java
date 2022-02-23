package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> lottoNumbers = new ArrayList<>();

    public Lotto(List<Integer> lottoNumbers) {
        for (Integer lottoNumber : lottoNumbers) {
            validateLottoNumber(lottoNumber);
            this.lottoNumbers.add(lottoNumber);
        }
    }

    private void validateLottoNumber(Integer lottoNumber) {
        validateNumberRange(lottoNumber);
        validateDuplicatedNumber(lottoNumber);
    }

    private void validateNumberRange(Integer lottoNumber) {
        if (lottoNumber > 45 || lottoNumber <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicatedNumber(Integer lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public String toString() {
        List<String> numbersToStrings = lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        return "[" + String.join(", ", numbersToStrings) + "]";
    }
}
