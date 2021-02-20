package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.view.InputView.convertStringsToInts;
import static org.junit.jupiter.api.Assertions.*;

class ManualLottoGeneratorTest {
    public static Lotto createCustomLotto(String customNumbers) {
        int[] numbers = convertStringsToInts(customNumbers.split(", "));
        return new ManualLottoGenerator(numbers).createLotto();
    }

    @Test
    void createLotto() {
    }

    @Test
    void createLottoNumbers() {
    }
}