package by.academy.it.dao.impl;

import by.academy.it.dao.UserDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance = null;

    private UserDaoImpl() {
    }

    public static synchronized UserDaoImpl getInstance() {
        if (instance == null) instance = new UserDaoImpl();
        return instance;
    }

    public String getPassword(String login) throws DaoException {
        String hql = "SELECT U.password FROM User U WHERE U.login=:login";
        String pass;
        try {
            session = util.getSession();
            query = session.createQuery(hql);
            query.setParameter("login", login);
            pass = (String) query.uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return pass;
    }

    public User getUserByLogin(String login) throws DaoException {
        String hql = "SELECT U FROM User U WHERE U.login=:login";
        User user;
        try {
            session = util.getSession();
            query = session.createQuery(hql);
            LOG.info("Requested login: " + login);
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return user;
    }
}

