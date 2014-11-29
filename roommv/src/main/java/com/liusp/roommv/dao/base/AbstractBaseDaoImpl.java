package com.liusp.roommv.dao.base;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.liusp.roommv.vo.Page;

public abstract class AbstractBaseDaoImpl extends HibernateDaoSupport implements
		IBaseDao {
	private static final Logger logger = Logger
			.getLogger(AbstractBaseDaoImpl.class);

	@Resource(name = "sessionFactory")
	public void setSessionFactoryLocal(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public List<Object> queryAllObjectList(Class className,
			String... filedNames) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(64);
		sb.append("select ");
		int i = 0;
		int length = 0;
		if (filedNames == null || (length = filedNames.length) == 0) {
			sb.append("o").append(" ");
		} else {
			for (String filedName : filedNames) {
				if (i == length) {
					sb.append("o.").append(filedName).append(" ");
				} else {
					sb.append("o.").append(filedName).append(",");
				}
				i++;
			}
		}
		sb.append("from ").append(className.getSimpleName()).append(" o");
		logger.info(sb.toString());
		List<Object> list = this.getHibernateTemplate().find(sb.toString());
		return list;
	}

	public List<Object> queryObjectListByPage(Class className, final Page page,
			String... filedNames) {
		// TODO Auto-generated method stub
		final StringBuilder sb = new StringBuilder(64);
		sb.append("select ");
		int i = 0;
		int length = 0;
		if (filedNames == null || (length = filedNames.length) == 0) {
			sb.append("o").append(" ");
		} else {
			for (String filedName : filedNames) {
				if (i == length) {
					sb.append("o.").append(filedName).append(" ");
				} else {
					sb.append("o.").append(filedName).append(",");
				}
				i++;
			}
		}
		sb.append("from ").append(className.getSimpleName()).append(" o");
		logger.info(sb.toString());
		List<Object> list = this.getHibernateTemplate().executeFind(
				new HibernateCallback<List<Object>>() {

					public List<Object> doInHibernate(Session session)
							throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(sb.toString());
				query.setFirstResult(page.getStart());
						query.setMaxResults(page.getCount());
				return query.list();
			}
				});

		return list;
	}
}
