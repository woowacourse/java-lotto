package view;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.LottoTickets;

import java.util.stream.Collectors;

public class OutputView {
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String COMMA_WITH_BLANK = ", ";

    private static OutputView instance;

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
        }
        return instance;
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printLottoTicket(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.lottoTickets()) {
            String numbers = lottoTicket.numbers().stream()
                    .map(LottoNumber::toString)
                    .collect(Collectors.joining(COMMA_WITH_BLANK));
            System.out.println(LEFT_BRACKET + numbers + RIGHT_BRACKET);
        }
    }

    public void newLine() {
        System.out.println();
    }
}
