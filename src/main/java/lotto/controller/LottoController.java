package lotto.controller;

import lotto.Application;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoController {
    public static void play() {
        PurchaseAmount purchaseAmount;
        LottoTickets lottoTickets;

        try {
            OutputView.printStartGuide();
            purchaseAmount = new PurchaseAmount(InputView.InputPurchaseAmount());
            OutputView.printLottePieces(purchaseAmount.lottoTicket());
            OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
            for (int i = 0; i < purchaseAmount.lottoTicket(); i++) {
                Collections.shuffle(LottoBallFactory.getInstance());
                lottoTickets = new LottoTickets(generateLottoTicket());
            }
            OutputView.printLottoTicket();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            Application.main(null);
        }

    }

    public static List<LottoBall> generateLottoTicket() {
        List<LottoBall> lottoTicket = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return lottoTicket;
    }
}
