package lotto.domain;

import lotto.util.ValidateTicketUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final String DELIMITER = ",";
    private static final int LOTTO_TICKET_SIZE = 6;
    private static final String LOTTO_TICKET_EMPTY_EXCEPTION = "로또 티켓에 로또볼이 비었습니다. 다시 드리겠습니다.";
    private static final String LOTTO_TICKET_OUT_OF_RANGE = "로또 티켓이 7장이상 발급되었습니다.";

    private final List<LottoBall> lottoTicket;

    public LottoTicket(List<LottoBall> lottoTicket) {
        if (lottoTicket.isEmpty()) {
            throw new NullPointerException(LOTTO_TICKET_EMPTY_EXCEPTION);
        }
        if (lottoTicket.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(LOTTO_TICKET_OUT_OF_RANGE);
        }
        this.lottoTicket = lottoTicket;
    }

    public LottoTicket(String inputValue){
        String[] winningBalls = inputValue.split(DELIMITER);

        ValidateTicketUtils.validateWinningBallsNumber(winningBalls);
        ValidateTicketUtils.validateWinningBallsLength(winningBalls);
        ValidateTicketUtils.validateDuplicatedWinningBalls(winningBalls);

        this.lottoTicket = generateCustomLottoTicket(winningBalls);
        Collections.sort(this.lottoTicket);
    }

    private List<LottoBall> generateCustomLottoTicket(String[] winningBalls) {
        return Arrays.stream(winningBalls)
                .map(Integer::parseInt)
                .map(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toList());
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }
}
