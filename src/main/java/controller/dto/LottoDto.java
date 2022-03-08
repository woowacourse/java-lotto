package controller.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;

import domain.Lotto;
import domain.LottoNumber;

public class LottoDto {
	private final List<Integer> lotto;

	public LottoDto(Lotto lotto) {
		this.lotto = lotto.getLotto()
			.stream()
			.map(LottoNumber::getLottoNumber)
			.collect(toList());
	}

	public List<Integer> getLotto() {
		return lotto;
	}
}
