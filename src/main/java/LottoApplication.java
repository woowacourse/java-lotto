import lotto.controller.LottoController;

public class LottoApplication {
    public static void main(final String... args) {
        LottoController controller = new LottoController();
        controller.buyTicket();
        controller.showLottoTickets();
        controller.showWinningStats();
        controller.clearRepository();
    }
}
