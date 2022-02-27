package lotto.exception;

import lotto.domain.Money;

public class InvalidException {
    public static final Character LOTTO_DELIMITER = ',';
    public static final String ERROR_CREATE_LOTTO = "[ERROR] 잘못된 숫자 입력입니다.";
    public static final String ERROR_INTEGER_RANGE = "[ERROR] 1~45 사이의 수가 아닙니다.";
    public static final String ERROR_NOT_INTEGER = "[ERROR] 양의 정수가 아닙니다.";
    public static final String ERROR_WRONG_INPUT_MONEY =
            "[ERROR] 올바른 구매 값을 입력해주세요. 로또가격: " + Money.getBasicLottoMoney() + "원";
    public static final String ERROR_DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 숫자 입력입니다.";
}
