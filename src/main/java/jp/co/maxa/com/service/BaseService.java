package jp.co.maxa.com.service;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.maxa.com.context.DefaultRepository;

public class BaseService {
	@Autowired
	public DefaultRepository repository;

	protected DefaultRepository repository() {
        return repository;
    }
}
