# payu-bank

## Instalación base de datos

La base de datos se puede crear con los siguientes pasos (dado que se tenga instalado postgres) en la consola de postgres:

```bash
postgres=# CREATE DATABASE "payubank";
CREATE DATABASE
postgres=# \c payubank
You are now connected to database "payubank" as user "postgres".
postgres=# create user payu with password "payu2015";
CREATE ROLE
postgres=# GRANT ALL PRIVILEGES ON DATABASE "payubank" to payu;
GRANT
```

Para crear el esquema no es necesario ejecutar ningún comando, spring se encarga de esto.

## Modelo entidad-relación

![Diagrama entidad-relación](http://i.imgur.com/kCRthaq.png)
