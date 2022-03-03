package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class LottoTestDataGenerator {

    public static List<List<LottoNumber>> generateLottoTickets() {
        return List.of(
                parseLottoNumbers(List.of(8, 21, 23, 41, 42, 43)),
                parseLottoNumbers(List.of(3, 5, 11, 16, 32, 38)),
                parseLottoNumbers(List.of(7, 11, 16, 35, 36, 44))
        );
    }

    public static List<LottoNumber> generateNumbers() {
        return parseLottoNumbers(List.of(8, 21, 23, 41, 42, 43));
    }

    public static List<LottoNumber> parseLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .collect(toList());
    }
}
