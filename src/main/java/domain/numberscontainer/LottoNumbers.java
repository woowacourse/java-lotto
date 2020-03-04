package domain.numberscontainer;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * 6개의 숫자로 이루어진 집합
 */
public class LottoNumbers {
    private static final int SIZE = 6;
    private static final String LOTTO_NUMBER_SPLITTER = ",";

    protected final Set<LottoNumber> lottoNumbers;

    public LottoNumbers(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        this.lottoNumbers = Collections.unmodifiableSet(lottoNumbers);
    }

    public LottoNumbers(String value) {
        Set<LottoNumber> lottoNumbers = toLottoNumbers(value);
        validateSize(lottoNumbers);
        this.lottoNumbers = Collections.unmodifiableSet(lottoNumbers);
    }

    private Set<LottoNumber> toLottoNumbers(String value) {
        return Arrays.asList(value.split(LOTTO_NUMBER_SPLITTER)).stream()
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

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> get() {
        return lottoNumbers;
    }
}