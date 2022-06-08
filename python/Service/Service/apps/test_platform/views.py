from django.shortcuts import render
from rest_framework.viewsets import ModelViewSet
from test_platform.models import SystemInfo, ApiInfo
from test_platform.serializer import SystemInfoSerializer, ApiInfoSerializer


class SystemInfoViewSet(ModelViewSet):
    queryset = SystemInfo.objects.all()
    serializer_class = SystemInfoSerializer


class ApiInfoViewSet(ModelViewSet):
    queryset = ApiInfo.objects.all()
    serializer_class = ApiInfoSerializer
