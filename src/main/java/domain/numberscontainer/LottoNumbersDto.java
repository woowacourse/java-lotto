package domain.numberscontainer;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumbersDto {
    private static final String SPLIT_REGEX = ",";

    private final LinkedHashSet<LottoNumber> lottoNumbers;

    public LottoNumbersDto(String numbersInput) {
        this.lottoNumbers = new LinkedHashSet<>(parseSet(numbersInput));
    }

    public LottoNumbersDto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new LinkedHashSet<>(lottoNumbers);
    }

    private LinkedHashSet<LottoNumber> parseSet(String numbersInput) {
        return Arrays.asList(numbersInput.split(SPLIT_REGEX)).stream()
                .map(String::trim)
                .map(Integer::new)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}