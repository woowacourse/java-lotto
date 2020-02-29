package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoStore {
	private static Lotto createRandomLotto() {
		List<LottoNumber> shuffledLottoNumbers = LottoNumber.values()
				.stream()
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					Collections.shuffle(list);
					return list;
				}));
		return new Lotto(shuffledLottoNumbers.subList(0, Lotto.SIZE));
	}

	public static WinningLotto createWinningLotto(String lottoInput, String bonusInput) {
		Lotto lotto = LottoFactory.createLotto(lottoInput);
		LottoNumber bonus = LottoNumber.of(bonusInput);
		return new WinningLotto(lotto, bonus);
	}

	public static Lottos buyManualAndAuto(LottoBuyCount lottoBuyCount, List<String> manual) {
		return Stream.concat(
				buyManual(lottoBuyCount.getManual(), manual).stream(),
				buyAuto(lottoBuyCount.getAuto()).stream()
		).collect(Collectors.collectingAndThen(Collectors.toList(), Lottos::new));
	}

	private static List<Lotto> buyManual(int buyCount, List<String> manual) {
		return buy(buyCount, () -> LottoFactory.createLotto(manual.remove(0)));
	}

	private static List<Lotto> buyAuto(int buyCount) {
		return buy(buyCount, LottoStore::createRandomLotto);
	}

	private static List<Lotto> buy(int buyCount, Supplier<Lotto> creator) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < buyCount; ++i) {
			lottos.add(creator.get());
		}
		return lottos;
	}
}
