package lotto.util;

import lotto.domain.ticket.LottoNumber;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    public static List<Integer> parseManualStringtoInt(String numbers) {
        Pattern.compile("\\d(\\d)?(,\\d(\\d)?)*").matcher(StringUtils.deleteWhitespace(numbers));
        return Arrays.stream(numbers
                .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<LottoNumber> parseManualStringtoLottoNumber(String numbers) {
        Pattern.compile("\\d(\\d)?(,\\d(\\d)?)*").matcher(StringUtils.deleteWhitespace(numbers));
        return Arrays.stream(numbers
                .split(","))
                .map(Integer::parseInt)
                .map(x->LottoNumber.of(x))
                .collect(Collectors.toList());
    }
}


