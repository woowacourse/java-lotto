package domain;

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
    private static final int INIT_WIN_PRICE = 0;

    private final List<Lotto> issuedLotto = new ArrayList<>();
    private final List<WinCount> winCountsOfIssuedLotto = new ArrayList<>();
    private final Money money;
    private Lotto lastWinLotto;
    private LottoNumber bonusNumber;

    public LottoFactory(final Money money) {
        this.money = money;
    }

    public List<Lotto> issueLotto() {
        issuedLotto.clear();
        winCountsOfIssuedLotto.clear();
        generateLotto(money.calculateCounts());
        return Collections.unmodifiableList(issuedLotto);
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

    public SortedMap<RankPrice, Integer> run(final Lotto lastWinLotto, final LottoNumber bonusNumber) {
        this.lastWinLotto = lastWinLotto;
        this.bonusNumber = bonusNumber;
        return extractRankCount();
    }

    private SortedMap<RankPrice, Integer> extractRankCount() {
        for (Lotto lotto : issuedLotto) {
            winCountsOfIssuedLotto.add(getWinCount(lotto));
        }
        return processRankCount();
    }

    private WinCount getWinCount(final Lotto lotto) {
        WinCount winCount = new WinCount(lotto.compare(this.lastWinLotto), false);
        if (isSecondRank(lotto, winCount)) {
            winCount = winCount.convertToSecondRankCount();
        }
        return winCount;
    }

    private boolean isSecondRank(final Lotto lotto, final WinCount winCount) {
        return winCount.isThirdRankCount() && lotto.isContainNumber(bonusNumber);
    }

    private SortedMap<RankPrice, Integer> processRankCount() {
        SortedMap<RankPrice, Integer> rankCount = new TreeMap<>(Collections.reverseOrder());
        initRank(rankCount);
        countRank(rankCount);
        return rankCount;
    }

    private void initRank(final SortedMap<RankPrice, Integer> rankCount) {
        Arrays.stream(RankPrice.values())
                .forEach(e -> rankCount.put(e, RANK_COUNT_INIT_NUMBER));
    }

    private void countRank(final SortedMap<RankPrice, Integer> rankCount) {
        for (WinCount winCount : winCountsOfIssuedLotto) {
            countOverFifthRank(rankCount, winCount);
        }
    }

    private void countOverFifthRank(final SortedMap<RankPrice, Integer> rankCount, final WinCount winCount) {
        if (winCount.isInRank()) {
            final RankPrice rankPrice = winCount.findRankPrice();
            rankCount.put(rankPrice, rankCount.get(rankPrice) + RANK_COUNT_UNIT);
        }
    }

    public double calculateProfit(final SortedMap<RankPrice, Integer> rankCounts) {
        int totalWinPrice = INIT_WIN_PRICE;
        for (RankPrice rankPrice : rankCounts.keySet()) {
            totalWinPrice += rankPrice.getPrice() * rankCounts.get(rankPrice);
        }
        return money.calculateProfit(totalWinPrice);
    }
}
