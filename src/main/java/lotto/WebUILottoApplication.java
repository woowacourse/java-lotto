package lotto;

import com.google.gson.Gson;
import lotto.domain.*;
import lotto.dto.ErrorDto;
import lotto.dto.LottoGameDto;
import lotto.dto.RoundResultDto;
import lotto.service.LottoRegister;
import lotto.service.LottoVendingMachine;
import lotto.service.WinningLottoParser;
import lotto.util.JsonTransformer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/lotto", "application/json", (req, res) -> {
            Gson gson = new Gson();
            try {
                LottoGameDto lottoGameDto = gson.fromJson(req.body(), LottoGameDto.class);
                RoundResultDto roundResultDto = createRoundResultDto(lottoGameDto);

                return LottoRegister.register(roundResultDto);
            } catch (IllegalArgumentException e) {
                return new ErrorDto(e.getMessage());
            }
        }, new JsonTransformer());

        get("/round/:roundNo", (req, res) -> {
            try {
                int roundNo = Integer.valueOf(req.params(":roundNo"));
                return LottoRegister.getRoundResult(roundNo);

            } catch (IllegalArgumentException e) {
                return new ErrorDto(e.getMessage());
            }
        }, new JsonTransformer());
    }

    private static RoundResultDto createRoundResultDto(LottoGameDto lottoGameDto) throws IllegalArgumentException {
        RoundResultDto roundResultDto = new RoundResultDto();
        roundResultDto.setMoney(new Money(lottoGameDto.getMoney()));
        roundResultDto.setLottoTickets(createLottoTickets(lottoGameDto, roundResultDto));
        roundResultDto.setWinningLotto(WinningLottoParser.parse(lottoGameDto.getWinningLotto(), lottoGameDto.getBonusNo()));
        roundResultDto.setWinningLottoState(roundResultDto.getWinningLotto().match(roundResultDto.getLottoTickets()));

        return roundResultDto;
    }

    private static LottoTickets createLottoTickets(LottoGameDto lottoGameDto, RoundResultDto roundResultDto) {
        LottoCount manualLottoCount = new LottoCount(roundResultDto.getMoney(), lottoGameDto.getLottoList().size());
        LottoNoGenerators lottoNoGenerators = new LottoNoGenerators();
        for (int index = 0; index < manualLottoCount.getCount(); index++) {
            lottoNoGenerators.add(new LottoNoManualGenerator(lottoGameDto.getLottoList().get(index)));
        }
        return LottoVendingMachine.purchase(roundResultDto.getMoney(), lottoNoGenerators);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
