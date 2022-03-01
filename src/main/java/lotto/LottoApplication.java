package lotto;

import lotto.controller.LottoController;

public class LottoApplication {

    public static void main(final String[] args) {
        final LottoApplication lottoApplication = new LottoApplication();
        lottoApplication.run();
    }

    private void run() {
        final AppConfig appConfig = AppConfig.getInstance();
        final LottoController lottoController = appConfig.lottoController;
        lottoController.run();
    }

}
