package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.domain.lottonumber.LottoNumber;

/**
 * 사용자가 입력한 문자열을 나눠주는 유틸, 예외처리와 함께 문자열을 정수로 변환하는 메서드 그리고 들어온 문자열을 나눠서 LottoNumber 리스트로 반환하는 메서드가 있다
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class StringUtils {
	private static final String DELIMITER = ",";
	private static final String INPUT_LOTTO_NUMBER_VALIDATION_PATTERN = "^((( *\\d* *, *){5})\\d+)";
	private static final String NULL_INPUT_EXCEPTION_MESSAGE = "로또번호로 null 이 입력될 수 없습니다.";
	private static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "아무것도 입력하지 않으셨습니다.";
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "잘못된 형식으로 입력하셨습니다.";
	private static final String NONE_INTEGER_INPUT_EXCEPTION_MESSAGE = "입력 로또 번호가 정수가 아닙니다.";


	private StringUtils() {
	}

	public static int parseToInteger(final String inputBonusLottoNumber) {
		try {
			return Integer.parseInt(inputBonusLottoNumber);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException(NONE_INTEGER_INPUT_EXCEPTION_MESSAGE);
		}
	}

	public static List<LottoNumber> splitIntoLottoNumbers(final String inputLottoNumbers) {
		validateInputString(inputLottoNumbers);
		validateInputLottoNumber(inputLottoNumbers);
		return Arrays.stream(inputLottoNumbers.split(DELIMITER))
				.map(String::trim)
				.map(Integer::parseInt)
				.map(LottoNumber::of)
				.collect(Collectors.toList());
	}

	private static void validateInputString(final String inputString) {
		Objects.requireNonNull(inputString, NULL_INPUT_EXCEPTION_MESSAGE);
		validateInputStringEmpty(inputString);
	}

	private static void validateInputStringEmpty(final String inputLottoNumbers) {
		if (inputLottoNumbers.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
		}
	}

	private static void validateInputLottoNumber(final String inputLottoNumbers) {
		if (!inputLottoNumbers.matches(INPUT_LOTTO_NUMBER_VALIDATION_PATTERN)) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
	}
}
