package lotto.controller;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.service.LottoTicketService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void lottoStart(){
        OutputView.askHowMuchToBuy();
        Money money = new Money(Integer.parseInt(InputView.getUserInput()));
        OutputView.howMuchBought(money.lottoCount());

        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        for (int i = 0; i < money.lottoCount(); i++) {
            lottoTicketGroup.add(LottoTicketService.createLottoTicket());
        }
        LottoTickets lottoTickets = new LottoTickets(lottoTicketGroup);
        OutputView.printTickets(lottoTickets);
    }
}

//        지난 주 당첨 번호를 입력해 주세요.
//        1, 2, 3, 4, 5, 6
//        보너스 볼을 입력해 주세요.
//        7
//
//        당첨 통계
//        ---------
//        3개 일치 (5000원)- 1개
//        4개 일치 (50000원)- 0개
//        5개 일치 (1500000원)- 0개
//        5개 일치, 보너스 볼 일치(30000000원) - 0개
//        6개 일치 (2000000000원)- 0개
//        총 수익률은 30%입니다.
//        ```