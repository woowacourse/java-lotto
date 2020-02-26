package lotto.validator;

import lotto.domain.LottoNumber;
import lotto.domain.Payment;
import lotto.domain.errors.ErrorMessage;

import java.util.List;

public class Validator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int MONEY_PER_LOTTO = 1000;

    public static void validateNumberCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            ErrorMessage nowErrorMessage = ErrorMessage.NUMBER_COUNT_NOT_SIX;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public static void validateDistinctNumbers(final List<LottoNumber> inputNumbers) {
        if (getDistinctSize(inputNumbers) != LOTTO_NUMBER_SIZE) {
            ErrorMessage nowErrorMessage = ErrorMessage.DUPLICATE_NUMBER;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    private static long getDistinctSize(final List<LottoNumber> inputNumbers) {
        return inputNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .distinct()
                .count();
    }

    public static void validatePositiveNumber(final int manualCount) {
        if (manualCount < 0) {
            ErrorMessage nowErrorMessage = ErrorMessage.NOT_POSITIVE;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public static void validateNumberScope(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER
                || lottoNumber > MAX_LOTTO_NUMBER) {
            ErrorMessage nowErrorMessage = ErrorMessage.OVER_SCOPE;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public static void validateNumber(final String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            ErrorMessage nowErrorMessage = ErrorMessage.NOT_NUMBER;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public static void validatePricePerLotto(final int inputMoney) {
        if (inputMoney % MONEY_PER_LOTTO != 0) {
            ErrorMessage nowErrorMessage = ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public static void validateUnderLottoPrice(final int inputMoney) {
        if (inputMoney < MONEY_PER_LOTTO) {
            ErrorMessage nowErrorMessage = ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public static void validateLessThanLottoCount(int manualCount, Payment lottoCouunt) {
        if (manualCount > lottoCouunt.getPurchasedCount()) {
            ErrorMessage nowErrorMessage = ErrorMessage.MANUAL_COUNT_OVER_TOTAL;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }


    public static void validateDistinctBonus(List<LottoNumber> lottoNumbers, final LottoNumber bonusNumber) {
        if (isBonusNumberAlreadyExist(lottoNumbers, bonusNumber)) {
            ErrorMessage nowErrorMessage = ErrorMessage.DUPLICATE_NUMBER;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    private static boolean isBonusNumberAlreadyExist(List<LottoNumber> lottoNumbers, LottoNumber inputBonusNumber) {
        return lottoNumbers.stream()
                .anyMatch(inputBonusNumber::equals);
    }
}
