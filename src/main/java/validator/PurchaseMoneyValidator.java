package validator;

public class PurchaseMoneyValidator {

    public static void validate(String purchaseMoneyString) {
        int purchaseMoney;
        try {
            purchaseMoney = Integer.parseInt(purchaseMoneyString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
