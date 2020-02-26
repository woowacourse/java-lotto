package domain.numberscontainer;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SixLottoNumbersDTO {
    private final Set<LottoNumber> sixNumbers;

    public SixLottoNumbersDTO(String sixNumbersInput) {
        Set<LottoNumber> sixNumbers = parseSixNumbers(sixNumbersInput);
        this.sixNumbers = sixNumbers;
    }

    public SixLottoNumbersDTO(Set<LottoNumber> sixNumbers) {
        this.sixNumbers = sixNumbers;
    }

    private Set<LottoNumber> parseSixNumbers(String sixNumbersInput) {
        return Arrays.asList(sixNumbersInput.split(",")).stream()
                .map(String::trim)
                .map(Integer::new)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());
    }

    public Set<LottoNumber> getSixNumbers() {
        return sixNumbers;
    }
}