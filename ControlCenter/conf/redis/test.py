from redis import Redis


class cache:
    def __init__(self, client):
        self.client = client

    def set(self, key, value):
        self.client.set(key, value)

    def get(self, key):
        return self.client.get(key)

    def update(self, key, new_value):
        """
            对键key存储的缓存数据进行更新，并返回key在被更新之前存储的缓存数据，如之前不存在数据返回None
        """
        return self.client.getset(key, new_value)

def check_connection():
    # 使用二进制编码方式打开客户端，当保存图片时需使用此方式
    #client = Redis()
    # 使用文本编码方式打开客户端
    client = Redis(decode_responses=True)

    if client.ping() is True:
        print("connected to redis")
    else:
        print("failed connect to redis")


if __name__ == "__main__":
    check_connection()
