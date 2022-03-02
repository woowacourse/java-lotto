package lotto.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.model.Lottos;
import lotto.model.number.LottoBall;

public class LottoDTO {

	private final List<LottoBall> numbers;

	private LottoDTO(List<LottoBall> numbers) {
		this.numbers = numbers;
	}

	public static List<LottoDTO> from(Lottos lottos) {
		return lottos.getLottos().stream()
				.map(lotto -> new LottoDTO(lotto.getAutoLotto()))
				.collect(Collectors.toList());
	}

	public List<LottoBall> getNumbers() {
		return numbers;
	}
}
