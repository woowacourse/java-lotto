package lotto.domain;

import lotto.view.OutputView;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class LottoStore {
    private static final int MAX_LOTTO_BALL_COUNT = 6;

    private PurchaseAmount purchaseAmount;
    private LottoTickets lottoTickets;

    public LottoStore(PurchaseAmount purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        generateLottoTickets();
    }

    private LottoTicket generateLottoTicket() {
        Set<LottoBall> lottoTicket = new TreeSet<>();
        for (int i = 0; i < MAX_LOTTO_BALL_COUNT; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return new LottoTicket(lottoTicket);
    }

    private void generateLottoTickets() {
        lottoTickets = new LottoTickets();
        for (int i = 0; i < purchaseAmount.giveLottoTicketNumber(); i++) {
            Collections.shuffle(LottoBallFactory.getInstance());
            lottoTickets.addLottoTicket(generateLottoTicket());
        }
        OutputView.printLottoTicket(lottoTickets);
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }
}