package lotto.domain;

import lotto.domain.ticket.LottoTicket;

public class PurchaseInfo {
    private static final int MANUAL_COUNT_LIMIT = 1;

    private final Money purchaseMoney;
    private final int purchaseManualCount;
    private final int purchaseAutoCount;

    public PurchaseInfo(Money purchaseMoney, int purchaseManualCount) {
        validateManualCountLimit(purchaseManualCount);
        validateNoExtraMoney(purchaseMoney);
        validateManualCountNotUnderZero(purchaseMoney, purchaseManualCount);

        this.purchaseMoney = purchaseMoney;
        this.purchaseManualCount = purchaseManualCount;
        this.purchaseAutoCount = calculatePurchaseAutoCount(purchaseMoney, purchaseManualCount);
    }

    private void validateManualCountLimit(int purchaseManualCount) {
        if (purchaseManualCount < MANUAL_COUNT_LIMIT) {
            throw new IllegalArgumentException(
                String.format("수동 로또 갯수는 %d개 이상이어야 합니다. 입력 값 : %d", MANUAL_COUNT_LIMIT,
                    purchaseManualCount));
        }
    }

    private void validateNoExtraMoney(Money purchaseMoney) {
        if (purchaseMoney.getMoney() % LottoTicket.PRICE != 0) {
            throw new IllegalArgumentException(
                String.format("로또는 %d원 단위로 구매해야 있습니다. 입력금액 : %d", LottoTicket.PRICE,
                    purchaseMoney.getMoney()));
        }
    }

    private void validateManualCountNotUnderZero(Money purchaseMoney, int purchaseManualCount) {
        int totalPurchaseCount = calculateTotalPurchaseCount(purchaseMoney);
        int autoPurchaseCount = calculatePurchaseAutoCount(purchaseMoney, purchaseManualCount);

        if (autoPurchaseCount < 0) {
            throw new IllegalArgumentException(
                String.format("수동 구매 개수가 구매 금액을 초과할 수 없습니다. 총 구매가능한 갯수 : %d, 수동 구매 요청 갯수 : %d",
                    totalPurchaseCount, purchaseManualCount));
        }
    }

    public int calculateTotalPurchaseCount(Money purchaseMoney) {
        return purchaseMoney.getMoney() / LottoTicket.PRICE;
    }

    public int calculatePurchaseAutoCount(Money purchaseMoney, int purchaseManualCount) {
        return calculateTotalPurchaseCount(purchaseMoney) - purchaseManualCount;
    }

    public Money getPurchaseMoney() {
        return purchaseMoney;
    }

    public int getPurchaseManualCount() {
        return purchaseManualCount;
    }

    public int getPurchaseAutoCount() {
        return purchaseAutoCount;
    }
}
