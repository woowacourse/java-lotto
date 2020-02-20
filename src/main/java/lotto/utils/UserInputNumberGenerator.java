package lotto.utils;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UserInputNumberGenerator implements NumberGenerator {

    @Override
    public List<LottoNumber> generateNumbers() {
        return null;
    }

    public List<LottoNumber> generateNumbers(String userInput) {
        List<LottoNumber> winningNumbers = new ArrayList<>();
        String[] userInputs = userInput.split(",");

        for (int i = 0; i < userInputs.length; i++) {
            winningNumbers.add(new LottoNumber(userInputs[i].trim()));
        }
        return winningNumbers;
    }

}
