package lotto.exception.reader;

import lotto.exception.LottoExceptionStatus;

public enum ReaderExceptionStatus implements LottoExceptionStatus {

    READER_CANNOT_READ("입력을 읽어들일 수 없습니다.");

    private final String message;

    ReaderExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
