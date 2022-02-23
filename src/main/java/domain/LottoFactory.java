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

    final private List<LottoNumbers> lotto = new ArrayList<>();
    final private List<Integer> winCounts = new ArrayList<>();
    private final Money money;
    private LottoNumbers winNumbers;
    private LottoNumber bonusNumber;

    public LottoFactory(final Money money, final LottoNumbers winNumbers, final LottoNumber bonusNumber) {
        this.money = money;
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoFactory(final Money money) {
        this.money = money;
    }

//    public Count calculateCount() {
//        return new Count(money.calculateCounts());
//    }

    public LottoNumbers generateAutoLottoNumbers() {
        HashSet<LottoNumber> autoLottoNumbers = new HashSet<>();
        while (autoLottoNumbers.size() < 6) {
            autoLottoNumbers.add(new LottoNumber(ThreadLocalRandom.current().nextInt(45) + 1));
        }

        return new LottoNumbers(autoLottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList()));
    }

    public List<LottoNumbers> issueLotto() {
        Count count = new Count(money.calculateCounts());
        lotto.clear();
        while (!count.isEnd()) {
            count = count.decrease();
            lotto.add(generateAutoLottoNumbers());
        }
        return Collections.unmodifiableList(lotto);
    }

    public List<LottoNumbers> getLottoTickets() {
        return Collections.unmodifiableList(lotto);
    }

    public SortedMap<WinPrice, Integer> run(final LottoNumbers winNumbers, final LottoNumber bonusNumber) {
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;

        compareAndCount(); // 1. 로또별 당첨갯수 list 채우기
        return countWin();
        // final double profit = calculateProfit(winPriceIntegerSortedMap); 3.

    }

    public void compareAndCount() {
        for (LottoNumbers autoLottoNumbers : lotto) {
            int winCount = autoLottoNumbers.compare(this.winNumbers);
            if (winCount == 5 && autoLottoNumbers.compareBonus(bonusNumber)) {
                winCount += 2; // todo: 포장하기
            }
            winCounts.add(winCount);
        }
    }

    public SortedMap<WinPrice, Integer> countWin() {
        SortedMap<WinPrice, Integer> rankCounts = new TreeMap<>(Collections.reverseOrder());

        Arrays.stream(WinPrice.values())
                .forEach(e -> rankCounts.put(e, 0));

        for (Integer winCount : winCounts) {
            if (winCount >= 3) {
                final WinPrice winPrice = WinPrice.findByCount(winCount);
                rankCounts.put(winPrice, rankCounts.get(winPrice) + 1);
            }
        }

        return rankCounts;
    }

    public double calculateProfit(final SortedMap<WinPrice, Integer> rankCounts) {
        int totalWinPrice = 0;

        for (WinPrice winPrice : rankCounts.keySet()) {
            totalWinPrice += winPrice.getPrice() * rankCounts.get(winPrice);
        }
        return money.calculateProfit(totalWinPrice);
    }
}
