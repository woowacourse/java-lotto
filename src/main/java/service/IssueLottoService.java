package service;

import static constant.LottoConstants.LOTTO_PRICE;
import static constant.LottoConstants.LOTTO_RANGE_MAX;
import static constant.LottoConstants.LOTTO_RANGE_MIN;
import static exception.ExceptionMessage.PRICE_RANGE_ERROR;
import static exception.ExceptionMessage.PRICE_UNIT_ERROR;

import domain.Lotto;
import dto.IssuedLottoDto;
import dto.IssuedLottosDto;
import exception.LottoException;
import java.util.ArrayList;
import java.util.List;
import util.RandomNumberGenerator;


public class IssueLottoService {

    public IssuedLottosDto issueLottos(int money) {
        validateMoney(money);
        int count = money / LOTTO_PRICE.getValue();
        List<IssuedLottoDto> issuedLottos = new ArrayList<>();
        while (issuedLottos.size() != count) {
            issuedLottos.add(makeUniqueLotto(issuedLottos));
        }
        return new IssuedLottosDto(issuedLottos);
    }

    private void validateMoney(int money) {
        if (money <= 0) {
            throw LottoException.from(PRICE_RANGE_ERROR);
        }
        if (money % LOTTO_PRICE.getValue() != 0) {
            throw LottoException.from(PRICE_UNIT_ERROR);
        }
    }

    private IssuedLottoDto makeUniqueLotto(final List<IssuedLottoDto> lottos) {
        List<Integer> newNumbers;
        do {
            Lotto lotto = new Lotto(
                    RandomNumberGenerator.getRandomNumbers(
                            LOTTO_RANGE_MIN.getValue(),
                            LOTTO_RANGE_MAX.getValue()));
            newNumbers = lotto.getSortedNumbers();
        } while (isDuplicate(lottos, newNumbers));
        return new IssuedLottoDto(newNumbers);
    }

    private boolean isDuplicate(final List<IssuedLottoDto> lottos, List<Integer> numbers) {
        IssuedLottoDto issuedLottoDto = new IssuedLottoDto(numbers);
        return lottos.contains(issuedLottoDto);
    }
}
