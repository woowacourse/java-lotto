package lotto.util;

import lotto.domain.lottoTicket.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertInput {
    public static List<LottoNumber> convertLottoNumbers(String input) {
        input = removeBlank(input);
        return splitLottoNumbers(input)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    static List<Integer> splitLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static String removeBlank(String input) {
        return input.replace(" ", "");
    }
}
