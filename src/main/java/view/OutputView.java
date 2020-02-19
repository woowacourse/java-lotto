package view;

import domain.Money;
import domain.numberscontainer.Ticket;

import java.util.List;

public class OutputView {

    public static void printNumberOfTickets(Money money) {
        System.out.println(money.getNumberOfTickets() + "개를 구매했습니다.");
    }

    public static void printTickets(List<Ticket> tickets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Ticket ticket : tickets) {
            stringBuilder.append(ticket.toString());
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}