package View;

import domain.*;

public class OutputViewer {
    private OutputViewer() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printLottoTicketsCount(Money money) {
        StringBuilder sb = new StringBuilder();
        sb.append(money.toString());
        sb.append("개를 구매했습니다.");
        System.out.println(sb.toString());
    }

    public static void printLottoTickets(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            StringBuilder sb = new StringBuilder();
            String ticketNumbers = String.join(", ", lottoTicket.lottoTicketNumberToString());
            sb.append("[");
            sb.append(ticketNumbers);
            sb.append("]");
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    private static void printWinningResult(WinningCalculator winningCalculator) {
        System.out.println();
        System.out.println("당첨 통계\n---------");
        for (PrizeType prizeType : PrizeType.values()) {
            String winningResult = prizeType.getResultString(winningCalculator.getPrizeTypeValue(prizeType));
            System.out.println(winningResult);
        }
    }

    private static void printProfitPercent(Money money, WinningCalculator winningCalculator) {
        String profitPercent = Integer.toString(ProfitCalculator.calculateProfit(money, winningCalculator));
        StringBuilder sb = new StringBuilder();
        sb.append("총 수익률은 ");
        sb.append(profitPercent);
        sb.append("%입니다.");
        System.out.println(sb.toString());
    }

    public static void printResult(Money money, WinningCalculator winningCalculator) {
        printWinningResult(winningCalculator);
        printProfitPercent(money, winningCalculator);
    }
}
