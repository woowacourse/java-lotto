package service;

import static constant.LottoConstants.LOTTO_PRICE;
import static constant.LottoConstants.LOTTO_RANGE_MAX;
import static constant.LottoConstants.LOTTO_RANGE_MIN;
import static exception.ErrorMessage.PRICE_RANGE_ERROR;
import static exception.ErrorMessage.PRICE_UNIT_ERROR;

import domain.Lotto;
import dto.IssuedLottoDto;
import dto.IssuedLottosDto;
import exception.UserInputException;
import java.util.ArrayList;
import java.util.List;
import util.RandomNumberGenerator;


public class IssueLottoService {

    public IssuedLottosDto issueLottos(int money) {
        validateMoney(money);
        int count = money / LOTTO_PRICE.getValue();
        List<IssuedLottoDto> issuedLottos = new ArrayList<>();
        while (issuedLottos.size() != count) {
            addUniqueLotto(issuedLottos);
        }
        return new IssuedLottosDto(issuedLottos);
    }

    private void addUniqueLotto(List<IssuedLottoDto> issuedLottos) {
        Lotto lotto = new Lotto(
                RandomNumberGenerator.getRandomNumbers(LOTTO_RANGE_MIN.getValue(), LOTTO_RANGE_MAX.getValue()));
        if (!isDuplicate(issuedLottos, lotto.getNumbers())) {
            issuedLottos.add(new IssuedLottoDto(lotto.getNumbers()));
        }
    }

    private void validateMoney(int money) {
        if (money <= 0) {
            throw UserInputException.from(PRICE_RANGE_ERROR);
        }
        if (money % LOTTO_PRICE.getValue() != 0) {
            throw UserInputException.from(PRICE_UNIT_ERROR);
        }
    }

    private boolean isDuplicate(List<IssuedLottoDto> lottos, List<Integer> numbers) {
        IssuedLottoDto issuedLottoDto = new IssuedLottoDto(numbers);
        return lottos.contains(issuedLottoDto);
    }
}
