package lottery;

import spark.Request;
import spark.Route;
import utils.ViewUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LotteryController {
    public static final Route serveLotteryPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(model, "lottery-page.html");
    };

    public static final Route handleLottery = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        int bonusNumber = getBonusNumber(req);
        List<Integer> winningNumbers = getWinningNumbers(req);

        StatisticsDTO statisticsDTOs = LotteryService.startLottery(winningNumbers, bonusNumber);

        model.put("statistics", statisticsDTOs.getCountsOfRanks());
        model.put("earningRatesAsPercent", statisticsDTOs.getEarningRates() * 100);
        return ViewUtil.render(model, "lottery-statistics-page.html");
    };

    private static int getBonusNumber(Request req) {
        return Integer.parseInt(req.queryParams("bonusNumber"));
    }

    private static List<Integer> getWinningNumbers(Request req) {
        return Arrays.stream(req.queryParams("winningNumbers")
                    .trim()
                    .split(",\\s*"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
    }
}
