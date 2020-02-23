package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 선택한 로또 하나를 나타내는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class Lotto {
	public static final int SIZE = 6;
	private static final String DUPLICATED_NUMBER_MESSAGE = "로또 번호가 중복됩니다.";
	private static final String INVALID_SIZE_MESSAGE = "로또 번호가 존재하지 않습니다.";
	private static final String NUMBER_DELIMITER = ",";

	private final List<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		validate(numbers);
		this.numbers = numbers.stream()
				.sorted()
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}

	static Lotto of(int... numbers) {
		return new Lotto(Arrays.stream(numbers)
				.mapToObj(LottoNumber::of)
				.collect(Collectors.toList()));
	}

	public static Lotto ofComma(String input) {
		return new Lotto(Stream.of(input.split(NUMBER_DELIMITER))
				.map(String::trim)
				.map(Integer::valueOf)
				.map(LottoNumber::of)
				.collect(Collectors.toList()));
	}

	private void validate(List<LottoNumber> numbers) {
		validateSize(numbers);
		validateDuplicate(numbers);
	}

	private void validateSize(List<LottoNumber> numbers) {
		if (Objects.isNull(numbers) || numbers.size() != SIZE) {
			throw new IllegalArgumentException(INVALID_SIZE_MESSAGE);
		}
	}

	private void validateDuplicate(List<LottoNumber> numbers) {
		Set<LottoNumber> distinctNumbers = new HashSet<>(numbers);
		if (distinctNumbers.size() != numbers.size()) {
			throw new IllegalArgumentException(DUPLICATED_NUMBER_MESSAGE);
		}
	}

	public boolean contains(LottoNumber number) {
		return numbers.contains(number);
	}

	public MatchCount calculateMatchCount(Lotto target) {
		return MatchCount.of((int)numbers.stream()
				.filter(target::contains)
				.count());
	}

	public List<LottoNumber> getLotto() {
		return numbers;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Lotto that = (Lotto)object;
		return Objects.equals(numbers, that.numbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numbers);
	}
}
