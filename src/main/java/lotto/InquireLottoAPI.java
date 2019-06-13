package lotto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Request;

public class InquireLottoAPI {
    public static String lookUp() throws SQLException {
        GameDAO gameDAO = new GameDAO();
        return TemplateEngine.render(lookUpModel(gameDAO), "lookup.html");
    }

    private static Map<String, Object> lookUpModel(GameDAO gameDAO) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        model.put("totalCount", gameDAO.selectQuery("COUNT(id)"));
        return model;
    }

    public static String look(Request req) throws SQLException {
        int games_id = Integer.parseInt(req.queryParams("radio"));
        GameDAO gameDAO = new GameDAO();
        List<String> lottos = gameDAO.getLottosOfGame(games_id);
        GameDTO gameDTO = gameDAO.getGameInformation(games_id);
        return TemplateEngine.render(lookModel(games_id, lottos, gameDTO), "game_results.html");
    }

    private static Map<String, Object> lookModel(int games_id, List<String> lottos, GameDTO gameDTO1) {
        Map<String, Object> model = new HashMap<>();
        model.put("games_id", games_id);
        model.put("lottos", lottos);
        model.put("game_info", gameDTO1);
        return model;
    }
}
