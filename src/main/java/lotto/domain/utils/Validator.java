package lotto.domain.utils;

import lotto.domain.Money;
import lotto.domain.Number;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {
    private static final String MONEY_LIMIT_EXCEPTION_MESSAGE = "1,000원 이상 100,000원 미만의 금액을 입력해주세요.";
    private static final String MONEY_EXCHANGE_EXCEPTION_MESSAGE = "1,000원 단위로 입력해주세요.";
    private static final String DUPLICATION_EXCEPTION_MESSAGE = "중복된 번호가 존재합니다.";
    private static final String AVAILABLE_PURCHASE_SIZE_EXCEPTION_MESSAGE = "구입 가능 매수를 초과하였습니다.";
    private static final int MAX_PURCHASE_PRICE = 100000;
    private static final int MIN_PURCHASE_PRICE = 1000;
    private static final int LOTTO_PRICE = 1000;
    private static final int MAX_LOTTO_SIZE = 6;

    public static void checkInputMoney(int inputMoney) {
        checkExchange(inputMoney);
        checkMoneyLimitRange(inputMoney);
    }

    private static void checkExchange(int inputMoney) {
        if (inputMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(MONEY_EXCHANGE_EXCEPTION_MESSAGE);
        }
    }

    private static void checkMoneyLimitRange(int inputMoney) {
        if (inputMoney < MIN_PURCHASE_PRICE || inputMoney > MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException(MONEY_LIMIT_EXCEPTION_MESSAGE);
        }
    }

    public static void checkManualLottoSize(int manualLottoSize, Money money) {
        if (manualLottoSize > money.availablePurchseTicketCount()) {
            throw new IllegalArgumentException(AVAILABLE_PURCHASE_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public static void checkDuplication(List<Number> lottoNumber) {
        Set<Number> lottoSetforCheckingDuplication = new HashSet<>(lottoNumber);
        if (lottoSetforCheckingDuplication.size() != MAX_LOTTO_SIZE) {
            throw new IllegalArgumentException(DUPLICATION_EXCEPTION_MESSAGE);
        }
    }
}
