package lotto;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lotto.domain.machine.Money;

import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        post("/api/money", (req, res) -> {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(req.body());
            int moneyAmount = element.getAsJsonObject().get("money").getAsInt();
            Money money = Money.of(moneyAmount);
            req.session().attribute("user",money);
            return money.ticketQuantity();
        });

        post("/api/manual", (req, res) -> {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(req.body());
            int manualAmount = element.getAsJsonObject().get("manualAmount").getAsInt();
            System.out.println(req);
            return null;
        });


    }

}
