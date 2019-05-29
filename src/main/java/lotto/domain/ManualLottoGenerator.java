package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator {

    public static List<LottoNumber> makeLotto(List<Integer> inputNumbers) {
        List<LottoNumber> generatedLottoNumbers = new ArrayList<>();

        for (int inputNumber : inputNumbers) {
            generatedLottoNumbers.add(LottoNumbers.of(inputNumber));
        }

        return generatedLottoNumbers;
    }
}
