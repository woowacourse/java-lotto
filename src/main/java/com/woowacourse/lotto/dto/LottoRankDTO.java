package com.woowacourse.lotto.dto;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import com.woowacourse.lotto.domain.LottoRank;

import static com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text.NEW_LINE;

public class LottoRankDTO {
	public static final String PRINT_SECOND_OF_LOTTO = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
	public static final String PRINT_RESULT_OF_LOTTO = "%d개 일치 (%d원)- %d개\n";

	private Map<LottoRank, Integer> lottoRanks;

	public LottoRankDTO(Map<LottoRank, Integer> lottoRanks) {
		this.lottoRanks = new TreeMap<>(lottoRanks);
	}

	public Map<LottoRank, Integer> getLottoRanks() {
		return lottoRanks;
	}

	public void setLottoRanks(Map<LottoRank, Integer> lottoRanks) {
		this.lottoRanks = lottoRanks;
	}

	public String printLottoRank() {
		StringBuilder stringBuilder = new StringBuilder();
		for(LottoRank lottoRank : lottoRanks.keySet()) {
			printRankResult(lottoRanks, stringBuilder, lottoRank);
		}
		return stringBuilder.toString();
	}

	private void printRankResult(Map<LottoRank, Integer> ranks, StringBuilder stringBuilder, LottoRank lottoRank) {
		if (lottoRank == LottoRank.SECOND) {
			stringBuilder.append(String.format(PRINT_SECOND_OF_LOTTO + NEW_LINE, lottoRank.getCount(), lottoRank.getPrice(), ranks.get(lottoRank)));
			return;
		}
		stringBuilder.append(String.format(PRINT_RESULT_OF_LOTTO + NEW_LINE, lottoRank.getCount(), lottoRank.getPrice(), ranks.get(lottoRank)));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof LottoRankDTO)) {
			return false;
		}
		final LottoRankDTO that = (LottoRankDTO) o;
		return Objects.equals(getLottoRanks(), that.getLottoRanks());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLottoRanks());
	}
}
