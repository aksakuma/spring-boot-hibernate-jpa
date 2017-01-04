package jp.co.maxa.com.context;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository {

	<T extends Entity> Optional<T> get(final Class<T> clazz, final Serializable id);

	<T extends Entity> T load(final Class<T> clazz, final Serializable id);

	<T extends Entity> List<T> findAll(Class<T> clazz);

	<T extends Entity> T update(final T clazz);

	<T extends Entity> T regist(final T clazz);

	<T extends Entity> T delete(final T clazz);
}