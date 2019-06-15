package lotto.service;

import lotto.domain.*;
import lotto.dto.LottoDto;
import lotto.dto.UserLottoDto;

import java.util.List;

public class LottoService {
    private static final String DELIMITER = ",";
    private static final int FIRST_INDEX = 0;

    public LottoDto offerLottoInfo(int money, int manualRound, String manualNumbers) {
        int round = new Money(money).getRound();
        int autoRound = getAutoRound(round, manualRound);
        String[] numbers = splitNumbers(manualNumbers);
        UserLotto userLotto = createUserLotto(numbers, autoRound);

        return new LottoDto(round, manualRound, autoRound, userLotto.getUserLotto(), numbers);
    }

    public UserLottoDto offerUserLottoInfo(int round, String userLottoString) {
        String[] numbers = Parser.parseLottoStrings(userLottoString);
        return new UserLottoDto(round, new UserLotto(Parser.parseLotto(numbers)));
    }

    private UserLotto createUserLotto(String[] numbers, int autoRound) {
        return new UserLotto(Parser.parseLotto(numbers), autoRound, new LottoNumberGenerator());
    }

    private String[] splitNumbers(String numbers) {
        if (numbers.length() == 0) {
            return new String[FIRST_INDEX];
        }
        return numbers.split("\r\n");
    }

    private int getAutoRound(int round, int manualRound) {
        return round - manualRound;
    }
}
