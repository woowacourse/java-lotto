package lotto.util;

import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 클래스 이름 : StringUtils.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class StringUtils {
	private static final String DELIMITER = ",";

	private StringUtils() {}

	public static List<Integer> splitIntoIntList(final String lottoNumbersInput) {
		Objects.requireNonNull(lottoNumbersInput, "로또번호로 null 이 입력될 수 없습니다.");

		return Arrays.stream(lottoNumbersInput.split(DELIMITER))
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}
}
