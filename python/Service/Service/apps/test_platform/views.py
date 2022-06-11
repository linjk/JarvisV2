from django.shortcuts import render
from rest_framework.viewsets import ModelViewSet
from test_platform.models import SystemInfo, ApiInfo
from test_platform.serializer import SystemInfoSerializer, ApiInfoSerializer
from test_platform.filter import SystemInfoFilter, ApiInfoFilter
from test_platform.paginations import CustomPageNumberPagination


class SystemInfoViewSet(ModelViewSet):
    """
        create:
        注册新系统
    """
    queryset = SystemInfo.objects.all()
    serializer_class = SystemInfoSerializer
    # 过滤
    filter_class = SystemInfoFilter
    # 搜索
    search_fields = ("sys_code", "sys_name")
    # 分页
    pagination_class = CustomPageNumberPagination


class ApiInfoViewSet(ModelViewSet):
    queryset = ApiInfo.objects.all()
    serializer_class = ApiInfoSerializer
    filter_class = ApiInfoFilter
