package lotto.util;

import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class StringUtils {
	private static final String DELIMITER = ", ";

	private StringUtils() {}

	public static List<LottoNumber> splitIntoLottoNumbers(final String lottoNumbersInput) {
		Objects.requireNonNull(lottoNumbersInput, "로또번호로 null 이 입력될 수 없습니다.");

		return Arrays.stream(lottoNumbersInput.split(DELIMITER))
						.map(str -> Integer.parseInt(str))
						.map(LottoNumber::of)
						.collect(Collectors.toList());
	}
}
