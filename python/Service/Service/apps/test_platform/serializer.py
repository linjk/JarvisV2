from rest_framework import serializers
from test_platform.models import SystemInfo, ApiInfo


class SystemInfoSerializer(serializers.ModelSerializer):
    class Meta:
        model = SystemInfo
        fields = "__all__"


class ApiInfoSerializer(serializers.ModelSerializer):
    class Meta:
        model = ApiInfo
        fields = "__all__"
