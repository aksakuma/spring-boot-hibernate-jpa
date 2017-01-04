package jp.co.maxa.com.context;

import java.io.Serializable;

/**
 * Entityの基底クラス
 */
public class BaseEntity<T extends Entity> implements Serializable, Entity{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public T save(AbstractRepository rep){
		return (T) rep.regist(this);
	}

	@SuppressWarnings("unchecked")
	public T update(AbstractRepository rep){
		return (T) rep.update(this);
	}

	@SuppressWarnings("unchecked")
	public T delete(AbstractRepository rep){
		return (T) rep.delete(this);
	}

}