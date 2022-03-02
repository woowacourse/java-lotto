package lotto;

import lotto.view.LottoView;

public class MainApplication {

    public static void main(final String[] args) {
        final LottoView lottoView = LottoView.newInstance();
        final LottoApplication lottoApplication = new LottoApplication(lottoView);
        lottoApplication.run();
    }

}
