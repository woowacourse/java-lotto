package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.util.NullOrEmptyValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.domain.ticket.LottoTicket.LOTTO_BALL_COUNT;

public class LottoMachine {
    private static final List<LottoBall> balls = LottoBallFactory.getInstance();
    private static final String MESSAGE_FOR_UNMATCHED_SIZE = "입력받은 수동 로또 번호 수가 구매하려는 수와 일치하지 않습니다";

    public final List<LottoTicket> buyTickets(int numberOfTickets) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            lottoTickets.add(createOneTicket());
        }

        return lottoTickets;
    }

    public final List<LottoTicket> buyTickets(int numberOfTickets, List<List<Integer>> manualNumbers) {
        NullOrEmptyValidator.isNullOrEmpty(manualNumbers);
        validateSize(numberOfTickets, manualNumbers.size());

        return manualNumbers.stream()
                .map(this::createOneTicket)
                .collect(Collectors.toList());
    }

    private void validateSize(int numberOfTickets, int sizeOfManualNumbers) {
        if (numberOfTickets != sizeOfManualNumbers) {
            throw new IllegalArgumentException(MESSAGE_FOR_UNMATCHED_SIZE);
        }
    }

    public LottoTicket createOneTicket() {
        Collections.shuffle(balls);

        Set<LottoBall> lottoBalls = balls.stream()
                .limit(LOTTO_BALL_COUNT)
                .collect(Collectors.toSet());

        return new LottoTicket(lottoBalls);
    }

    public LottoTicket createOneTicket(List<Integer> manualNumbers) {
        Set<LottoBall> manualBalls = manualNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        return new LottoTicket(manualBalls);
    }

    public final WinLottoTicket createWinLottoTicket(List<Integer> winNumbers, int bonusNumber) {
        NullOrEmptyValidator.isNullOrEmpty(winNumbers);

        Set<LottoBall> winBalls = winNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        LottoBall bonusBall = LottoBallFactory.getLottoBallByNumber(bonusNumber);

        return new WinLottoTicket(new LottoTicket(winBalls), bonusBall);
    }
}
