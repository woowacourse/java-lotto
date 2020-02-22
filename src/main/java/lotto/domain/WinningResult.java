package lotto.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class WinningResult {
	private static final String NULL_RESULT_EXCEPTION_MESSAGE = "결과 데이터가 null 이라네";
	private static final String INVALID_RESULT_SIZE_EXCEPTION_MESSAGE = "일부 추첨 결과 정보가 누락되어있습니다.";

	private final Map<LottoRank, Long> winningResult;

	public WinningResult(Map<LottoRank, Long> winningResult) {
		validate(winningResult);
		this.winningResult = Collections.unmodifiableMap(new LinkedHashMap<>(winningResult));
	}

	private void validate(Map<LottoRank, Long> winningResult) {
		validateNull(winningResult);
		validateResultSize(winningResult);
	}

	private void validateResultSize(Map<LottoRank, Long> winningResult) {
		Set<LottoRank> lottoRanks = winningResult.keySet();
		if (LottoRank.isNotRightResultSize(lottoRanks.size())) {
			throw new IllegalArgumentException(INVALID_RESULT_SIZE_EXCEPTION_MESSAGE);
		}
	}

	private void validateNull(Map<LottoRank, Long> winningResult) {
		if (Objects.isNull(winningResult)) {
			throw new NullPointerException(NULL_RESULT_EXCEPTION_MESSAGE);
		}
	}

	Money calculateTotalMoney() {
		return winningResult.keySet().stream()
			.map(rank -> rank.calculateTotalMoney(winningResult.get(rank)))
			.reduce(Money.valueOf(0), Money::plus);
	}

	public long getProfitRate(Money money) {
		Money totalMoney = calculateTotalMoney();
		return totalMoney.calculateProfitRate(money);
	}

	public Map<LottoRank, Long> getWinningResult() {
		return winningResult;
	}
}
