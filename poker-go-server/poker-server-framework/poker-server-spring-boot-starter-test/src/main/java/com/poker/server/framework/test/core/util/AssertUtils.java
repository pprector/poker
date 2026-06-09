package com.poker.server.framework.test.core.util;

import com.poker.server.framework.common.exception.ErrorCode;
import com.poker.server.framework.common.exception.ServiceException;
import org.junit.jupiter.api.Assertions;

import java.util.function.Supplier;

public class AssertUtils {

    public static void assertServiceException(ThrowableRunnable runnable, ErrorCode errorCode) {
        assertServiceException(() -> {
            try {
                runnable.run();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            return null;
        }, errorCode);
    }

    public static void assertServiceException(Supplier<?> supplier, ErrorCode errorCode) {
        try {
            supplier.get();
            Assertions.fail("Expected ServiceException but no exception was thrown");
        } catch (ServiceException e) {
            Assertions.assertEquals(errorCode.getCode(), e.getCode());
            Assertions.assertEquals(errorCode.getMsg(), e.getMessage());
        }
    }

    @FunctionalInterface
    public interface ThrowableRunnable {
        void run() throws Throwable;
    }

}
