package cn.linjk.jarvis.common.mybatis.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @author linjk
 */
@Table(name = "device_info")
public class DeviceInfo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user")
    private String updateUser;

    /**
     * 数据有效性, 0-无效,1-有效
     */
    private Short valid;

    /**
     * 数据是否启用, 0-否,1-是
     */
    @Column(name = "enable_flag")
    private Short enableFlag;

    /**
     * 设备登录账户
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 设备别名
     */
    @Column(name = "alias_name")
    private String aliasName;

    /**
     * 设备登录密码
     */
    @Column(name = "secret_key")
    private String secretKey;

    /**
     * 设备远程呼叫手机
     */
    private String phone;

    /**
     * 设备头像
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    private String roles;

    private String remark;

    private String verifyCode;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return update_user
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取数据有效性, 0-无效,1-有效
     *
     * @return valid - 数据有效性, 0-无效,1-有效
     */
    public Short getValid() {
        return valid;
    }

    /**
     * 设置数据有效性, 0-无效,1-有效
     *
     * @param valid 数据有效性, 0-无效,1-有效
     */
    public void setValid(Short valid) {
        this.valid = valid;
    }

    /**
     * 获取数据是否启用, 0-否,1-是
     *
     * @return enable_flag - 数据是否启用, 0-否,1-是
     */
    public Short getEnableFlag() {
        return enableFlag;
    }

    /**
     * 设置数据是否启用, 0-否,1-是
     *
     * @param enableFlag 数据是否启用, 0-否,1-是
     */
    public void setEnableFlag(Short enableFlag) {
        this.enableFlag = enableFlag;
    }

    /**
     * 获取设备登录账户
     *
     * @return device_name - 设备登录账户
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置设备登录账户
     *
     * @param deviceName 设备登录账户
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 获取设备别名
     *
     * @return alias_name - 设备别名
     */
    public String getAliasName() {
        return aliasName;
    }

    /**
     * 设置设备别名
     *
     * @param aliasName 设备别名
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    /**
     * 获取设备登录密码
     *
     * @return secret_key - 设备登录密码
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * 设置设备登录密码
     *
     * @param secretKey 设备登录密码
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * 获取设备远程呼叫手机
     *
     * @return phone - 设备远程呼叫手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置设备远程呼叫手机
     *
     * @param phone 设备远程呼叫手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取设备头像
     *
     * @return avatar_url - 设备头像
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置设备头像
     *
     * @param avatarUrl 设备头像
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * @param roles
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}