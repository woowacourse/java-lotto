package lotto;

import lotto.model.LottoGame;
import lotto.view.JsonInput;
import lotto.view.JsonOutput;

import java.util.List;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final int SERVICE_PORT = 8080;
    private static final String STATIC_FILE_LOCATION = "/";
    private static final String CONTENT_JSON = "application/json";

    public static void main(final String[] args) {
        port(SERVICE_PORT);
        staticFileLocation(STATIC_FILE_LOCATION);
        init();

        DBConnection dbConnection = new DBConnection("localhost", "lotto", "user", "1234");
        LottoDAO lottoDAO = new LottoDAO(dbConnection);

        post("/api/newLottos", (request, response) -> {
            response.type(CONTENT_JSON);
            try {
                final LottoGame game = JsonInput.getLottoGame(request.body());
                lottoDAO.newLottos(game);
                return JsonOutput.lottoGameToJSON(game);
            } catch (Exception e) {
                return JsonOutput.responseFailed(e.getMessage());
            }
        });

        get("api/previousResults", (request, response) -> {
            response.type(CONTENT_JSON);
            try {
                List<PreviousWinLottoResultDTO> resultDTO = lottoDAO.getPreviousResults();
                System.err.println(JsonOutput.previousResultListDTO(resultDTO));
                return JsonOutput.previousResultListDTO(resultDTO);
            } catch (Exception e) {
                System.err.println(e);
                return JsonOutput.responseFailed(e.getMessage());
            }
        });
    }
}
