package lotto.controller;

import lotto.domain.LottoCompany;
import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.WinningLottoRequestDTO;

import java.util.List;

public class LottoController {
    public void run() {
        BettingMoneyRequestDTO bettingMoney = new BettingMoneyRequestDTO(InputView.inputBettingMoney());
        List<LottoTicket> lottoTickets = LottoCompany.buyTicket(bettingMoney);

        String winningNumber = InputView.inputWinningNumber();
        int bonusNumber = InputView.inputBonusNumber();
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);
        WinningLotto winningLotto = LottoCompany.makeWinningLotto(winningLottoRequestDTO);


    }
}
