package controller.dto;

import java.util.List;

import domain.Lotto;

public class BuyingInfoDto {
	private final int autoLottoCount;
	private final int manualLottoCount;
	private final List<Lotto> totalLottos;

	public BuyingInfoDto(int autoLottoCount, int manualLottoCount, List<Lotto> totalLottos) {
		this.autoLottoCount = autoLottoCount;
		this.manualLottoCount = manualLottoCount;
		this.totalLottos = totalLottos;
	}

	public int getAutoLottoCount() {
		return autoLottoCount;
	}

	public int getManualLottoCount() {
		return manualLottoCount;
	}

	public List<Lotto> getTotalLottos() {
		return totalLottos;
	}
}
