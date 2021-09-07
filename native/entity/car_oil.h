//
// Created by linjk on 2020/12/26.
// 车辆加油记录实体

#ifndef JARVIS_CAR_OIL_H
#define JARVIS_CAR_OIL_H

#include "../commons/jarvis.h"

class car_oil {
public:
    car_oil(): oilDate(date(day_clock::local_day())) {}

    car_oil& operator += (const car_oil& co);

    void setOilSummary(double summary);
    double getOilSummary() const ;

    void setOilDate(date oil_date);
    date getOilDate() const;

    void setOilCnt(float cnt);
    float getOilCnt() const;
private:
    // 加油量
    float oilCnt{};
    // 加油金额
    double oilSummary{};
    // 加油日期
    date oilDate;
    // 距离上次加油距离天数
    days days;
    // 是否加满
    bool is_full{};

    friend car_oil& __doapl(car_oil* ths, const car_oil& co) {
        ths->oilSummary += co.oilSummary;
        ths->oilCnt += co.oilCnt;

        return *ths;
    }
};

inline car_oil& car_oil::operator += (const car_oil& co) {
    return __doapl(this, co);
}

void car_oil::setOilSummary(double summary) {
    this->oilSummary = summary;
}

void car_oil::setOilDate(date oil_date) {
    this->oilDate = oil_date;
}

date car_oil::getOilDate() const {
    return this->oilDate;
}

void car_oil::setOilCnt(float cnt) {
    this->oilCnt = cnt;
}

float car_oil::getOilCnt() const {
    return this->oilCnt;
}

double car_oil::getOilSummary() const {
    return this->oilSummary;
}


#endif //JARVIS_CAR_OIL_H
