package domain;

import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;
import domain.lotto.WinningLotto;
import domain.result.LottoRank;
import domain.result.Result;
import util.InputUtil;
import util.RandomLottoUtil;
import view.LottoGameScreen;
import view.dto.DrawResultDto;
import view.dto.LottoCountResponseDto;
import view.dto.LottoResponseDto;
import view.dto.RevenueDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoGameMachine {
    private final BettingMoney bettingMoney;
    private final LottoGameScreen lottoGameScreen;

    public LottoGameMachine(final BettingMoney bettingMoney, LottoGameScreen lottoGameScreen) {
        this.bettingMoney = bettingMoney;
        this.lottoGameScreen = lottoGameScreen;
    }

    public LottoTickets makeLottos() {
        TicketCount ticketCount = getTicketCount();
        LottoGameScreen lottoGameScreen = new LottoGameScreen();
        lottoGameScreen.showLottoCount(new LottoCountResponseDto(ticketCount.getTicketCount()));

        LottoTickets lottoTickets = makeLottos(ticketCount);
        List<LottoTicket> lottoTicketGroup = lottoTickets.getLottos();

        List<LottoResponseDto> lottoResponseDtos = makeLottoResponseDtos(lottoTicketGroup);
        lottoGameScreen.showAllLottoStatus(lottoResponseDtos);
        return lottoTickets;
    }

    private List<LottoResponseDto> makeLottoResponseDtos(final List<LottoTicket> lottoTicketGroup) {
        List<LottoResponseDto> lottoResponseDtos = lottoTicketGroup.stream()
                .map(lotto -> (new LottoResponseDto(lotto)))
                .collect(Collectors.toList());
        return lottoResponseDtos;
    }

    private LottoTickets makeLottos(final TicketCount ticketCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount.getTicketCount(); i++) {
            lottoTickets.add(new LottoTicket(RandomLottoUtil.generateLottoNumbers()));
        }
        return new LottoTickets(lottoTickets);
    }

    private TicketCount getTicketCount() {
        int lottoCount = bettingMoney.getTicketCount();
        return TicketCount.of(lottoCount);
    }

    public WinningLotto findWinnings() {
        lottoGameScreen.confirmWinningLotto();
        Set<Integer> winningNumbers = InputUtil.inputWinningNumbers();

        lottoGameScreen.confirmBonusLotto();
        int bonusNumber = InputUtil.inputBonusNumber();

        return new WinningLotto(winningNumbers, bonusNumber);
    }

    public void lottoDraw(LottoTickets lottoTickets, WinningLotto winnings) {
        Result result = new Result(lottoTickets);
        Map<LottoRank, Integer> matches = result.findMatches(winnings);
        lottoGameScreen.showDrawResult(new DrawResultDto(matches));
        int prizeMoney = 0;

        List<Integer> prizes = matches.entrySet().stream()
                .map(Map.Entry::getKey)
                .map(LottoRank::getPrize)
                .collect(Collectors.toList());
        for (int prize : prizes) {
            prizeMoney += prize;
        }

        BigDecimal revenue = bettingMoney.getRevenue(prizeMoney);
        lottoGameScreen.showRevenueResult(new RevenueDto(revenue));
    }
}
