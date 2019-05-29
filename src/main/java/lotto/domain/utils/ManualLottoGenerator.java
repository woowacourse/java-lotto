package lotto.domain.utils;

import lotto.domain.Number;
import lotto.domain.NumberSet;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator {

    public static List<Number> makeLotto(List<Integer> inputNumbers) {
        List<Number> generatedLottoNumbers = new ArrayList<>();

        for (int inputNumber : inputNumbers) {
            generatedLottoNumbers.add(NumberSet.of(inputNumber));
        }

        return generatedLottoNumbers;
    }
}
