package lottogame.utils;

import lottogame.domain.InvalidLottoNumberException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersParser {
    public static List<Integer> parse(String lottoNumbers) {
        try {
            return Arrays.stream(lottoNumbers.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidLottoNumberException("올바른 로또 숫자를 입력해 주세요.");
        }
    }
}
