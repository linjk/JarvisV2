from django.db import models

# Create your models here.

# 设备管理系统
# 如何把下面的model同步到数据库
# 1. python manage.py makemigrations
# 2. python manage.py migrate


class DeviceInfo(models.Model):
    device_name = models.CharField(verbose_name='设备名称', max_length=32, unique=True, null=False, blank=False)

    class Meta:
        managed = True
        app_label = 'devicemanagement'
        db_table = 'device_info'

    def __str__(self):
        return '%s' % self.device_name
