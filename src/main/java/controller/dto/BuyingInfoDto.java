package controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoNumber;

public class BuyingInfoDto {
	private final int autoLottoCount;
	private final int manualLottoCount;
	private final List<LottoDto> totalLottos;

	public BuyingInfoDto(int autoLottoCount, int manualLottoCount, List<Lotto> totalLottos) {
		this.autoLottoCount = autoLottoCount;
		this.manualLottoCount = manualLottoCount;
		this.totalLottos = toLottoDto(totalLottos);
	}

	public int getAutoLottoCount() {
		return autoLottoCount;
	}

	public int getManualLottoCount() {
		return manualLottoCount;
	}

	private List<LottoDto> toLottoDto(List<Lotto> lottos) {
		return lottos.stream()
			.map(LottoDto::new)
			.collect(Collectors.toList());
	}

	public List<Lotto> toLotto(){
		return totalLottos.stream()
			.map(LottoDto::getLotto)
			.map(this::toLottoNumber)
			.map(Lotto::from)
			.collect(Collectors.toList());
	}

	private List<LottoNumber> toLottoNumber(List<Integer> lottos) {
		return lottos.stream()
			.map(LottoNumber::of)
			.collect(Collectors.toList());
	}

	public List<LottoDto> getTotalLottos() {
		return totalLottos;
	}
}
