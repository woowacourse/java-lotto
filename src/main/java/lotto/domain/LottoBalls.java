package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoBalls {
    private String lottoTicketNumbers;
    private String winningBallNumbers;

    public LottoBalls(String lottoTicketNumbers, String winningBallNumbers) {
        this.lottoTicketNumbers = lottoTicketNumbers;
        this.winningBallNumbers = winningBallNumbers;
    }

    public Set<LottoBall> generateLottoTicket(){
        return Arrays.stream(lottoTicketNumbers.split(","))
                .map(lottoTicketNumber -> LottoBallFactory.findByLottoBall(Integer.parseInt(lottoTicketNumber)))
                .collect(Collectors.toSet());
    }

    public List<LottoBall> generateWinningBalls(){
        return Arrays.stream(winningBallNumbers.split(","))
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toList());
    }

}
