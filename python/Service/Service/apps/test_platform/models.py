from django.db import models

# Create your models here.

# 接口测试管理系统
# 同步各个model同步到数据库的步骤：
#   1. python manage.py makemigrations
#   2. python manage.py migrate


# 注册系统信息
class SystemInfo(models.Model):
    sys_code = models.CharField(verbose_name='使用系统编码', max_length=32, unique=True, null=False, blank=False)
    sys_name = models.CharField(verbose_name='使用系统名称', max_length=32, unique=False, null=False, blank=False)
    create_time = models.DateTimeField(verbose_name='注册时间', default=True)

    class Meta:
        managed = True
        app_label = 'test_platform'
        db_table = 'system_info'

    def __str__(self):
        return f'{self.sys_code} - {self.sys_name}'


# 系统关联API信息
class ApiInfo(models.Model):
    api_method = models.CharField(verbose_name='API请求方式', max_length=32, null=False, blank=False, unique=False)
    api_uri = models.CharField(verbose_name='API请求URI', max_length=256, null=False, blank=False, unique=False)
    enable = models.IntegerField(verbose_name='是否启用', default=1, null=False)
    create_time = models.DateTimeField(verbose_name='创建时间', default=True)
    remark = models.CharField(verbose_name='备注', max_length=256, null=True, default=None, blank=True)

    system = models.ForeignKey(verbose_name="关联系统", to=SystemInfo, on_delete=models.PROTECT)

    class Meta:
        managed = True
        app_label = 'test_platform'
        db_table = 'api_info'

    def __str__(self):
        return f'{self.system} - {self.api_method} - {self.api_uri} - {self.create_time} - {self.remark}'
