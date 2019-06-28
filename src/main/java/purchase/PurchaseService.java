package purchase;

import domain.IssuedLottos;
import domain.LottoFactory;
import domain.money.Money;
import repository.LottoInmemoryRepository;
import spark.Request;

import java.util.*;
import java.util.stream.Collectors;

class PurchaseService {
    static IssuedLottos autoissueLottosWorthOf(Money purchaseAmount) {
        IssuedLottos issuedLottos = LottoFactory.autoIssueLottoWorthOf(purchaseAmount);

        LottoInmemoryRepository.add(issuedLottos);
        return issuedLottos;
    }

    static IssuedLottos manualIssueLottosBy(Request req) {
        IssuedLottos manualIssuedLottos = IssuedLottos.of(new ArrayList<>());
        List<String> groupOfSixNumbers = getManualNumbersFrom(req);

        for (String sixNumbers : groupOfSixNumbers) {
            List<Integer> manualNumbers = splitToInt(sixNumbers);
            manualIssuedLottos.add(LottoFactory.manualIssueLottoBy(manualNumbers));
        }

        LottoInmemoryRepository.add(manualIssuedLottos);
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
