import controller.LottoController;
import domain.*;
import view.InputViewer;
import view.OutputViewer;

public class LottoApplication {
    public static void main(String[] args) {
        Money money = LottoController.getMoney(InputViewer.inputMoney());
        int allLottoTicketCount = money.getLottoTicketCount();

        ManualLottoTicketCount manualLottoTicketCount = LottoController.getManualLottoTicketCount(allLottoTicketCount,
                InputViewer.inputManualLottoCount());
        int manualLottoTicketCounts = manualLottoTicketCount.getManualLottoTicketCount();
        ManualLottoTickets manualLottoTickets = LottoController.getManualLottoTickets(manualLottoTicketCount);

        int autoLottoTicketCount = allLottoTicketCount - manualLottoTicketCounts;
        AutoLottoTickets autoLottoTickets = new AutoLottoTickets(
                AutoLottoTicketsGenerator.generateAutoLottoTickets(autoLottoTicketCount));

        LottoTickets lottoTickets = new LottoTickets(
                LottoController.concatManualTicketsWithAutoTickets(manualLottoTickets, autoLottoTickets));

        OutputViewer.printLottoTicketsCount(manualLottoTicketCounts, autoLottoTicketCount);
        OutputViewer.printLottoTickets(lottoTickets);

        WinningLottoTicket winningLottoTicket = LottoController.getWinningLottoTicket(
                InputViewer.inputWinningLottoTicketNumber());
        LottoController.getBonusBall(winningLottoTicket, InputViewer.inputBonusBallNumber());

        LottoResult lottoResult = LottoController.calculateLottoResult(lottoTickets, winningLottoTicket);
        LottoController.calculateProfit(money, lottoResult);
        OutputViewer.printResult(lottoResult);
    }
}
