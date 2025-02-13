package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoTicket;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    private static final int LOTTO_PRICE = 1000;

    public LottoTicket issueTicket(long purchaseAmount) {
        int count = countNumberOfPurchases(purchaseAmount);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> lottoNumbers = LottoNumberGenerator.generate();
            OutputView.printLottos(lottoNumbers);
            lottos.add(new Lotto(lottoNumbers));
        }
        return new LottoTicket(lottos);
    }

    public int countNumberOfPurchases(long purchaseAmount) {
        return (int) purchaseAmount / LOTTO_PRICE;
    }
}
