package lotto.controller;

import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.ticketFactory.TicketBox;
import lotto.exception.LottoCustomException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController() {
        inputView = new InputView();
    }

    public void run() {
        Money money = inputMoney();
        LottoTickets lottoTickets = buyTickets(money);

        WinningLotto winningLotto = inputWinningLotto();

        showResult(lottoTickets, winningLotto, money);
    }

    private Money inputMoney() {
        try {
            OutputView.printMoneyMessage();
            return new Money(inputView.inputValue());
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputMoney();
        }
    }

    private LottoTickets buyTickets(Money money) {
        TicketBox ticketBox = new TicketBox();
        LottoTickets lottoTickets = new LottoTickets();
        OutputView.printFixedTicketMessage();
        int fixedTickets = inputView.inputValue();
        OutputView.printInputFixedTicketMessage();
        for(int i = 0; i<fixedTickets; i++){
            lottoTickets.addTicket(inputTicket());
        }
        for(int i=0; i< money.countTickets() - fixedTickets; i++){
            lottoTickets.addTicket(ticketBox.makeRandomTicket());
        }
        OutputView.printAllTickets(fixedTickets, money.countTickets() - fixedTickets, lottoTickets);
        return lottoTickets;
    }

    private WinningLotto inputWinningLotto() {
        OutputView.printWinningNumbers();
        LottoTicket lottoTicket = inputTicket();
        LottoNumber bonus = inputBonus(lottoTicket);
        return new WinningLotto(lottoTicket, bonus);
    }

    private LottoTicket inputTicket() {
        try {
            return new LottoTicket(inputView.inputNumbers());
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputTicket();
        }
    }

    private LottoNumber inputBonus(LottoTicket lottoTicket) {
        try {
            OutputView.printBonusNumber();
            LottoNumber bonusNumber = new LottoNumber(inputView.inputValue());
            validateDuplicate(lottoTicket, bonusNumber);
            return bonusNumber;
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputBonus(lottoTicket);
        }
    }

    private void validateDuplicate(LottoTicket lottoTicket, LottoNumber bonusNumber) {
        if (lottoTicket.hasNumber(bonusNumber)) {
            throw new LottoCustomException("보너스 볼은 지난 주 당첨번호와 중복될 수 없습니다.");
        }
    }

    private void showResult(LottoTickets lottoTickets, WinningLotto winningLotto, Money money) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.checkWinnings(lottoTickets, winningLotto);

        OutputView.printWinningResultTitle();
        OutputView.printProfit(money.calculateProfit(lottoResult.calculateTotalReward()),
            lottoResult.getResults());
    }
}
