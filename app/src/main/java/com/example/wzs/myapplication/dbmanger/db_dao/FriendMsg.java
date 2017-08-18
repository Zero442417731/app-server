package com.example.wzs.myapplication.dbmanger.db_dao;



import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by hxcs-02 on 2017/8/18.
 */
@Entity
public class FriendMsg {
    @Id(autoincrement = true)
    private Long id;
    private String friendUserId;
    private String token;
    private String drawingId;
    private String order;
    private String command;
    private String paintSize;
    private String paintColor;
    @ToMany(referencedJoinProperty = "Ttime")
    private List<DrawingDataBean> drawingData;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 205297265)
    private transient FriendMsgDao myDao;
    @Generated(hash = 2126754407)
    public FriendMsg(Long id, String friendUserId, String token, String drawingId,
            String order, String command, String paintSize, String paintColor) {
        this.id = id;
        this.friendUserId = friendUserId;
        this.token = token;
        this.drawingId = drawingId;
        this.order = order;
        this.command = command;
        this.paintSize = paintSize;
        this.paintColor = paintColor;
    }
    @Generated(hash = 407870943)
    public FriendMsg() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFriendUserId() {
        return this.friendUserId;
    }
    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getDrawingId() {
        return this.drawingId;
    }
    public void setDrawingId(String drawingId) {
        this.drawingId = drawingId;
    }
    public String getOrder() {
        return this.order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getCommand() {
        return this.command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getPaintSize() {
        return this.paintSize;
    }
    public void setPaintSize(String paintSize) {
        this.paintSize = paintSize;
    }
    public String getPaintColor() {
        return this.paintColor;
    }
    public void setPaintColor(String paintColor) {
        this.paintColor = paintColor;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1211746462)
    public List<DrawingDataBean> getDrawingData() {
        if (drawingData == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DrawingDataBeanDao targetDao = daoSession.getDrawingDataBeanDao();
            List<DrawingDataBean> drawingDataNew = targetDao
                    ._queryFriendMsg_DrawingData(id);
            synchronized (this) {
                if (drawingData == null) {
                    drawingData = drawingDataNew;
                }
            }
        }
        return drawingData;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1534831071)
    public synchronized void resetDrawingData() {
        drawingData = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 575850349)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFriendMsgDao() : null;
    }



}
