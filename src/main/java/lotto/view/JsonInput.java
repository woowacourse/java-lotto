package lotto.view;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lotto.controller.LottoGame;
import lotto.model.*;

import java.util.ArrayList;
import java.util.List;

public class JsonInput {
    private static final JsonParser JSON_PARSER = new JsonParser();
    private static final String PURCHASE_AMOUNT = "allPurchaseAmount";
    private static final String MANUAL_LOTTOS = "manualLottos";
    private static final String WIN_LOTTO = "winningLotto";
    private static final String BONUS_NUMBER = "bonusNo";
    private static final LottoRule RULE = new KoreaLottoRule();
    private static final LottoMaker MAKER = new RandomLottoMaker(RULE);

    public static LottoGame getLottoGame(final String json) {
        final JsonElement element = JSON_PARSER.parse(json);
        final int bonusNo = element.getAsJsonObject().get(BONUS_NUMBER).getAsInt();
        final int purchaseAmount = element.getAsJsonObject().get(PURCHASE_AMOUNT).getAsInt();
        final JsonArray manualLottos = element.getAsJsonObject().get(MANUAL_LOTTOS).getAsJsonArray();
        final JsonArray winningLotto = element.getAsJsonObject().get(WIN_LOTTO).getAsJsonArray();
        return makeLottoGame(manualLottos, winningLotto, purchaseAmount, bonusNo);
    }

    private static LottoGame makeLottoGame(final JsonArray manualLottos, final JsonArray winningLotto, final int purchaseAmount, final int bonusNo) {
        final List<Lotto> manualLottoList = parseLottoList(manualLottos);
        final Lottos lottos = new Lottos(getLottoCount(purchaseAmount), manualLottoList.size());
        lottos.add(manualLottoList);
        final WinningLotto winLotto = new WinningLotto(parseLotto(winningLotto), bonusNo);
        return new LottoGame.Builder(RULE, MAKER)
                .purchaseAmount(purchaseAmount)
                .purchasedLottos(lottos)
                .winLotto(winLotto)
                .build();
    }

    private static int getLottoCount(final int purchaseAmount) {
        return purchaseAmount / RULE.getPrice();
    }

    private static List<Lotto> parseLottoList(final JsonArray array) {
        final List<Lotto> result = new ArrayList<>();
        for (JsonElement lottoArray : array) {
            result.add(parseLotto(lottoArray));
        }
        return result;
    }

    private static Lotto parseLotto(final JsonElement array) {
        final List<Integer> numbers = new ArrayList<>();
        for (JsonElement element : array.getAsJsonArray()) {
            numbers.add(element.getAsInt());
        }
        return new Lotto(numbers, RULE);
    }
}
