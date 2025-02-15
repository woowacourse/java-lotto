package util;

import java.util.function.Supplier;

public class RetryHandler {
    public static <T> Object retryUntilSuccessWithReturn(Supplier<T> supplier) {
        ExecuteResult rr;
        do {
            rr = tryGivenMethod(supplier);
        } while (rr.isSuccess());
        return rr.getResult();
    }

    private static <T> ExecuteResult tryGivenMethod(Supplier<T> supplier) {
        try {
            return new ExecuteResult(supplier.get(), true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return new ExecuteResult(null, false);
    }

    static class ExecuteResult {
        private final Object result;
        private final boolean isSuccess;

        public ExecuteResult(Object result, boolean isSuccess) {
            this.result = result;
            this.isSuccess = isSuccess;
        }

        public Object getResult() {
            return result;
        }

        public boolean isSuccess() {
            return isSuccess;
        }
    }
}
