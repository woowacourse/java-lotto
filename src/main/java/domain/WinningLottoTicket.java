package domain;

import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WinningLottoTicket {
    private static final String DELIMITER = ", ";

    private LottoTicket winningTicket;
    private BonusBall bonusBall;

    public WinningLottoTicket(String inputWinningTicket, String inputBonusBall) {
        validateBlank(inputWinningTicket);
        validateBlank(inputBonusBall);
        List<Integer> winningTicket = new ArrayList<>();

        addTicketNumber(winningTicket, inputWinningTicket);
        this.winningTicket = new LottoTicket(winningTicket);
        this.bonusBall = new BonusBall(this.winningTicket.getLottoTicket(), inputBonusBall);
    }

    public LottoTicket getWinningTicket() {
        return winningTicket;
    }

    public boolean isMatchBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket()
                .contains(bonusBall.getBonusNumber());
    }

    private void addTicketNumber(List<Integer> winningTicket, String input) {
        for (String number : splitInputNumber(input)) {
            validateBlank(number);
            validateNumber(number);
            winningTicket.add(Integer.parseInt(number));
        }
    }

    private String[] splitInputNumber(String input) {
        return input.split(DELIMITER);
    }

    private void validateBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input값이 공백 또는 null입니다.");
        }
    }

    private void validateNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("input값이 숫자가 아닙니다.");
        }
    }
}
