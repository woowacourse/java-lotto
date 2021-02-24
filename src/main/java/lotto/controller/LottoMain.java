package lotto.controller;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketgenerator.LottoGenerator;
import lotto.domain.LottoTickets;
import lotto.domain.ticketpurchase.PurchasedTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoComparator;
import lotto.domain.ticketresult.WinningLottoNumbers;
import lotto.strategy.AutoStrategy;
import lotto.strategy.ManualStrategy;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.printer.InputPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMain {
    public static void main(String[] args) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        UserPurchase userPurchase = getUserPurchaseInput();
        List<LottoTicket> manualLottoTickets = getManualLottoTicketInput(userPurchase);

        LottoTickets manualTickets = generateManualTicket(lottoGenerator, manualLottoTickets);
        LottoTickets autoTickets = generateAutoTicket(lottoGenerator, userPurchase);
        PurchasedTickets purchasedTickets = new PurchasedTickets(manualTickets, autoTickets);

        OutputView.printPurchasedLottoTickets(purchasedTickets);

        WinningLottoNumbers winningLottoNumbers = getWinningLottoNumbersInput();
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        OutputView.printResult(lottoComparator.getLottoResult(purchasedTickets), userPurchase);
    }

    private static LottoTickets generateManualTicket(LottoGenerator lottoGenerator, List<LottoTicket> manualLottoTickets) {
        lottoGenerator.setGenerateStrategy(new ManualStrategy(manualLottoTickets));
        return lottoGenerator.generateTickets();
    }

    private static LottoTickets generateAutoTicket(LottoGenerator lottoGenerator, UserPurchase userPurchase) {
        lottoGenerator.setGenerateStrategy(new AutoStrategy(userPurchase.getAutoTicketCount()));
        return lottoGenerator.generateTickets();
    }

    private static UserPurchase getUserPurchaseInput() {
        try {
            int purchasePrice = InputView.getPurchasePrice();
            int manualTicketCount = InputView.getManualTicketCount();
            return new UserPurchase(purchasePrice, manualTicketCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getUserPurchaseInput();
        }
    }

    private static List<LottoTicket> getManualLottoTicketInput(UserPurchase userPurchase) {
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        InputPrinter.printManualTicketInputGuideMessage();
        try {
            //TODO 메소드 분리
            for (int i = 0; i < userPurchase.getManualTicketCount(); i++) {
                manualLottoTickets.add(getLottoTicketInput());
            }
            return manualLottoTickets;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getManualLottoTicketInput(userPurchase);
        }
    }

    private static WinningLottoNumbers getWinningLottoNumbersInput() {
        LottoTicket winningLottoTicket = getWinningLottoTicketInput();
        LottoNumber bonusNumber = getBonusNumberInput();
        return new WinningLottoNumbers(winningLottoTicket, bonusNumber);
    }

    private static LottoTicket getWinningLottoTicketInput() {
        try {
            InputPrinter.printWinnerLottoNumbersInputGuideMessage();
            return getLottoTicketInput();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getWinningLottoTicketInput();
        }
    }

    private static LottoTicket getLottoTicketInput() {
        List<Integer> numbers = InputView.getLottoNumbers();
        List<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::new).collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
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
