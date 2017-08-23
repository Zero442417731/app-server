package com.example.wzs.myapplication.dbmanger.db_dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.wzs.myapplication.dbmanger.db_dao.DrawingDataBean;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendMsg;
import com.example.wzs.myapplication.dbmanger.db_dao.UserInfoEntity;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendID;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendsList;

import com.example.wzs.myapplication.dbmanger.db_dao.DrawingDataBeanDao;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendMsgDao;
import com.example.wzs.myapplication.dbmanger.db_dao.UserInfoEntityDao;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendIDDao;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendsListDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig drawingDataBeanDaoConfig;
    private final DaoConfig friendMsgDaoConfig;
    private final DaoConfig userInfoEntityDaoConfig;
    private final DaoConfig friendIDDaoConfig;
    private final DaoConfig friendsListDaoConfig;

    private final DrawingDataBeanDao drawingDataBeanDao;
    private final FriendMsgDao friendMsgDao;
    private final UserInfoEntityDao userInfoEntityDao;
    private final FriendIDDao friendIDDao;
    private final FriendsListDao friendsListDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        drawingDataBeanDaoConfig = daoConfigMap.get(DrawingDataBeanDao.class).clone();
        drawingDataBeanDaoConfig.initIdentityScope(type);

        friendMsgDaoConfig = daoConfigMap.get(FriendMsgDao.class).clone();
        friendMsgDaoConfig.initIdentityScope(type);

        userInfoEntityDaoConfig = daoConfigMap.get(UserInfoEntityDao.class).clone();
        userInfoEntityDaoConfig.initIdentityScope(type);

        friendIDDaoConfig = daoConfigMap.get(FriendIDDao.class).clone();
        friendIDDaoConfig.initIdentityScope(type);

        friendsListDaoConfig = daoConfigMap.get(FriendsListDao.class).clone();
        friendsListDaoConfig.initIdentityScope(type);

        drawingDataBeanDao = new DrawingDataBeanDao(drawingDataBeanDaoConfig, this);
        friendMsgDao = new FriendMsgDao(friendMsgDaoConfig, this);
        userInfoEntityDao = new UserInfoEntityDao(userInfoEntityDaoConfig, this);
        friendIDDao = new FriendIDDao(friendIDDaoConfig, this);
        friendsListDao = new FriendsListDao(friendsListDaoConfig, this);

        registerDao(DrawingDataBean.class, drawingDataBeanDao);
        registerDao(FriendMsg.class, friendMsgDao);
        registerDao(UserInfoEntity.class, userInfoEntityDao);
        registerDao(FriendID.class, friendIDDao);
        registerDao(FriendsList.class, friendsListDao);
    }
    
    public void clear() {
        drawingDataBeanDaoConfig.clearIdentityScope();
        friendMsgDaoConfig.clearIdentityScope();
        userInfoEntityDaoConfig.clearIdentityScope();
        friendIDDaoConfig.clearIdentityScope();
        friendsListDaoConfig.clearIdentityScope();
    }

    public DrawingDataBeanDao getDrawingDataBeanDao() {
        return drawingDataBeanDao;
    }

    public FriendMsgDao getFriendMsgDao() {
        return friendMsgDao;
    }

    public UserInfoEntityDao getUserInfoEntityDao() {
        return userInfoEntityDao;
    }

    public FriendIDDao getFriendIDDao() {
        return friendIDDao;
    }

    public FriendsListDao getFriendsListDao() {
        return friendsListDao;
    }

}
