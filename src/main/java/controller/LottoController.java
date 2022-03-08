package controller;

import java.util.List;
import java.util.Map;

import controller.dto.BuyingInfoDto;
import controller.dto.LottoResultDto;
import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Rank;
import domain.WinningLotto;
import service.LottoService;

public class LottoController {

	private final LottoService lottoService;

	public LottoController(LottoService lottoService) {
		this.lottoService = lottoService;
	}

	public BuyingInfoDto buy(String paymentValue, int countValue, List<String[]> manualLottosValue) {
		return lottoService.buy(paymentValue, countValue, manualLottosValue);
	}

	public LottoResultDto showLottoResult(List<Lotto> totalLotto, String[] lotto, int bonus) {
		WinningLotto winningLotto = lottoService.createWinningLotto(lotto, bonus);
		return lottoService.createLottoResult(lottoService.toLottos(totalLotto), winningLotto);
	}

	public double showProfitRate(Map<Rank, Long> ranks, String payment) {
		return lottoService.createProfitRate(ranks, payment);
	}
}
