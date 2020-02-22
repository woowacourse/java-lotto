package lotto.domain;

import lotto.util.ValidateWinningTicketUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WinningTicketFactory {
    private static final String DELIMITER = ",";

    private final List<LottoBall> winningTicket;

    public WinningTicketFactory(String inputValue) {
        String[] winningBalls = inputValue.split(DELIMITER);

        ValidateWinningTicketUtils.validateWinningBallsNumber(winningBalls);
        ValidateWinningTicketUtils.validateWinningBallsLength(winningBalls);
        ValidateWinningTicketUtils.validateDuplicatedWinningBalls(winningBalls);

        this.winningTicket = generateWinningTicket(winningBalls);
        Collections.sort(this.winningTicket);
    }

    private List<LottoBall> generateWinningTicket(String[] winningBalls) {
        return Arrays.stream(winningBalls)
                .map(Integer::parseInt)
                .map(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toList());
    }

    public List<LottoBall> getWinningTicket() {
        return Collections.unmodifiableList(this.winningTicket);
    }
}
