package lotto.controller;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketgenerator.LottoGenerator;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoComparator;
import lotto.domain.ticketresult.WinningLottoNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMain {
    public static void main(String[] args) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        UserPurchase userPurchase = getUserPurchaseInput();
        PurchasedLottoTickets purchasedLottoTickets = lottoGenerator.generatePurchasedTickets(userPurchase);
        OutputView.printPurchasedLottoTickets(purchasedLottoTickets);

        LottoTicket winningLottoTicket = getWinningLottoTicketInput();
        LottoNumber bonusNumber = getBonusNumberInput();
        WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers(winningLottoTicket, bonusNumber);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        OutputView.printResult(lottoComparator.getLottoResult(purchasedLottoTickets), userPurchase.getPurchasePrice());
    }

    private static UserPurchase getUserPurchaseInput() {
        try {
            return new UserPurchase(InputView.getUserPurchase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getUserPurchaseInput();
        }
    }

    private static LottoTicket getWinningLottoTicketInput() {
        try {
            List<Integer> numbers = InputView.getWinningLottoNumbers();
            List<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::new).collect(Collectors.toList());
            return new LottoTicket(lottoNumbers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getWinningLottoTicketInput();
        }
    }

    private static LottoNumber getBonusNumberInput() {
        try {
            return new LottoNumber(InputView.getBonusNumberInput());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getBonusNumberInput();
        }
    }
}
