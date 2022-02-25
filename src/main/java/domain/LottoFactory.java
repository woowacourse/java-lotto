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

    private final Money money;
    private Lotto lastWinLotto;
    private LottoNumber bonusNumber;

    public LottoFactory(final Money money) {
        this.money = money;
    }

    public List<Lotto> issueLotto() {
        final ArrayList<Lotto> issuedLotto = generateLotto(money.calculateCounts());
        return Collections.unmodifiableList(issuedLotto);
    }

    private ArrayList<Lotto> generateLotto(int number) {
        return issueLottoWithCount(number);
    }

    private ArrayList<Lotto> issueLottoWithCount(final int number) {
        final ArrayList<Lotto> issuedLotto = new ArrayList<>();
        Count count = new Count(number);
        while (!count.isEnd()) {
            count = count.decrease();
            issuedLotto.add(generateAutoLotto());
        }
        return issuedLotto;
    }

    private Lotto generateAutoLotto() {
        return getAutoLottoFrom(getAutoLottoNumbers());
    }

    private HashSet<LottoNumber> getAutoLottoNumbers() {
        HashSet<LottoNumber> autoLottoNumbers = new HashSet<>();
        while (autoLottoNumbers.size() < LOTTO_SIZE) {
            autoLottoNumbers.add(
                new LottoNumber(
                    ThreadLocalRandom.current().nextInt(LOTTO_NUMBER_MAX) + LOTTO_NUMBER_UNIT_TO_CORRECT));
        }
        return autoLottoNumbers;
    }

    private Lotto getAutoLottoFrom(final HashSet<LottoNumber> autoLottoNumbers) {
        return new Lotto(autoLottoNumbers.stream()
            .sorted()
            .collect(Collectors.toList()));
    }

    public SortedMap<RankPrice, Integer> run(final Lotto lastWinLotto, final LottoNumber bonusNumber,
                                             final List<Lotto> issuedLotto) {
        this.lastWinLotto = lastWinLotto;
        this.bonusNumber = bonusNumber;
        return extractRankCount(issuedLotto);
    }

    private SortedMap<RankPrice, Integer> extractRankCount(final List<Lotto> issuedLotto) {
        final List<WinCount> winCountsOfIssuedLotto = new ArrayList<>();
        for (Lotto lotto : issuedLotto) {
            winCountsOfIssuedLotto.add(getWinCount(lotto));
        }
        return processRankCount(winCountsOfIssuedLotto);
    }

    private WinCount getWinCount(final Lotto lotto) {
        WinCount winCount = new WinCount(lotto.compare(this.lastWinLotto));
        if (isSecondRank(lotto, winCount)) {
            winCount = winCount.convertToSecondRankCount();
        }
        return winCount;
    }

    private boolean isSecondRank(final Lotto lotto, final WinCount winCount) {
        return winCount.isThirdRankCount() && lotto.isContainNumber(bonusNumber);
    }

    private SortedMap<RankPrice, Integer> processRankCount(final List<WinCount> winCountsOfIssuedLotto) {
        SortedMap<RankPrice, Integer> rankCount = new TreeMap<>(Collections.reverseOrder());
        initRank(rankCount);
        countRank(rankCount, winCountsOfIssuedLotto);
        return rankCount;
    }

    private void initRank(final SortedMap<RankPrice, Integer> rankCount) {
        Arrays.stream(RankPrice.values())
            .forEach(e -> rankCount.put(e, RANK_COUNT_INIT_NUMBER));
    }

    private void countRank(final SortedMap<RankPrice, Integer> rankCount,
                           final List<WinCount> winCountsOfIssuedLotto) {
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
