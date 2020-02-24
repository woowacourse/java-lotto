package lotto.utils;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class UserInputNumberGenerator implements NumberGenerator {
    private static final String DELIMITER = ",";

    @Override
    public List<LottoNumber> generateNumbers() {
        return null;
    }

    @Override
    public List<LottoNumber> generateNumbers(String userInput) {
        List<LottoNumber> winningNumbers = new ArrayList<>();
        String[] userInputs = userInput.split(DELIMITER);

        for (int i = 0; i < userInputs.length; i++) {
            winningNumbers.add(new LottoNumber(userInputs[i]
                    .trim()));
        }
        return winningNumbers;
    }
}
