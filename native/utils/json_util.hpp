//
// Created by linjk on 12/27/20.
//

#ifndef JARVIS_JSON_UTIL_HPP
#define JARVIS_JSON_UTIL_HPP

#include "../commons/jarvis.h"
#include <boost/property_tree/ptree.hpp>
#include <boost/property_tree/json_parser.hpp>

using namespace boost::property_tree;

class json_util {
public:
    static string read_config(const string &node_name, const string& key_name);
};

string json_util::read_config(const string &node_name, const string& key_name) {
    const string file_path = "/Users/linjk/Documents/code/java/Jarvis/native/config/jarvis.json";

    string result;
    ptree root;
    ptree items;
    read_json<ptree>(file_path,root);

    items = root.get_child(node_name);
    for(ptree::iterator it = items.begin(); it != items.end(); ++it) {
        string key = it->first;
        if (key == key_name) {
            result = it->second.get_value(key);
        }
    }

    return result;
}

TEST(json_util, json_util_test) { /* NOLINT */
    cout << "Test: json_util_test start" << endl;
    json_util jsonUtil;
    cout << "host: " << jsonUtil.read_config("database", "host") << endl;

    cout << "Test: json_util_test end" << endl;
    SUCCEED();
}


#endif //JARVIS_JSON_UTIL_HPP
