package lotto.validator;

import java.util.List;

import lotto.domain.LottoNo;

public class Validator {
	public static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";
	private static final String ERROR_MESSAGE_LOTTO_SIZE = "6개의 숫자가 아닙니다.";
	private static final String ERROR_MESSAGE_LOTTO_RANGE = "1이상 45이하의 숫자를 입력하세요.";
	private static final int LOTTO_SIZE = 6;
	private static final int MIN_LOTTO_NO = 1;
	private static final int MAX_LOTTO_NO = 45;

	public static void validateInteger(String inputMoney) {
		try {
			Integer.parseInt(inputMoney);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
		}
	}

	public static void validateLottoRange(int i) {
		if (i < MIN_LOTTO_NO || i > MAX_LOTTO_NO) {
			throw new IllegalArgumentException(ERROR_MESSAGE_LOTTO_RANGE);
		}
	}

	public static void validateLottoSize(List<LottoNo> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException(ERROR_MESSAGE_LOTTO_SIZE);
		}
	}
}
