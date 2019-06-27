package lotto;

import lotto.controller.LottoGame;
import lotto.view.JsonInput;
import lotto.view.JsonOutput;

import java.util.List;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final int SERVICE_PORT = 8080;
    private static final String STATIC_FILE_LOCATION = "/";
    private static final String CONTENT_JSON = "application/json";
    private static final String EMPTY_JSON = "{}";

    public static void main(final String[] args) {
        port(SERVICE_PORT);
        staticFileLocation(STATIC_FILE_LOCATION);
        init();

        DBConnection dbConnection = new DBConnection("localhost", "lotto", "kunggom", "1234");
        LottoDAO lottoDAO = new LottoDAO(dbConnection);

        post("/api/newLottos", (request, response) -> {
            response.type(CONTENT_JSON);
            final LottoGame game = JsonInput.getLottoGame(request.body());
            lottoDAO.newLottos(game);
            return JsonOutput.lottoGameToJSON(game);
        });

        get("api/previousResults", (request, response) -> {
            response.type(CONTENT_JSON);
            List<PreviousWinLottoResultDTO> resultDTO = lottoDAO.getPreviousResults();
            return JsonOutput.previousResultListDTO(resultDTO);
        });
    }
}
