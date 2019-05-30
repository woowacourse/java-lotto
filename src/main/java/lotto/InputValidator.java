package lotto;

import lotto.Exception.duplicateBonusBallException;
import lotto.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class InputValidator {
    private static final int LOTTO_SIZE = 6;

    public static boolean isNotValidPrice(String priceInput) {
        return isBlank(priceInput) || isNotInteger(priceInput) || isNotValidPriceRange(priceInput);
    }

    private static boolean isNotValidPriceRange(String price) {
        try {
            MoneyFactory.createMoney(Integer.parseInt(price));
            return false;
        } catch (RuntimeException e) {
            return true;
        }
    }

    private static boolean isBlank(String price) {
        if (price.contains(" ")) {
            return true;
        }
        return false;
    }

    private static boolean isNotInteger(String price) {
        try {
            Integer.parseInt(price);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isNotValidLotto(List<String> lottoNumbersInput) {
        return isNotValidLottoNumbersFormat(lottoNumbersInput)
                || isDuplicateLottoNumber(lottoNumbersInput)
                || isNotValidLottoSize(lottoNumbersInput);
    }

    private static boolean isNotValidLottoSize(List<String> lottoNumbersInput) {
        return lottoNumbersInput.size() != LOTTO_SIZE;
    }

    private static boolean isNotValidLottoNumbersFormat(List<String> lottoNumbersInput) {
        return lottoNumbersInput.stream() //로또숫자가 유효한지 검사
                .filter(s -> !isBlank(s))
                .filter(s -> !isNotInteger(s))
                .filter(s -> !isNotValidLottoNumber(Integer.parseInt(s)))
                .collect(Collectors.toList())
                .size() != lottoNumbersInput.size();
    }

    private static boolean isDuplicateLottoNumber(List<String> lottoNumbersInput) {
        return lottoNumbersInput.stream() //중복된 숫자있는지 검사
        .distinct()
        .collect(Collectors.toList())
        .size() != lottoNumbersInput.size();
    }

    private static boolean isNotValidLottoNumber(int lottoNumber) {
        return LottoNumber.isNotValidLottoNumber(lottoNumber);
    }

    public static boolean isNotValidWinningLotto(Lotto lotto, String bonusBall) {
        return isNotInteger(bonusBall)
                || isDuplicateBonusBall(lotto, bonusBall)
                || isNotValidLottoNumber(Integer.parseInt(bonusBall));
    }

    private static boolean isDuplicateBonusBall(Lotto lotto, String bonusBall) {
        try {
            LottoFactory.createWinningLotto(lotto, Integer.parseInt(bonusBall));
            return false;
        } catch (duplicateBonusBallException e) {
            return true;
        }
    }
}
