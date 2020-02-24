package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.view.dto.WinLottoTicketDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public abstract class LottoMachine {
    private static final String MESSAGE_FOR_NOT_ENOUGH_MONEY = "%d는 최소 구매 금액보다 작습니다.";

    public final List<LottoTicket> buyTickets(int bettingMoney) {
        validateMoney(bettingMoney);

        int numberOfTickets = calculateAffordableNumber(bettingMoney);

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            lottoTickets.add(createOneTicket());
        }

        return lottoTickets;
    }

    private void validateMoney(int bettingMoney) {
        if (bettingMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_NOT_ENOUGH_MONEY, bettingMoney));
        }
    }

    private int calculateAffordableNumber(int bettingMoney) {
        return bettingMoney / LOTTO_PRICE;
    }

    public abstract LottoTicket createOneTicket();

    public final WinLottoTicket createWinLottoTicket(WinLottoTicketDTO winLottoTicketDTO) {
        Set<LottoBall> winBalls = new HashSet<>();
        for (int number : winLottoTicketDTO.getWinningNumbers()) {
            winBalls.add(LottoBallFactory.getLottoBallByNumber(number));
        }
        return new WinLottoTicket(new LottoTicket(winBalls), LottoBallFactory.getLottoBallByNumber(winLottoTicketDTO.getBonusNumber()));
    }
}
