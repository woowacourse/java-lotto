package lotto.domain;

import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoStore {
    private static final int MAX_LOTTO_BALL_COUNT = 6;

    private PurchaseAmount purchaseAmount;
    private LottoTickets lottoTickets;

    public LottoStore(PurchaseAmount purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        generateLottoTickets();
    }

    private LottoTicket generateLottoTicket() {
        List<LottoBall> lottoTicket = new ArrayList<>();
        for (int i = 0; i < MAX_LOTTO_BALL_COUNT; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return new LottoTicket(lottoTicket);
    }

    private void generateLottoTickets() {
        for (int i = 0; i < purchaseAmount.lottoTicket(); i++) {
            Collections.shuffle(LottoBallFactory.getInstance());
            lottoTickets = new LottoTickets(generateLottoTicket());
        }
        OutputView.printLottoTicket(lottoTickets);
    }

    public PurchaseAmount getPurchaseAmount() {
        return purchaseAmount;
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }
}
