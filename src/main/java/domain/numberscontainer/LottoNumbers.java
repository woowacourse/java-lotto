package domain.numberscontainer;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int SIZE = 6;

    protected final Set<LottoNumber> lottoNumbers;

    public LottoNumbers(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoNumbers(String value) {
        Set<LottoNumber> lottoNumbers = toLottoNumbers(value);
        validateSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private Set<LottoNumber> toLottoNumbers(String value) {
        return Arrays.asList(value.split(",")).stream()
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::get)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    protected void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException(String.format("중복되지 않는 %d개의 숫자를 입력해주세요.", SIZE));
        }
    }
}