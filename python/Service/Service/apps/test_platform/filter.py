from django_filters import FilterSet, filters
from test_platform.models import SystemInfo, ApiInfo


class SystemInfoFilter(FilterSet):
    # 支持模糊搜索配置
    sys_name = filters.CharFilter(field_name='sys_name', lookup_expr='icontains')
    class Meta:
        model = SystemInfo
        fields = ("sys_name",)


class ApiInfoFilter(FilterSet):
    class Meta:
        model = ApiInfo
        fields = ("api_uri", "api_method")
