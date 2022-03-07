package lotto.domain;

import lotto.exception.LottoPurchaseMoneyException;
import lotto.util.InputConvertor;

public class LottoPurchaseMoney {

    private static final int LOTTO_TICKET_PRICE = 1000;

    private final int lottoPurchaseMoney;

    public LottoPurchaseMoney(int input) {
        checkNotPositive(input);
        checkUnit(input);
        this.lottoPurchaseMoney = input;
    }

    public static LottoPurchaseMoney of(String input) {
        return new LottoPurchaseMoney(InputConvertor.toInt(input));
    }

    private void checkNotPositive(int input) {
        if (input <= 0) {
            throw new LottoPurchaseMoneyException(LottoPurchaseMoneyException.INPUT_NOT_POSITIVE_NUMBER_ERROR_MESSAGE);
        }
    }

    private void checkUnit(int input) {
        if (!isCorrectUnit(input)) {
            throw new LottoPurchaseMoneyException(LottoPurchaseMoneyException.MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private boolean isCorrectUnit(int input) {
        return input % LOTTO_TICKET_PRICE == 0;
    }

    public int calculateTotalLottoCount() {
        return lottoPurchaseMoney / LOTTO_TICKET_PRICE;
    }

    public double calculateProfitRate(long totalPrize) {
        double profitRate = (double) totalPrize / (double) lottoPurchaseMoney;
        return Math.floor(profitRate * 100) / 100.0;
    }
}
