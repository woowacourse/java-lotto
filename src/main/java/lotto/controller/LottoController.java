package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoTicketService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private static final String DELIMITER = ",";

    public void lottoStart() {
        OutputView.askHowMuchToBuy();
        Money money = new Money(Integer.parseInt(InputView.getUserInput()));
        OutputView.howMuchBought(money.lottoCount());

        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        for (int i = 0; i < money.lottoCount(); i++) {
            lottoTicketGroup.add(LottoTicketService.createLottoTicket());
        }
        LottoTickets lottoTickets = new LottoTickets(lottoTicketGroup);
        OutputView.printTickets(lottoTickets);
        OutputView.askWinnerLottoTicket();

        List<LottoNumber> lottoWinnerNumbers =
                Arrays.stream(InputView.getUserInput().split(DELIMITER))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList());

        LottoWinnerTicket lottoWinnerTicket = new LottoWinnerTicket(lottoWinnerNumbers);

        OutputView.askWinnerBonusNumber();
        LottoWinnerBonusNumber lottoWinnerBonusNumber =
                new LottoWinnerBonusNumber(Integer.parseInt(InputView.getUserInput()));
        LottoWinner lottoWinner = new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber);

        OutputView.printRewardResultBoard();
        LottoResultStatistics lottoResultStatistics =
                LottoResultStatistics.getResultStatistics(lottoTickets, lottoWinner);
        OutputView.printStatistics(lottoResultStatistics);

        OutputView.printFinalResult(30);
    }
}