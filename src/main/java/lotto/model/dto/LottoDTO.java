package lotto.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.model.Lottos;
import lotto.model.number.LottoBall;

public class LottoDTO {

	private final List<LottoBall> lottoBalls;

	private LottoDTO(List<LottoBall> lottoBalls) {
		this.lottoBalls = lottoBalls;
	}

	public static List<LottoDTO> from(Lottos lottos) {
		return lottos.getLottos().stream()
				.map(lotto -> new LottoDTO(lotto.getLotto()))
				.collect(Collectors.toList());
	}

	public List<LottoBall> getLottoBalls() {
		return lottoBalls;
	}
}
