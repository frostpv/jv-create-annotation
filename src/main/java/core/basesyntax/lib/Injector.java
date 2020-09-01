package core.basesyntax.lib;

import core.basesyntax.dao.BetDaoImpl;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Injector {

    public static Object getInstance(Class clazz) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        Object instance = declaredConstructor.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {

            if (field.getAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                field.set(instance, new BetDaoImpl());
            }
        }
        return instance;
    }
}
