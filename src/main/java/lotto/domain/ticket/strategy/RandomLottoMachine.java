package lotto.domain.ticket.strategy;

import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine implements LottoMachine {

    private static final int START_LOTTO_BALL = 1;
    private static final int END_LOTTO_BALL = 6;

    public List<LottoTicket> buyTicket(BettingMoney bettingMoney) {
        int ticketCount = bettingMoney.getTicketCount();
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int count = 0; count < ticketCount; count++) {
            LottoTicket lottoTicket = makeTickets();
            lottoTickets.add(lottoTicket);
        }
        return lottoTickets;
    }

    private LottoTicket makeTickets() {
        List<LottoBall> lottoBalls = LottoBallFactory.getInstance();
        Collections.shuffle(lottoBalls);

        return IntStream.rangeClosed(START_LOTTO_BALL, END_LOTTO_BALL)
                .mapToObj(lottoBalls::get)
                .collect(Collectors.collectingAndThen(Collectors.toSet(), LottoTicket::new));
    }
}
