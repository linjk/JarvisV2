//
// Created by linjk on 2020/11/24.

#ifndef JARVIS_CAR_OIL_UTIL_HPP
#define JARVIS_CAR_OIL_UTIL_HPP

#include "../commons/jarvis.h"
#include "../entity/car_oil.h"
#include "mysql_connector.hpp"

class car_oil_util {
public:
    explicit car_oil_util() {
        connectMySQL();
    }

    vector<car_oil> getAll();

    ~car_oil_util() {
        sql_connector.disconnect();
    }
private:
    mysql_connector sql_connector;

    void connectMySQL();
};

vector<car_oil> car_oil_util::getAll() {
    vector<car_oil> oil_records;
    try {
        vector<Row> results = sql_connector.query("select * from car_oil limit 3");
        for (auto & result : results) {
            car_oil oilRecord;
            oilRecord.setOilSummary(result["oil_summary"]);
            oilRecord.setOilDate(from_string(string(result["oil_date"])));
            oil_records.push_back(oilRecord);
        }
    }
    catch (const BadConversion &er){
        cout << "Conversion error: " << er.what() << endl <<
             "\tretrieved data size: " << er.retrieved <<
             ", actual size: " << er.actual_size << endl;
    }
    return oil_records;
}

void car_oil_util::connectMySQL() {
    char *account, *pwd;
    account = getenv("MYSQL_ACCOUNT");
    pwd = getenv("MYSQL_PASSWORD");
    sql_connector = mysql_connector("127.0.0.1", 3308, account, pwd);
    sql_connector.connect();
}

TEST(CarOilRecord, CarOilRecordInit) { /* NOLINT */
    cout << "Test: CarOilRecordInit start" << endl;
    car_oil_util carOilRecord;

    vector<car_oil> r = carOilRecord.getAll();
    for (auto & i : r) {
        cout << "加油日期: " << to_iso_extended_string(i.getOilDate()) << ", 加油金额: " << i.getOilSummary() << endl;
    }
    cout << "Test += operator ..." << endl;
    car_oil c1 = r.at(0);
    car_oil c2 = r.at(1);
    c1 += c2;
    cout << c1.getOilCnt() << ", " << c1.getOilSummary() << endl;

    cout << "Test double operator ..." << endl;
    cout << r.at(2) + 10.2 << endl;
    SUCCEED();
    cout << "Test: CarOilRecordInit end" << endl;
}

#endif //JARVIS_CAR_OIL_UTIL_HPP
