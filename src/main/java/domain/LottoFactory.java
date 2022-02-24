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
    private static final int SECOND_RANK_UNIT = 2;
    private static final int RANK_COUNT_UNIT = 1;
    private static final int RANK_COUNT_INIT_NUMBER = 0;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_UNIT_TO_CORRECT = 1;
    private static final int INIT_WIN_PRICE = 0;

    final private List<Lotto> issuedLotto = new ArrayList<>();
    final private List<Integer> winCountsOfIssuedLotto = new ArrayList<>();
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
            issuedLotto.add(generateAutoLottoNumbers());
        }
    }

    public Lotto generateAutoLottoNumbers() {
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


    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(issuedLotto);
    }

    public SortedMap<RankPrice, Integer> run(final Lotto lastWinLotto, final LottoNumber bonusNumber) {
        this.lastWinLotto = lastWinLotto;
        this.bonusNumber = bonusNumber;
        return extractRankCount();
    }

    public SortedMap<RankPrice, Integer> extractRankCount() {
        for (Lotto lotto : issuedLotto) {
            winCountsOfIssuedLotto.add(getWinCount(lotto));
        }
        return processRankCount();
    }

    private int getWinCount(final Lotto lotto) {
        int winCount = lotto.compare(this.lastWinLotto);
        if (isSecondRank(lotto, winCount)) {
            winCount += SECOND_RANK_UNIT; // todo: 포장하기
        }
        return winCount;
    }

    private boolean isSecondRank(final Lotto lotto, final int winCount) {
        return winCount == RankPrice.THIRD.getCount() && lotto.isContainNumber(bonusNumber);
    }

    public SortedMap<RankPrice, Integer> processRankCount() {
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
        for (Integer winCount : winCountsOfIssuedLotto) {
            countOverFifthRank(rankCount, winCount);
        }
    }

    private void countOverFifthRank(final SortedMap<RankPrice, Integer> rankCount, final Integer winCount) {
        if (winCount >= RankPrice.FIFTH.getCount()) {
            final RankPrice rankPrice = RankPrice.findByCount(winCount);
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
