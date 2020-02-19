package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 선택한 로또 하나를 나타내는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class Lotto implements Iterable<LottoNumber> {
	public static final int SIZE = 6;
	private static final String DUPLICATED_NUMBER_MESSAGE = "로또 번호가 중복됩니다.";
	private static final String INVALID_SIZE_MESSAGE = "로또 번호가 존재하지 않습니다.";

	private final List<LottoNumber> numbers = new ArrayList<>();

	public Lotto(List<LottoNumber> numbers) {
		validate(numbers);
		this.numbers.addAll(numbers);
		Collections.sort(this.numbers);
	}

	static Lotto of(int... numbers) {
		return new Lotto(Arrays.stream(numbers)
				.mapToObj(LottoNumber::of)
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

	public long calculateMatchCount(Lotto target) {
		return numbers.stream()
				.filter(target::contains)
				.count();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Lotto that = (Lotto)o;
		return Objects.equals(numbers, that.numbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numbers);
	}

	@Override
	public Iterator<LottoNumber> iterator() {
		return numbers.iterator();
	}
}
