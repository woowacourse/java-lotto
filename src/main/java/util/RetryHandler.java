package util;

import java.util.function.Supplier;

public class RetryHandler {
    public static <T> T retryUntilSuccessWithReturn(Supplier<T> supplier) {
        ExecuteResult er;
        do {
            er = executeGivenMethod(supplier);
        } while (!er.isSuccess());
        return (T) er.result();
    }

    private static <T> ExecuteResult executeGivenMethod(Supplier<T> supplier) {
        try {
            return new ExecuteResult(supplier.get(), true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ExecuteResult(null, false);
    }

    record ExecuteResult(Object result, boolean isSuccess) {
    }
}
