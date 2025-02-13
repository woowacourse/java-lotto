package lotto.dto.request;

import lotto.dto.ConvertUtil;

public record PaymentRequest(
        int payment
) {
    public static PaymentRequest from(String input) {
        return new PaymentRequest(ConvertUtil.convertToNumber(input));
    }
}
