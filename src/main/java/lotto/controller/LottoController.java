package lotto.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lotto.domain.machine.Money;
import lotto.dto.PurchaseDTO;
import lotto.service.PurchaseService;
import lotto.service.RoundService;
import lotto.util.Parser;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {
    public static Map<String, Object> insertMoney(Request req, Response res) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(req.body());
            Map<String, Object> resMap = new HashMap<>();

            int moneyAmount = element.getAsJsonObject().get("money").getAsInt();
            Money money = Money.of(moneyAmount);
            req.session().attribute("money", money);
            resMap.put("rest", money.getRest());
            return resMap;

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("error", e);
            return resMap;
        }
    }

    public static Map<String, Object> doPurchase(Request req, Response res) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray element = parser.parse(req.body()).getAsJsonObject().get("manualNumbers").getAsJsonArray();

            List<List<Integer>> manualNumbers = new ArrayList<>();
            for (int i = 0; i < element.size(); i++) {
                manualNumbers.add(Parser.parseManualStringtoInt(element.get(i).getAsString()));
            }

            int nowRound = RoundService.getMaxRound() + 1;
            RoundService.createRound(nowRound);

            req.session().attribute("round", nowRound);
            PurchaseDTO purchaseDTO = PurchaseService.createPurchaseDTO(nowRound, req.session().attribute("money"), manualNumbers);
            PurchaseService.savePurchase(purchaseDTO);
            req.session().attribute("lottoTickets", purchaseDTO.getLottoTickets());
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("error", e);
            return resMap;
        }
    }
}
