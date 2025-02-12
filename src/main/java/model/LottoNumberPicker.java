package model;

import java.util.concurrent.ThreadLocalRandom;

public class LottoNumberPicker {

    public Number pickRandomNumber() {
        return new Number(ThreadLocalRandom.current().nextInt(1, 46));
    }
}
