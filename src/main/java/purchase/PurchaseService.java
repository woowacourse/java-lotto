package purchase;

import domain.IssuedLottos;
import domain.Lotto;
import domain.LottoFactory;
import domain.Statistics;
import domain.money.Money;
import repository.IssuedLottoDao;
import repository.LottoInmemoryRepository;
import repository.StatisticsDao;
import spark.Request;

import java.util.*;
import java.util.stream.Collectors;

class PurchaseService {
    static IssuedLottos autoissueLottosWorthOf(Money purchaseAmount) {
        IssuedLottoDao issuedLottoDao = IssuedLottoDao.getInsatnce();
        StatisticsDao statisticsDao = StatisticsDao.getInstance();
        IssuedLottos issuedLottos = LottoFactory.autoIssueLottoWorthOf(purchaseAmount);

        int trial = statisticsDao.fetchLastTrial() + 1;
        for (Lotto lotto : issuedLottos.getLottos()) {
            System.out.println("===================================");
            System.out.println(lotto);
            issuedLottoDao.add(lotto, trial);
        }
        return issuedLottos;
    }

    static IssuedLottos manualIssueLottosBy(Request req) {
        IssuedLottoDao issuedLottoDao = IssuedLottoDao.getInsatnce();
        StatisticsDao statisticsDao = StatisticsDao.getInstance();
        int trial = statisticsDao.fetchLastTrial() + 1;

        IssuedLottos manualIssuedLottos = IssuedLottos.of(new ArrayList<>());
        List<String> groupOfSixNumbers = getManualNumbersFrom(req);

        for (String sixNumbers : groupOfSixNumbers) {
            List<Integer> manualNumbers = splitToInt(sixNumbers);
            manualIssuedLottos.add(LottoFactory.manualIssueLottoBy(manualNumbers));
        }

        for (Lotto lotto : manualIssuedLottos.getLottos()) {
            issuedLottoDao.add(lotto, trial);
        }
        return manualIssuedLottos;
    }

    private static List<String> getManualNumbersFrom(Request req) {
        if (req == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(req.queryParams("manualNumbers")
                    .split("\\n+"))
                    .map(String::trim)
                    .collect(Collectors.toList());
    }

    private static List<Integer> splitToInt(String sixNumbers) {
        return Arrays.stream(sixNumbers.split(",\\s*"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
