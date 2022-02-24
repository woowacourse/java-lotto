package lotto;

import lotto.controller.LottoController;

public class LottoApplication {

	private final LottoController lottoController;

	public LottoApplication(final AppConfig appConfig) {
		this.lottoController = appConfig.lottoController;
	}

	public static void main(final String[] args) {
		final AppConfig appConfig = AppConfig.getInstance();
		final LottoApplication lottoApplication = new LottoApplication(appConfig);
		lottoApplication.run();
	}

	private void run() {
		lottoController.purchaseTickets();
		lottoController.checkOutAnalysis();
	}

}
