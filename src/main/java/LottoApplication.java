import controller.LottoController;
import domain.*;
import view.InputViewer;
import view.OutputViewer;

public class LottoApplication {
    public static void main(String[] args) {
        Money money = LottoController.getMoney(InputViewer.inputMoney());
        OutputViewer.printLottoTicketsCount(money);

        LottoTickets lottoTickets = new LottoTickets(LottoTicketsGenerator.generateLottoTickets(
                money.getLottoTicketCount()));
        OutputViewer.printLottoTickets(lottoTickets);

        WinningLottoTicket winningLottoTicket = LottoController.getWinningLottoTicket(
                InputViewer.inputWinningLottoTicketNumber());
        LottoController.getBonusBall(winningLottoTicket, InputViewer.inputBonusBallNumber());

        LottoResult lottoResult = LottoController.calculateLottoResult(lottoTickets, winningLottoTicket);
        LottoController.calculateProfit(money, lottoResult);
        OutputViewer.printResult(lottoResult);
    }
}
