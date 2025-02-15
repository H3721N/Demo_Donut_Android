# Aplicación Demo de Donuts

## Descripción

esta es una aplicación demo para android que permite a los usuarios iniciar sesión y ver una lista de donuts. la aplicación admite la creación de usuarios temporales en una base de datos local y muestra los datos de los donuts en un `RecyclerView`.

## Características

- autenticación de usuario.
- creación de usuarios temporales en una base de datos local.
- obtención de datos de donuts desde una api remota.
- visualización de datos en un `RecyclerView`.

## Información del Usuario

hay un usuario preconfigurado disponible para pruebas:

- **usuario:** `hgomez123`
- **contraseña:** `hgomez123`

## API

la aplicación consume datos desde el siguiente endpoint de api:

https://private-d24209-ocisneros.apiary-mock.com/donuts

## 🛠 Tecnologías Utilizadas

- kotlin/java para el desarrollo en android.
- `recyclerview` para mostrar la lista de donuts.
- base de datos local para almacenamiento temporal de usuarios.
- retrofit para consumo de api.

## Instalación

1. clonar el repositorio:
   ```bash
   git clone https://github.com/H3721N/Demo_Donut_Android.git

## Testing APK
incluye el archivo apk [loginDemo.apk](loginDemo.apk) para probar la aplicación en un dispositivo android.