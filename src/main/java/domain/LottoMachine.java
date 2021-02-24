package domain;

import domain.ball.LottoBall;
import domain.ball.LottoBallFactory;
import domain.ball.LottoBalls;
import domain.lotto.LottoTicket;
import util.OutputUtil;
import view.InputView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.ball.LottoBalls.LOTTO_BALL_SIZE;

public class LottoMachine {
    public List<LottoTicket> makeRandomTickets(int ticketCount) {
        return IntStream.range(0, ticketCount)
                .mapToObj(count -> makeRandomTicket())
                .collect(Collectors.toList());
    }

    private LottoTicket makeRandomTicket() {
        List<LottoBall> lottoBalls = getRandomLottoBalls();
        return new LottoTicket(new LottoBalls(lottoBalls));
    }

    public List<LottoTicket> makeManualTickets(InputView inputView, int ticketCount) {
        OutputUtil.printMessage("수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, ticketCount)
                .mapToObj(count -> makeManualTicket(inputView))
                .collect(Collectors.toList());
    }

    private LottoTicket makeManualTicket(InputView inputView) {
        List<Integer> manualTicketNumbers = inputView.inputManualTicketNumber();
        List<LottoBall> lottoBalls = manualTicketNumbers.stream()
                .map(LottoBall::new)
                .collect(Collectors.toList());
        return new LottoTicket(new LottoBalls(lottoBalls));
    }

    private List<LottoBall> getRandomLottoBalls() {
        List<LottoBall> lottoBalls = LottoBallFactory.getLottoBalls();
        Collections.shuffle(lottoBalls);
        return lottoBalls.stream()
                .limit(LOTTO_BALL_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }
}
