package domain.numberscontainer;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumbersDto {
    private final LinkedHashSet<LottoNumber> lottoNumbers;

    public LottoNumbersDto(String sixNumbersInput) {
        this.lottoNumbers = new LinkedHashSet<>(parseSet(sixNumbersInput));
    }

    public LottoNumbersDto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new LinkedHashSet<>(lottoNumbers);
    }

    private Set<LottoNumber> parseSet(String sixNumbersInput) {
        return Arrays.asList(sixNumbersInput.split(",")).stream()
                .map(String::trim)
                .map(Integer::new)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}