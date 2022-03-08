package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {
	public static final int SIZE = 6;

	private final Set<LottoNumber> lotto;

	public Lotto(Set<LottoNumber> numbers) {
		checkSize(numbers);
		this.lotto = numbers;
	}

	public static Lotto from(List<LottoNumber> numbers) {
		return new Lotto(new TreeSet<>(numbers));
	}

	public static Lotto of(String[] lottoNumbers) {
		return Lotto.from(Arrays.stream(lottoNumbers)
			.mapToInt(number -> Integer.parseInt(number.trim()))
			.mapToObj(LottoNumber::of)
			.collect(Collectors.toList()));
	}

	private void checkSize(Set<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != SIZE) {
			throw new IllegalArgumentException("로또 길이는 6이어야 합니다.");
		}
	}

	public int calculateMatchCount(Lotto targetLotto) {
		return (int)lotto.stream()
			.filter(targetLotto::contains)
			.count();
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lotto.contains(lottoNumber);
	}

	public TreeSet<LottoNumber> getLotto() {
		return new TreeSet<>(lotto);
	}
}
