package domain;

import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WinningLottoTicket {
    private static final String DELIMITER = ", ";
    private static final String NUMBER_FORMAT = "-?\\d+(\\.\\d+)?";

    LottoTicket winningTicket;

    public WinningLottoTicket(String input) {
        validateNull(input);
        List<Integer> winningTicket = new ArrayList<>();

        addTicketNumber(winningTicket, input);
        this.winningTicket = new LottoTicket(winningTicket);
    }

    private void addTicketNumber(List<Integer> winningTicket, String input) {
        for (String number : splitInputNumber(input)) {
            validateEmpty(number);
            validateNumber(number);
            winningTicket.add(Integer.parseInt(number));
        }
    }

    private String[] splitInputNumber(String input) {
        return input.split(DELIMITER);
    }

    private void validateNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("input값이 null 입니다.");
        }
    }

    private void validateEmpty(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input값이 공백입니다.");
        }
    }

    private void validateNumber(String input) {
        if (input.matches(NUMBER_FORMAT)) {
            throw new IllegalArgumentException("input값이 숫자가 아닙니다.");
        }
    }
}
