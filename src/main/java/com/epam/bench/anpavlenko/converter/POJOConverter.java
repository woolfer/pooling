package com.epam.bench.anpavlenko.converter;

import java.util.Collection;

/**
 * @author an.pavlenko
 */
public interface POJOConverter<T, U> {

  Collection<T> convert(Collection<U> pojos);

  T convertPOJO(U pojo);
}
