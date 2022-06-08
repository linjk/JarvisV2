from django.urls import path
from rest_framework.routers import DefaultRouter
from test_platform.views import SystemInfoViewSet, ApiInfoViewSet

router = DefaultRouter()

# http://127.0.0.1:8000/api/testplatform/v1/systeminfos
router.register('systeminfos', SystemInfoViewSet, basename='systeminfos')
router.register('apiinfos', ApiInfoViewSet, basename='apiinfos')

urlpatterns = [

]

urlpatterns += router.urls
