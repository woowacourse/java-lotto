package lotto.domain.customer;

import lotto.util.NullOrEmptyValidator;

import java.util.List;

public class Customer {
    public static final int NO_MANUAL_TICKET = 0;
    private static final String MESSAGE_FOR_UNMATCHED_SIZE = "입력받은 수동 로또 번호 수(%d개) 구매하려는 수(%d개)와 일치하지 않습니다";

    private final PurchaseInfo purchaseInfo;
    private final List<List<Integer>> manualNumbers;

    public Customer(PurchaseInfo purchaseInfo) {
        this(purchaseInfo, null);
    }

    public Customer(PurchaseInfo purchaseInfo, List<List<Integer>> manualNumbers) {
        NullOrEmptyValidator.isNull(purchaseInfo);

        // 수동 로또를 구매하지 않는 경우
        if (purchaseInfo.getNumberOfManualTickets() == NO_MANUAL_TICKET) {
            this.purchaseInfo = purchaseInfo;
            this.manualNumbers = null;
            return;
        }

        NullOrEmptyValidator.isNullOrEmpty(manualNumbers);
        validateSize(purchaseInfo.getNumberOfManualTickets(), manualNumbers.size());
        validateEachManualNumbers(manualNumbers);
        this.purchaseInfo = purchaseInfo;
        this.manualNumbers = manualNumbers;
    }

    private void validateEachManualNumbers(List<List<Integer>> manualNumbers) {
        for (List<Integer> numbers : manualNumbers) {
            NullOrEmptyValidator.isNullOrEmpty(numbers);
        }
    }

    private void validateSize(int numberOfTickets, int sizeOfManualNumbers) {
        if (numberOfTickets != sizeOfManualNumbers) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_UNMATCHED_SIZE, sizeOfManualNumbers, numberOfTickets));
        }
    }

    public PurchaseInfo getPurchaseInfo() {
        return purchaseInfo;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
