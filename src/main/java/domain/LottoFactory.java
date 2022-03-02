package domain;

import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class LottoFactory {

    private static final int LOTTO_SIZE = 6;
    private static final int RANK_COUNT_UNIT = 1;
    private static final int RANK_COUNT_INIT_NUMBER = 0;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_UNIT_TO_CORRECT = 1;
    private static final int INIT_WIN_PRIZE = 0;
    private static final String ERROR_MESSAGE = "[ERROR] ";

    private final List<Lotto> issuedLotto = new ArrayList<>();
    private final List<CorrectNumber> correctNumbersOfIssuedLotto = new ArrayList<>();
    private final Money money;
    private Count manualCount;
    private Lotto lastWinLotto;
    private LottoNumber bonusNumber;

    public LottoFactory(final Money money, final Count manualCount) {
        this.money = money;
        this.manualCount = manualCount;
    }

    public List<Lotto> issueLotto() {
        issuedLotto.clear();
        correctNumbersOfIssuedLotto.clear();
        issueManualLotto();
        generateLotto(money.calculateCounts() - issuedLotto.size());
        return Collections.unmodifiableList(issuedLotto);
    }

    private void issueManualLotto() {
        OutputView.printManualLottoInstruction();
        while (!manualCount.isEnd()) {
            issuedLotto.add(generateManualLotto());
            this.manualCount = manualCount.decrease();
        }
    }

    private Lotto generateManualLotto() {
        try {
            return new Lotto(InputView.getManualLotto());
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return generateManualLotto();
        }
    }

    private void generateLotto(int number) {
        Count count = new Count(number);
        while (!count.isEnd()) {
            count = count.decrease();
            issuedLotto.add(generateAutoLotto());
        }
    }

    private Lotto generateAutoLotto() {
        HashSet<LottoNumber> autoLottoNumbers = new HashSet<>();
        while (autoLottoNumbers.size() < LOTTO_SIZE) {
            autoLottoNumbers.add(
                    new LottoNumber(
                            ThreadLocalRandom.current().nextInt(LOTTO_NUMBER_MAX) + LOTTO_NUMBER_UNIT_TO_CORRECT));
        }
        return new Lotto(autoLottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList()));
    }

    public List<Lotto> getLotto() {
        return Collections.unmodifiableList(issuedLotto);
    }

    public SortedMap<RankPrize, Integer> run(final Lotto lastWinLotto, final LottoNumber bonusNumber) {
        this.lastWinLotto = lastWinLotto;
        this.bonusNumber = bonusNumber;
        return extractRankCount();
    }

    private SortedMap<RankPrize, Integer> extractRankCount() {
        for (Lotto lotto : issuedLotto) {
            correctNumbersOfIssuedLotto.add(getCorrectNumber(lotto));
        }
        return processRankCount();
    }

    private CorrectNumber getCorrectNumber(final Lotto lotto) {
        CorrectNumber correctNumber = new CorrectNumber(lotto.compare(this.lastWinLotto), false);
        if (isSecondRank(lotto, correctNumber)) {
            correctNumber = correctNumber.convertToSecondRankCount();
        }
        return correctNumber;
    }

    private boolean isSecondRank(final Lotto lotto, final CorrectNumber correctNumber) {
        return correctNumber.isThirdRankCount() && lotto.isContainNumber(bonusNumber);
    }

    private SortedMap<RankPrize, Integer> processRankCount() {
        SortedMap<RankPrize, Integer> rankCount = new TreeMap<>(Collections.reverseOrder());
        initRank(rankCount);
        countRank(rankCount);
        return rankCount;
    }

    private void initRank(final SortedMap<RankPrize, Integer> rankCount) {
        Arrays.stream(RankPrize.values())
                .forEach(e -> rankCount.put(e, RANK_COUNT_INIT_NUMBER));
    }

    private void countRank(final SortedMap<RankPrize, Integer> rankCount) {
        for (CorrectNumber correctNumber : correctNumbersOfIssuedLotto) {
            countOverFifthRank(rankCount, correctNumber);
        }
    }

    private void countOverFifthRank(final SortedMap<RankPrize, Integer> rankCount, final CorrectNumber correctNumber) {
        if (correctNumber.isInRank()) {
            final RankPrize rankPrize = correctNumber.findRankPrize();
            rankCount.put(rankPrize, rankCount.get(rankPrize) + RANK_COUNT_UNIT);
        }
    }

    public double calculateProfit(final int totalPrize) {
        return money.calculateProfit(totalPrize);
    }

    public int calculatePrize(SortedMap<RankPrize, Integer> rankCounts) {
        int totalPrize = INIT_WIN_PRIZE;
        for (RankPrize rankPrize : rankCounts.keySet()) {
            totalPrize += rankPrize.getPrize() * rankCounts.get(rankPrize);
        }
        return totalPrize;
    }
}
