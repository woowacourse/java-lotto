package lotto.service;

import lotto.domain.LottoNumberGenerator;
import lotto.domain.Parser;
import lotto.domain.UserLotto;

public class LottoService {
    private static final int FIRST_INDEX = 0;

    public UserLotto createUserLotto(String[] numbers, int autoRound) {
        return new UserLotto(Parser.parseLotto(numbers), autoRound, new LottoNumberGenerator());
    }

    public String[] splitNumbers(String numbers) {
        if (numbers.length() == 0) {
            return new String[FIRST_INDEX];
        }
        return numbers.split("\r\n");
    }

    public int getAutoRound(int round, int manualRound) {
        return round - manualRound;
    }
}
