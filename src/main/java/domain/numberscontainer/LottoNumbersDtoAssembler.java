package domain.numberscontainer;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * LottoNumbersDto 생성 담당 클래스
 */
public class LottoNumbersDtoAssembler {
    private static final String SPLIT_REGEX = ",";

    public static LottoNumbersDto assemble(String numbers) {
        return new LottoNumbersDto(parseSet(numbers));
    }

    public static LottoNumbersDto assemble(Set<LottoNumber> numbers) {
        return new LottoNumbersDto(numbers);
    }

    private static LinkedHashSet<LottoNumber> parseSet(String numbersInput) {
        return Arrays.asList(numbersInput.split(SPLIT_REGEX)).stream()
                .map(String::trim)
                .map(Integer::new)
                .map(LottoNumber::get)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
