package lotto.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lotto.domain.game.WinningLotto;
import lotto.domain.ticket.LottoNumber;
import lotto.dto.ResultDTO;
import lotto.service.ResultService;
import lotto.util.Parser;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultController {


    public static Map<String, Object> getWinningLotto(Request req, Response res) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(req.body());

            String numbrers = element.getAsJsonObject().get("winningNumber").getAsString();
            List<LottoNumber> winningNumbers = Parser.parseManualStringtoLottoNumber(numbrers);
            int bonusNumber = element.getAsJsonObject().get("bonusNumber").getAsInt();

            WinningLotto winningLotto = WinningLotto.of(winningNumbers, LottoNumber.of(bonusNumber));
            ResultService.saveWinningLotto(req.session().attribute("round"), winningLotto);
            ResultDTO resultDTO = ResultService.createResult(req.session().attribute("round"), req.session().attribute("lottoTickets"), winningLotto);
            ResultService.saveResult(resultDTO);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("error", e);
            return resMap;
        }
    }

    public static Map<String, Object> getResult(Request req, Response res) {
        try {
            Map<String, Object> resMap = new HashMap<>();

            resMap.put("prize",  ResultService.getLastestPrize());
            resMap.put("winningRate", ResultService.getLastestRate());
            return resMap;
        } catch (SQLException e) {
            e.printStackTrace();
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("error", e);
            return resMap;
        }
    }
}
