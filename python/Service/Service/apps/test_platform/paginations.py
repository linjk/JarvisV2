from rest_framework.pagination import PageNumberPagination


class CustomPageNumberPagination(PageNumberPagination):
    page_size = 100
    page_query_param = 'pageNo'
    page_size_query_param = 'pageSize'
    max_page_size = 500
