package lotto.generator;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserInputNumberGenerator implements NumberGenerator {
    private static final String DELIMITER = ",";

    @Override
    public List<LottoNumber> generateNumbers() {
        return Collections.emptyList();
    }

    @Override
    public List<LottoNumber> generateNumbers(String userInput) {
        List<LottoNumber> winningNumbers = new ArrayList<>();
        String[] userInputs = userInput.split(DELIMITER);
        for (String input : userInputs) {
            LottoNumber userNumber = new LottoNumber(input.trim());
            winningNumbers.add(userNumber);
        }
        return winningNumbers;
    }
}
