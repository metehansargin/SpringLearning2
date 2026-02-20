package com.metehansargin.springlearn2.mapper;

import com.metehansargin.springlearn2.exception.BaseException;
import com.metehansargin.springlearn2.exception.ErrorMesage;
import com.metehansargin.springlearn2.exception.MessageType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class BaseMapper {

    public <D, E> E toEntity(D dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            copy(dto, entity, false);
            return entity;
        } catch (Exception e) {
            throw new BaseException(new ErrorMesage(MessageType.MAPPER_EXIST,e.getMessage()));
        }
    }

    public <E, D> D toDto(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            copy(entity, dto, false);
            return dto;
        } catch (Exception e) {
            throw new BaseException(new ErrorMesage(MessageType.MAPPER_EXIST,e.getMessage()));
        }
    }

    public <D, E> void update(D dto, E entity) {
        copy(dto, entity, true); // id'yi değiştirme
    }

    private void copy(Object source, Object target, boolean skipId) {
        Field[] sourceFields = source.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            try {
                sourceField.setAccessible(true);
                Object value = sourceField.get(source);

                if (value == null) continue;
                if (skipId && sourceField.getName().equals("id")) continue;

                Field targetField = target.getClass()
                        .getDeclaredField(sourceField.getName());

                targetField.setAccessible(true);

                // Basit tip mi?
                if (isSimple(sourceField.getType())) {
                    targetField.set(target, value);
                } else {
                    // Nested object (Department gibi)
                    Object nested = targetField.getType()
                            .getDeclaredConstructor()
                            .newInstance();

                    copy(value, nested, false);
                    targetField.set(target, nested);
                }

            } catch (NoSuchFieldException ignored) {
                // DTO'da olup entity'de olmayan field varsa geç
            } catch (Exception e) {
               throw new BaseException(new ErrorMesage(MessageType.MAPPER_EXIST,e.getMessage()));
            }
        }
    }

    private boolean isSimple(Class<?> type) {
        return type.equals(String.class)
                || type.equals(Long.class)
                || type.equals(Integer.class)
                || type.equals(Boolean.class);
    }
}
