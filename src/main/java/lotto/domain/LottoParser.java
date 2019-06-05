package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoParser {

    public static List<Integer> parseLottoNumbers(String[] scannedLotto) {
        List<Integer> winningLottoNumbers = new ArrayList<>();
        Arrays.stream(scannedLotto)
                .forEach(number -> winningLottoNumbers.add(Integer.parseInt(number)));
        return winningLottoNumbers;
    }

}
