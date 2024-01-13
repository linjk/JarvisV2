////
//// Created by linjk on 12/27/20.
////
//
//#ifndef JARVIS_MQTT_UTIL_HPP
//#define JARVIS_MQTT_UTIL_HPP
//
//#include "../commons/jarvis.h"
//#include "MQTTClient.h"
//
//#define ADDRESS     "tcp://localhost:1883"
//#define CLIENTID    "mqttx_531e9c9c"
//#define TOPIC       "testtopic/1"
//#define PAYLOAD     "Hello World!"
//#define QOS         1
//#define TIMEOUT     10000L
//
//class mqtt_util {
//public:
//    static void test();
//};
//
//void mqtt_util::test() {
//    MQTTClient client;
//    MQTTClient_connectOptions conn_opts = MQTTClient_connectOptions_initializer;
//    MQTTClient_message pubmsg = MQTTClient_message_initializer;
//    MQTTClient_deliveryToken token;
//    int rc;
//
//    MQTTClient_create(&client, ADDRESS, CLIENTID,
//                      MQTTCLIENT_PERSISTENCE_NONE, nullptr);
//
//    // MQTT 连接参数
//    conn_opts.keepAliveInterval = 20;
//    conn_opts.cleansession = 1;
//
//    if ((rc = MQTTClient_connect(client, &conn_opts)) != MQTTCLIENT_SUCCESS) {
//        printf("Failed to connect, return code %d\n", rc);
//        exit(-1);
//    }
//
//    // 发布消息
//    pubmsg.payload = (void *) PAYLOAD;
//    pubmsg.payloadlen = strlen(PAYLOAD);
//    pubmsg.qos = QOS;
//    pubmsg.retained = 0;
//    MQTTClient_publishMessage(client, TOPIC, &pubmsg, &token);
//    printf("Waiting for up to %d seconds for publication of %s\n"
//           "on topic %s for client with ClientID: %s\n",
//           (int)(TIMEOUT/1000), PAYLOAD, TOPIC, CLIENTID);
//    rc = MQTTClient_waitForCompletion(client, token, TIMEOUT);
//    printf("Message with delivery token %d delivered\n", token);
//
//    // 断开连接
//    MQTTClient_disconnect(client, 10000);
//    MQTTClient_destroy(&client);
//
//    cout << "result: " << rc << endl;
//}
//
//TEST(mqtt_util, mqtt_util_test) { /* NOLINT */
//    mqtt_util mqttUtil;
//
//    mqttUtil.test();
//
//    SUCCEED();
//}
//
//
//#endif //JARVIS_MQTT_UTIL_HPP
