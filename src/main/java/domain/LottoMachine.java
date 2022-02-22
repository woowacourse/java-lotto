package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
	private static final List<LottoNumber> lottoBucket = new ArrayList<>();

	static {
		IntStream.rangeClosed(1, 45)
			.mapToObj(LottoNumber::new)
			.forEach(lottoBucket::add);
	}

	public LottoMachine() {

	}

	public Lottos createLottos(int lottoCount) {
		List<Lotto> lottos = new ArrayList<>();
		for (int count = 0; count < lottoCount; count++) {
			lottos.add(createAutoLotto());
		}
		return new Lottos(lottos);
	}

	private Lotto createAutoLotto() {
		Collections.shuffle(lottoBucket);
		return new Lotto(lottoBucket.stream()
			.limit(6)
			.collect(Collectors.toList()));
	}

	public void print() {
		System.out.println(createLottos(2));
	}
}
