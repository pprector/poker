package com.poker.server.framework.test.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class RandomUtils {

    private static final PodamFactory PODAM_FACTORY = new PodamFactoryImpl();

    static {
        PODAM_FACTORY.getStrategy().setDefaultNumberOfCollectionElements(1);
    }

    public static <T> T randomPojo(Class<T> clazz) {
        return PODAM_FACTORY.manufacturePojo(clazz);
    }

    @SafeVarargs
    public static <T> T randomPojo(Class<T> clazz, Consumer<T>... consumers) {
        T pojo = randomPojo(clazz);
        if (ArrayUtil.isNotEmpty(consumers)) {
            for (Consumer<T> consumer : consumers) {
                consumer.accept(pojo);
            }
        }
        return pojo;
    }

    public static Long randomLongId() {
        return RandomUtil.randomLong(1_000_000_000, 9_999_999_999L);
    }

    public static Integer randomInteger() {
        return RandomUtil.randomInt(1, 1000);
    }

    public static String randomString() {
        return RandomUtil.randomString(20);
    }

    public static <T> T cloneIgnoreId(T object, Consumer<T>... consumers) {
        T clone = BeanUtil.toBean(object, (Class<T>) object.getClass());
        if (ArrayUtil.isNotEmpty(consumers)) {
            for (Consumer<T> consumer : consumers) {
                consumer.accept(clone);
            }
        }
        return clone;
    }

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

}
