package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResultFactory {
	public static final int DEFAULT = 0;

	public static LottoResult create(PurchasedLottoTickets purchasedLottoTickets,
									 WinningLottoNumbers winningLottoNumbers) {
		Map<WinningType, Integer> lottoResult = init();
		List<WinningType> winningTypes =
				purchasedLottoTickets.findMatchingWinningTypesWith(winningLottoNumbers);

		lottoResult.replaceAll((k, v) -> Collections.frequency(winningTypes, k));

		return new LottoResult(lottoResult);
	}

	private static Map<WinningType, Integer> init() {
		Map<WinningType, Integer> lottoResult = new HashMap<>();
		for (WinningType winningType : WinningType.values()) {
			lottoResult.put(winningType, DEFAULT);
		}
		return lottoResult;
	}
}
