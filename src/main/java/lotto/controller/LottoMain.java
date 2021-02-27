package lotto.controller;

import lotto.domain.ticket.*;
import lotto.domain.ticketgenerator.LottoGenerator;
import lotto.domain.ticketpurchase.PurchasedTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoComparator;
import lotto.domain.ticketresult.WinningLottoNumbers;
import lotto.domain.strategy.AutoStrategy;
import lotto.domain.strategy.ManualStrategy;
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
        List<LottoTicket> manualLottoTicketsInput = getManualLottoTicketInput(userPurchase);

        ManualTickets manualTickets = (ManualTickets) generateManualTicket(lottoGenerator, manualLottoTicketsInput);
        AutoTickets autoTickets = (AutoTickets) generateAutoTicket(lottoGenerator, userPurchase);
        PurchasedTickets purchasedTickets = new PurchasedTickets(manualTickets, autoTickets);
        OutputView.printPurchasedLottoTickets(purchasedTickets);

        WinningLottoNumbers winningLottoNumbers = getWinningLottoNumbersInput();
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        OutputView.printResult(lottoComparator.getLottoResult(purchasedTickets), userPurchase);
    }

    private static UserPurchase getUserPurchaseInput() {
        try {
            int purchasePrice = InputView.getPurchasePrice();
            int manualTicketCount = getManualTicketCountInput();
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
            addLottoTicketInput(userPurchase, manualLottoTickets);
            return manualLottoTickets;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getManualLottoTicketInput(userPurchase);
        }
    }

    private static void addLottoTicketInput(UserPurchase userPurchase, List<LottoTicket> manualLottoTickets) {
        for (int i = 0; i < userPurchase.getManualTicketCount(); i++) {
            manualLottoTickets.add(getLottoTicketInput());
        }
    }

    private static LottoTickets generateManualTicket(LottoGenerator lottoGenerator, List<LottoTicket> manualLottoTickets) {
        lottoGenerator.setGenerateStrategy(new ManualStrategy(manualLottoTickets));
        return lottoGenerator.generateTickets();
    }

    private static LottoTickets generateAutoTicket(LottoGenerator lottoGenerator, UserPurchase userPurchase) {
        lottoGenerator.setGenerateStrategy(new AutoStrategy(userPurchase.getAutoTicketCount()));
        return lottoGenerator.generateTickets();
    }

    private static int getManualTicketCountInput() {
        try {
            return InputView.getManualTicketCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getManualTicketCountInput();
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
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
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
