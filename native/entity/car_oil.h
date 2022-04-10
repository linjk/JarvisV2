//
// Created by linjk on 2020/12/26.
// 车辆加油记录实体

#ifndef JARVIS_CAR_OIL_H
#define JARVIS_CAR_OIL_H

#include "../commons/jarvis.h"

class car_oil {
public:
    car_oil(): oilDate(date(day_clock::local_day())) {}

    friend ostream& operator << (ostream& os, const car_oil& co) {
        os << "car_oil: " << co.getOilDate() << endl;
        return os;
    }

    car_oil& operator += (const car_oil& co) {
        return __doapl(this, co);
    }

    operator double() const {
        return (double)this->oilSummary;
    }

    long getId() const {
        return this->id;
    }
    void setId(const long _id) {
        this->id = _id;
    }

    void setOilDate(const date& oil_date) {
        this->oilDate = oil_date;
    }
    const date& getOilDate() const {
        return this->oilDate;
    }

    void setOilType(const string& _oilType) {
        this->oilType = _oilType;
    }
    const string& getOilType() const {
        return this->oilType;
    }

    void setOilTarget(const string& _oilTarget) {
        this->oilTarget = _oilTarget;
    }
    const string& getOilTarget() const {
        return this->oilTarget;
    }

    void setOilCnt(float cnt) {
        this->oilCnt = cnt;
    }
    float getOilCnt() const {
        return this->oilCnt;
    }

    void setOilSummary(double summary) {
        this->oilSummary = summary;
    }
    double getOilSummary() const {
        return this->oilSummary;
    }

    void setOilPrice(double _price) {
        this->oilPrice = _price;
    }
    double getOilPrice() const {
        return this->oilPrice;
    }

    long getCurDistance() const {
        return this->curDistance;
    }
    void setCurDistance(long _curDistance) {
        this->curDistance = _curDistance;
    }

    int getDeltaDistance() const {
        return this->deltaDistance;
    }
    void setDeltaDistance(const int _deltaDistance) {
        this->deltaDistance = _deltaDistance;
    }

    float getOilCost() const {
        return this->oilCost;
    }
    void setOilCost(const float _oilCost) {
        this->oilCost = _oilCost;
    }

    float getPricePerKm() const {
        return this->pricePerKm;
    }
    void setPricePerKm(const float _pricePerKm) {
        this->pricePerKm = _pricePerKm;
    }

    bool isFull() const {
        return this->full;
    }
    void setFull(bool _full) {
        this->full = _full;
    }

    const string& getCreateUserCode() const {
        return this->createUserCode;
    }
    void setCreateUserCode(const string& _createUserCode) {
        this->createUserCode = _createUserCode;
    }

    const date& getCreateTime() const {
        return this->createTime;
    }
    void setCreateTime(const date& _createTime) {
        this->createTime = _createTime;
    }

    const string& getUpdateUserCode() const {
        return this->updateUserCode;
    }
    void setUpdateUserCode(const string& _updateUserCode) {
        this->updateUserCode = _updateUserCode;
    }

    const date& getUpdateTime() const {
        return this->updateTime;
    }
    void setUpdateTime(const date& _updateTime) {
        this->updateTime = _updateTime;
    }

    int getDeleteFlag() const {
        return this->deleteFlag;
    }
    void setDeleteFlag(int _deleteFlag) {
        this->deleteFlag = _deleteFlag;
    }

    int getVersion() const {
        return this->version;
    }
    void setVersion(const int _version) {
        this->version = _version;
    }

    const string & getRemark() const {
        return this->remark;
    }
    void setRemark(const string& _remark) {
        this->remark = _remark;
    }

    const gregorian::days& getDays() const {
        return this->days;
    }
    void setDays(const gregorian::days& _days) {
        this->days = _days;
    }

private:
    // 数据库主键
    long id{};
    // 加油日期
    date oilDate;
    // 加油类型
    string oilType;
    // 加油目标
    string oilTarget;
    // 加油量
    float oilCnt{};
    // 加油金额
    double oilSummary{};
    // 加油费用
    double oilPrice;
    // 当前行驶距离
    long curDistance;
    // 相比上次，本次行驶的距离
    int deltaDistance;
    // 油耗
    float oilCost;
    // 每公里油耗
    float pricePerKm;
    // 是否加满
    bool full{};
    // 记录创建人
    string createUserCode;
    // 记录创建时间
    date createTime;
    // 记录更新人
    string updateUserCode;
    // 记录更新时间
    date updateTime;
    // 删除标记
    int deleteFlag;
    // 数据版本号
    int version;
    // 备注
    string remark;

    // 【非数据库字段】距离上次加油距离天数
    days days;

    friend car_oil& __doapl(car_oil* ths, const car_oil& co) {
        ths->oilSummary += co.oilSummary;
        ths->oilCnt += co.oilCnt;

        return *ths;
    }
};

#endif //JARVIS_CAR_OIL_H
