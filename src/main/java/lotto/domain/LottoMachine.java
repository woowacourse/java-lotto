package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
	private final List<LottoNumber> lottoBalls;

	private LottoMachine() {
		List<LottoNumber> initialSetting = new ArrayList<>();

		for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
			initialSetting.add(new LottoNumber(i));
		}
		this.lottoBalls = initialSetting;
	}

	public static LottoMachine getInstance() {
		return LottoMachineSingletonHolder.instance;
	}

	public List<Lotto> makeLottos(LottoCount lottoCount, List<LottoNumbers> manualLottoNumbers) {
		List<Lotto> lottos = makeManualLottos(manualLottoNumbers);
		lottos.addAll(makeAutoLottos(lottoCount.getAutoLottoCount()));
		return lottos;
	}

	private List<Lotto> makeManualLottos(List<LottoNumbers> manualLottoNumbers) {
		return manualLottoNumbers.stream()
			.map(Lotto::new)
			.collect(Collectors.toList());
	}

	private List<Lotto> makeAutoLottos(int lottoCount) {
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < lottoCount; i++) {
			lottos.add(new Lotto(new LottoNumbers(pickRandomNumbers())));
		}
		return lottos;
	}

	private List<LottoNumber> pickRandomNumbers() {
		Collections.shuffle(lottoBalls);
		return lottoBalls.stream()
			.limit(LottoNumbers.SIZE)
			.sorted()
			.collect(Collectors.toList());
	}

	private static class LottoMachineSingletonHolder {
		private static final LottoMachine instance = new LottoMachine();
	}
}
