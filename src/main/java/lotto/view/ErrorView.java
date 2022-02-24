package lotto.view;

import lotto.client.ErrorClient;

public class ErrorView {

    private static final String PREFIX = "[ERROR] ";

    private final ErrorClient errorClient;

    public ErrorView(ErrorClient errorClient) {
        this.errorClient = errorClient;
    }

    public void error(String message) {
        errorClient.error(PREFIX + message + "\n");
    }
}
