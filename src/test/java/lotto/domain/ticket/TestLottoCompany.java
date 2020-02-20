package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import lotto.view.dto.BettingMoneyRequestDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestLottoCompany extends LottoCompany {
    @Override
    public List<LottoTicket> buyTicket(BettingMoneyRequestDTO bettingMoney) {
        LottoTicket testTicket = new LottoTicket(getTestBalls());
        return new ArrayList<LottoTicket>(Arrays.asList(testTicket));
    }

    private Set<LottoBall> getTestBalls() {
        int[] fixedNumber = new int[]{1, 2, 3, 4, 5, 6};
        return Arrays.stream(fixedNumber)
                .mapToObj(LottoFactory::findLottoBallByNumber)
                .collect(Collectors.toSet());
    }
}
