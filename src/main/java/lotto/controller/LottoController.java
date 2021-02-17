package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoTicketService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        LottoWinnerBonusNumber lottoWinnerBonusNumber = new LottoWinnerBonusNumber(Integer.parseInt(InputView.getUserInput()));
        LottoWinner lottoWinner = new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber);

        OutputView.printRewardResultBoard();

    }
}

//        3개 일치 (5000원)- 1개
//        4개 일치 (50000원)- 0개
//        5개 일치 (1500000원)- 0개
//        5개 일치, 보너스 볼 일치(30000000원) - 0개
//        6개 일치 (2000000000원)- 0개
//        총 수익률은 30%입니다.
//        ```