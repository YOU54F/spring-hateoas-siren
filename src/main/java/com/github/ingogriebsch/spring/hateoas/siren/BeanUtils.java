package com.github.ingogriebsch.spring.hateoas.siren;

import static java.util.Optional.ofNullable;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.beans.BeanUtils.getPropertyDescriptor;
import static org.springframework.beans.BeanUtils.instantiateClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.ReflectionUtils;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = PRIVATE)
class BeanUtils {

    static <T> T instantiate(@NonNull Class<T> clazz, @NonNull Class<?>[] types, @NonNull Object[] args) {
        try {
            return instantiateClass(clazz.getDeclaredConstructor(types), args);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    static Map<String, Object> extractProperties(@NonNull Object object, @NonNull String... excludes) {
        Map<String, Object> properties = new ObjectMapper().convertValue(object, new TypeReference<Map<String, Object>>() {
        });

        for (String exclude : excludes) {
            properties.remove(exclude);
        }

        return properties;
    }

    static <T> T applyProperties(@NonNull T obj, @NonNull Map<String, Object> properties) {
        properties.forEach((key, value) -> {
            ofNullable(getPropertyDescriptor(obj.getClass(), key)).ifPresent(property -> {
                try {
                    Method writeMethod = property.getWriteMethod();
                    ReflectionUtils.makeAccessible(writeMethod);
                    writeMethod.invoke(obj, value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        return obj;
    }

}
