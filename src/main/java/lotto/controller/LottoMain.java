package lotto.controller;

import lotto.domain.ticketgenerator.strategy.AutoStrategy;
import lotto.domain.ticketgenerator.strategy.ManualStrategy;
import lotto.domain.ticket.AutoTickets;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ManualTickets;
import lotto.domain.ticketgenerator.LottoGenerator;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.PurchasedTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoComparator;
import lotto.domain.ticketresult.WinningLottoNumbers;
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

        ManualTickets manualTickets = generateManualTicket(lottoGenerator, manualLottoTicketsInput);
        AutoTickets autoTickets = generateAutoTicket(lottoGenerator, userPurchase);
        PurchasedTickets purchasedTickets = new PurchasedTickets(manualTickets, autoTickets);
        OutputView.printPurchasedLottoTickets(purchasedTickets);

        WinningLottoNumbers winningLottoNumbers = getWinningLottoNumbersInput();
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        OutputView.printResult(lottoComparator.getLottoResult(purchasedTickets), userPurchase.getPurchasePrice());
    }

    private static UserPurchase getUserPurchaseInput() {
        try {
            PurchasePrice purchasePrice = new PurchasePrice(InputView.getPurchasePrice());
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

    private static ManualTickets generateManualTicket(LottoGenerator lottoGenerator, List<LottoTicket> manualLottoTickets) {
        lottoGenerator.setGenerateStrategy(new ManualStrategy(manualLottoTickets));
        return (ManualTickets) lottoGenerator.generateTickets();
    }

    private static AutoTickets generateAutoTicket(LottoGenerator lottoGenerator, UserPurchase userPurchase) {
        lottoGenerator.setGenerateStrategy(new AutoStrategy(userPurchase.getAutoTicketCount()));
        return (AutoTickets) lottoGenerator.generateTickets();
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
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }

    private static LottoNumber getBonusNumberInput() {
        try {
            return LottoNumber.of(InputView.getBonusNumberInput());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getBonusNumberInput();
        }
    }
}
