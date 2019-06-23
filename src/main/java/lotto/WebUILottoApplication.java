package lotto;

import com.google.gson.Gson;
import lotto.dto.LottoRoundDTO;
import lotto.dto.PurchaseDTO;
import lotto.dto.WinningLottoDTO;
import lotto.dto.WinningResultDTO;
import lotto.service.lottoroundservice.LottoRoundService;
import lotto.service.lottoticketservice.LottoTicketService;
import lotto.service.winninglottoservice.WinningLottoService;
import lotto.service.winningresultservice.WinningResultService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUILottoApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/purchase", (request, response) -> {
            PurchaseDTO purchaseDTO = new Gson().fromJson(request.body(), PurchaseDTO.class);

            int lottoRoundId = LottoRoundService.getInstance().addLottoRound();
            request.session().attribute("lottoRoundId", lottoRoundId);
            LottoTicketService.getInstance().addLottoTicketByLottoRoundId(purchaseDTO.getLottoNumbers(), purchaseDTO.getPurchaseAmount(), lottoRoundId);
            return "";
        });

        post("/winninglotto", (request, response) -> {
            response.type("application/json");
            WinningLottoDTO winningLottoDTO = new Gson().fromJson(request.body(), WinningLottoDTO.class);

            int lottoRoundId = request.session().attribute("lottoRoundId");
            WinningLottoService.addWinningLottoByLottoRoundId(winningLottoDTO.getWinningLotto(), winningLottoDTO.getBonusNumber(), lottoRoundId);
            WinningResultService.addWinningResult(winningLottoDTO, lottoRoundId);
            return new Gson().toJson(new LottoRoundDTO(lottoRoundId));
        });

        get("/winningResult/:lottoRoundId", (request, response) -> {
            response.type("application/json");
            int lottoRoundId = Integer.parseInt(request.params(":lottoRoundId"));

            WinningResultDTO winningResultDTO = WinningResultService.getWinningResultByLottoRoundId(lottoRoundId);
            return new Gson().toJson(winningResultDTO);
        });

        get("/winningResult", (request, response) -> {
            response.type("application/json");

            List<LottoRoundDTO> lottoRounds = LottoRoundService.getInstance().getLottoRoundAll();
            return new Gson().toJson(lottoRounds);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}