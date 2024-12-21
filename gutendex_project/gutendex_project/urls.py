from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('books/', include('gutendex.urls')),  # Asegúrate de incluir las URLs de la aplicación 'gutendex'
]

