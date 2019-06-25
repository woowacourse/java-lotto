package lotto.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lotto.dto.HistoryDTO;
import lotto.service.HistoryService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HistoryController {
    public static Map<String, Object> getHistory(Request req, Response res) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(req.body());
            Map<String, Object> resMap = new HashMap<>();
            int round = element.getAsJsonObject().get("round").getAsInt();
            HistoryDTO historyDTO = HistoryService.createHistoryDTO(round);
            resMap.put("prize", historyDTO.getPrize());
            resMap.put("lottoNumbers", historyDTO.getLottoNumbers());
            resMap.put("winningRate", historyDTO.getWinningRate());
            resMap.put("winningNumber", historyDTO.getWinningNumbers());
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("error", e);
            return resMap;
        }
    }
}
