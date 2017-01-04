package jp.co.maxa.com.context;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class OrmTemplate {

	private HibernateTemplate tmpl;

	public OrmTemplate(SessionFactory sessionFactory) {
        tmpl = new HibernateTemplate(sessionFactory);
    }

    public HibernateTemplate ht() {
        return tmpl;
    }

}