package lotto.service;

import lotto.domain.LottoBuyingMoney;

import static lotto.service.ServiceUtils.parseInt;

public class LottoBuyingMoneyService {
    public static int validateLottoBuyingMoney(String lottoBuyingMoney) {
        new LottoBuyingMoney(parseInt(lottoBuyingMoney));
        return parseInt(lottoBuyingMoney);
    }
}
