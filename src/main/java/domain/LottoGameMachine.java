package domain;

import domain.budget.Budget;
import domain.lotto.LottoTicket;
import domain.lotto.TicketCount;
import domain.lotto.LottoTickets;
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
    private static final Budget LOTTO_COST = Budget.amounts(1000);

    private final Budget budget;
    private final LottoGameScreen lottoGameScreen;

    public LottoGameMachine(final Budget budget, LottoGameScreen lottoGameScreen) {
        this.budget = budget;
        this.lottoGameScreen = lottoGameScreen;
    }

    public LottoTickets makeLottos() {
        TicketCount ticketCount = calculateLottoCount();
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

    private TicketCount calculateLottoCount() {
        int lottoCount = budget.intQuotient(LOTTO_COST);
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
        Budget price = Budget.amounts(0);

        List<Budget> budgets = matches.entrySet().stream()
                .map(Map.Entry::getKey)
                .map(LottoRank::getBudget)
                .collect(Collectors.toList());
        for (Budget budget : budgets) {
            price = price.add(budget);
        }

        BigDecimal revenue = price.getRevenue(budget);
        lottoGameScreen.showRevenueResult(new RevenueDto(revenue));
    }
}
