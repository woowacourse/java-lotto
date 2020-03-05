package view;

import domain.lottonumbers.LottoTicket;
import domain.result.LottoResult;

import java.util.List;

public class OutputView {

    public static void printNumberOfTickets(int size) {
        System.out.println(size + "개를 구매했습니다.");
    }

    public static void printTickets(List<LottoTicket> lottoTickets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoTicket lottoTicket : lottoTickets) {
            stringBuilder.append(lottoTicket.toString());
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    public static void printLottoResults(LottoResult lottoResult) {
        System.out.println(StringConverter.convertLottoResults(lottoResult));
    }

    public static void printProfit(double profit) {
        System.out.println("총 수익률은 " + (long) profit + "%입니다.");
    }
}