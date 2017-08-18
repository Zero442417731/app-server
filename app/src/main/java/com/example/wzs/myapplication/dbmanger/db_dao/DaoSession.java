package com.example.wzs.myapplication.dbmanger.db_dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.wzs.myapplication.dbmanger.db_dao.FriendMsg;
import com.example.wzs.myapplication.dbmanger.db_dao.DrawingDataBean;
import com.example.wzs.myapplication.dbmanger.db_dao.UserInfoEntity;

import com.example.wzs.myapplication.dbmanger.db_dao.FriendMsgDao;
import com.example.wzs.myapplication.dbmanger.db_dao.DrawingDataBeanDao;
import com.example.wzs.myapplication.dbmanger.db_dao.UserInfoEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig friendMsgDaoConfig;
    private final DaoConfig drawingDataBeanDaoConfig;
    private final DaoConfig userInfoEntityDaoConfig;

    private final FriendMsgDao friendMsgDao;
    private final DrawingDataBeanDao drawingDataBeanDao;
    private final UserInfoEntityDao userInfoEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        friendMsgDaoConfig = daoConfigMap.get(FriendMsgDao.class).clone();
        friendMsgDaoConfig.initIdentityScope(type);

        drawingDataBeanDaoConfig = daoConfigMap.get(DrawingDataBeanDao.class).clone();
        drawingDataBeanDaoConfig.initIdentityScope(type);

        userInfoEntityDaoConfig = daoConfigMap.get(UserInfoEntityDao.class).clone();
        userInfoEntityDaoConfig.initIdentityScope(type);

        friendMsgDao = new FriendMsgDao(friendMsgDaoConfig, this);
        drawingDataBeanDao = new DrawingDataBeanDao(drawingDataBeanDaoConfig, this);
        userInfoEntityDao = new UserInfoEntityDao(userInfoEntityDaoConfig, this);

        registerDao(FriendMsg.class, friendMsgDao);
        registerDao(DrawingDataBean.class, drawingDataBeanDao);
        registerDao(UserInfoEntity.class, userInfoEntityDao);
    }
    
    public void clear() {
        friendMsgDaoConfig.clearIdentityScope();
        drawingDataBeanDaoConfig.clearIdentityScope();
        userInfoEntityDaoConfig.clearIdentityScope();
    }

    public FriendMsgDao getFriendMsgDao() {
        return friendMsgDao;
    }

    public DrawingDataBeanDao getDrawingDataBeanDao() {
        return drawingDataBeanDao;
    }

    public UserInfoEntityDao getUserInfoEntityDao() {
        return userInfoEntityDao;
    }

}
