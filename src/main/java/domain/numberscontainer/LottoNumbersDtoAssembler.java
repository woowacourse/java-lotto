package domain.numberscontainer;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//dto -> 도메인, 도메인 -> dto로 변환 담당.
public class LottoNumbersDtoAssembler {
    /*
    private static final String SPLIT_REGEX = ",";

    public static LottoNumbersDto assemble(List<String> numbers) {
        return new LottoNumbersDto(numbers);
    }

    public static List<LottoNumbers> assemble(LottoNumbersDto numbers) {
        return numbers.getLottoNumbers().stream()
                .map(String::trim)

                .collect(Collectors.toList());
    }

    private static Set<>

    private static Set<LottoNumber> parseSet(String rawNumbers) {
        return Arrays.asList(rawNumbers.split(SPLIT_REGEX)).stream()
                .map(String::trim)
                .map(Integer::new)
                .map(LottoNumber::get)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }*/
}
