package domain;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.budget.Budget;
import domain.lotto.Lotto;
import domain.lotto.LottoCount;
import domain.lotto.Lottos;
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
import java.util.stream.Collectors;

public class LottoGameMachine {
    private static final Budget LOTTO_COST = Budget.amounts(1000);

    private final Budget budget;
    private final LottoGameScreen lottoGameScreen;

    public LottoGameMachine(final Budget budget, LottoGameScreen lottoGameScreen) {
        this.budget = budget;
        this.lottoGameScreen = lottoGameScreen;
    }

    public Lottos makeLottos() {
        LottoCount lottoCount = calculateLottoCount();
        LottoGameScreen lottoGameScreen = new LottoGameScreen();
        lottoGameScreen.showLottoCount(new LottoCountResponseDto(lottoCount.getLottoCount()));

        Lottos lottos = makeLottos(lottoCount);
        List<Lotto> lottoGroup = lottos.getLottos();

        List<LottoResponseDto> lottoResponseDtos = makeLottoResponseDtos(lottoGroup);
        lottoGameScreen.showAllLottoStatus(lottoResponseDtos);
        return lottos;
    }

    private List<LottoResponseDto> makeLottoResponseDtos(final List<Lotto> lottoGroup) {
        List<LottoResponseDto> lottoResponseDtos = lottoGroup.stream()
                .map(lotto -> (new LottoResponseDto(lotto)))
                .collect(Collectors.toList());
        return lottoResponseDtos;
    }

    private Lottos makeLottos(final LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount.getLottoCount(); i++) {
            lottos.add(new Lotto(RandomLottoUtil.generateLottoNumbers()));
        }
        return new Lottos(lottos);
    }

    private LottoCount calculateLottoCount() {
        int lottoCount = budget.intQuotient(LOTTO_COST);
        return LottoCount.of(lottoCount);
    }

    public WinningLotto findWinnings() {
        lottoGameScreen.confirmWinningLotto();
        String winningLottoInput = InputUtil.nextLine();

        lottoGameScreen.confirmBonusLotto();
        String bonusLottoInput = InputUtil.nextLine();

        LottoBalls lottoBalls = new LottoBalls(winningLottoInput);
        LottoBall bonusBall = LottoBall.of(bonusLottoInput);

        return new WinningLotto(lottoBalls, bonusBall);
    }

    public void lottoDraw(Lottos lottos, WinningLotto winnings) {
        Result result = new Result(lottos);
        Map<LottoRank, Integer> matches = result.findMatches(winnings);
        lottoGameScreen.showDrawResult(new DrawResultDto(matches));
        Budget price = Budget.amounts(0);

        List<Budget> budgets = matches.entrySet().stream()
                .map(match -> match.getKey())
                .map(lottoRank -> lottoRank.getBudget())
                .collect(Collectors.toList());
        for (Budget budget : budgets) {
            price = price.add(budget);
        }

        BigDecimal revenue = price.getRevenue(budget);
        lottoGameScreen.showRevenueResult(new RevenueDto(revenue));
    }
}
