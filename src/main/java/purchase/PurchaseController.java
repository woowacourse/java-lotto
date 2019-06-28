package purchase;

import domain.IssuedLottos;
import domain.money.IllegalNumberOfManualIssueException;
import domain.money.Money;
import domain.money.PurchaseAmount;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.ViewUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PurchaseController {
    public static final Route servePurchasePage = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(model, "purchase-page.html");
    };

    public static final Route handlePurchase = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();

        PurchaseAmount totalPurchaseAmount = PurchaseAmount.valueOf(Integer.parseInt(req.queryParams("purchaseAmount")));
        int numberOfMannualPurchase = Optional.ofNullable(req.queryParams("numberOfManualPurchase"))
                .map(String::trim)
                .map(Integer::parseInt)
                .orElse(0);
        IssuedLottos manualIssuedLottos;

        try {
            totalPurchaseAmount.checkNumberOfManualIssue(numberOfMannualPurchase);
            manualIssuedLottos = PurchaseService.manualIssueLottosBy(req);
        } catch (IllegalNumberOfManualIssueException e) {
            System.out.println(e.getMessage());
            return ViewUtil.render(model, "purchase-fail-page.html");
        }

        Money changeAfterManualIssue = totalPurchaseAmount.getChangeOf(manualIssuedLottos.getPurchasedAmount());
        IssuedLottos autoissuedLottos = PurchaseService.autoissueLottosWorthOf(changeAfterManualIssue);
        IssuedLottos issuedLottos = IssuedLottos.join(manualIssuedLottos, autoissuedLottos);

        model.put("sizeOfManual", manualIssuedLottos.size());
        model.put("sizeOfAuto", autoissuedLottos.size());
        model.put("issuedLottos", issuedLottos.getLottos());

        return ViewUtil.render(model, "purchase-success-page.html");
    };
}
