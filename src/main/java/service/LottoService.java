package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.dto.BuyingInfoDto;
import controller.dto.LottoResultDto;
import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.OrderForm;

import domain.Payment;
import domain.Rank;
import domain.WinningLotto;
import domain.generator.AutoLottoGenerator;
import domain.generator.ManualLottoGenerator;

public class LottoService {

	public BuyingInfoDto buy(String paymentValue, int countValue, List<String[]> manualLottosValue) {
		Payment payment = new Payment(paymentValue);
		OrderForm orderForm = new OrderForm(payment, countValue);
		List<Lotto> totalLottos = createLottos(manualLottosValue, orderForm.calculateAutoLottoCount());

		return new BuyingInfoDto(orderForm, totalLottos);
	}

	private List<Lotto> createLottos(List<String[]> manualnumbers, int count) {
		List<Lotto> totalLottos = new ArrayList<>(createManualLottos(manualnumbers));
		totalLottos.addAll(createAutoLottos(count));
		return new ArrayList<>(totalLottos);
	}

	private List<Lotto> createManualLottos(List<String[]> manualnumbers) {
		return new ManualLottoGenerator(manualnumbers).creatLottos();
	}

	private List<Lotto> createAutoLottos(int count) {
		return new AutoLottoGenerator(count).creatLottos();
	}

	public LottoResultDto createLottoResult(Lottos lottos, WinningLotto winningLotto) {
		LottoResult lottoResult = LottoResult.from(lottos.countRank(winningLotto));
		return new LottoResultDto(lottoResult.getRanks());
	}

	public double createProfitRate(Map<Rank, Long> ranks, String paymentValue) {
		Payment payment = new Payment(paymentValue);
		LottoResult lottoResult = new LottoResult(ranks);
		return lottoResult.calculateProfitRate(payment);
	}
}

