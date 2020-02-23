package lotto.domain;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoBalls {
    public static final String DELIMITER = ",";

    public static Set<LottoBall> generateLottoTicket(String lottoTicketNumbers){
        return Arrays.stream(lottoTicketNumbers.split(DELIMITER))
                .map(lottoTicketNumber -> LottoBallFactory.findByLottoBall(Integer.parseInt(lottoTicketNumber)))
                .collect(Collectors.toSet());
    }

    public static Set<LottoBall> generateWinningBalls(String winningBallNumbers){
        return Arrays.stream(winningBallNumbers.split(DELIMITER))
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toSet());
    }

}
