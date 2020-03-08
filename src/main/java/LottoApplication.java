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
        int autoLottoTicketCount = allLottoTicketCount - manualLottoTicketCounts;

        LottoTickets lottoTickets = LottoController.getLottoTickets(manualLottoTicketCounts, autoLottoTicketCount);

        OutputViewer.printLottoTicketsCount(manualLottoTicketCounts, autoLottoTicketCount);
        OutputViewer.printLottoTickets(lottoTickets);

        WinningLottoTicket winningLottoTicket = LottoController.getWinningLottoTicket();

        LottoResult lottoResult = new LottoResult(lottoTickets, winningLottoTicket, money);
        OutputViewer.printResult(lottoResult);
    }
}
