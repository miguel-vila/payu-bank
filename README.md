# payu-bank

## Ejecutable

[jar compilado](https://www.dropbox.com/s/xq9z3f2r3g8abwj/payu-bank-0.1.0.jar?dl=0)

para ejecutarlo:

```bash
java -jar payu-bank-0.1.0.jar
```

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

Para crear el esquema no es necesario ejecutar ningún comando, spring se encarga de esto cuando se ejecute la aplicación.

## Modelo entidad-relación

![Diagrama entidad-relación](http://i.imgur.com/kCRthaq.png)

## Versiones

|       Nombre      | Versión |
|-----------------|---------|
| JDK     | 1.8   |
| Spring boot    | 1.2.4  |
| Postgres    | 9.3 |

## Empaquetado y ejecución

Para empaquetar a partir del código fuente debe ejecutar:

```bash
mvn package
```

esto generará un jar en la carpeta `target`.

Dicho jar se puede ejecutar usando:

```bash
java -jar target/payu-bank-0.1.0.jar
```

## Manual de usuario

El entorno gráfico de la aplicación se accede a través de la url [http:localhost:8080/#/](http:localhost:8080/#/) (importante no omitir el `/#/` al final). A continuación se describe como realizar cada acción. Cada explicación está acompañada de un gif ilustrativo.

### Crear un nuevo cliente

Para crear un nuevo cliente se debe:

* Dar click en la pestaña `Create new client` o ir a la url [http://localhost:8080/#/new-client](http://localhost:8080/#/new-client)
* Llenar los datos básicos del cliente
* Dar click en el botón `Save client`

![crear cliente](https://raw.githubusercontent.com/miguel-vila/payu-bank/master/doc/create-client.gif)

### Listar los clientes creados

* Dar click en la pestaña `Clients` o ir a la url [http://localhost:8080/#/clients](http://localhost:8080/#/clients)

![listar clientes](https://raw.githubusercontent.com/miguel-vila/payu-bank/master/doc/query-clients.gif)

### Actualizar un cliente

* Dar click en la pestaña `Clients` o ir a la url [http://localhost:8080/#/clients](http://localhost:8080/#/clients)
* Dar click en el link `Client detail` del cliente que se quiere actualizar
* Actualizar los campos deseados
* Dar click en el botón `Update client`

![listar clientes](https://raw.githubusercontent.com/miguel-vila/payu-bank/master/doc/update-client.gif)

### Eliminar un cliente

* Dar click en la pestaña `Clients` o ir a la url [http://localhost:8080/#/clients](http://localhost:8080/#/clients)
* Dar click en el botón `Delete` del cliente que se quiere eliminar

![eliminar cliente](https://raw.githubusercontent.com/miguel-vila/payu-bank/master/doc/delete-client.gif)

### Crear una cuenta y agregar movimientos

* Dar click en la pestaña `Clients` o ir a la url [http://localhost:8080/#/clients](http://localhost:8080/#/clients)
* Dar click en el link `Client detail` del cliente deseado
* Dar click en el botón `Create new account`
* Seleccione el monto y el tipo de movimiento que desee realizar
* Dé click en el botón `Create movement`

![crear cuenta](https://raw.githubusercontent.com/miguel-vila/payu-bank/master/doc/create-account-and-movements.gif)

### Eliminar una cuenta

* Dar click en la pestaña `Clients` o ir a la url [http://localhost:8080/#/clients](http://localhost:8080/#/clients)
* Dar click en el link `Client detail` del cliente deseado
* Dar click en el botón `Delete account` de la cuenta que se desea eliminar

![eliminar cuenta](https://raw.githubusercontent.com/miguel-vila/payu-bank/master/doc/delete-account.gif)

### Reporte de cuentas y movimientos por rango de fechas
* Dar click en la pestaña `Date Range Report` o ir al enlace [http://localhost:8080/#/date-range-report](http://localhost:8080/#/date-range-report)
* Asignar los valores de fecha de inicio y de fecha de fin del rango con su respectivos valores de fecha, hora, minutos y segundos.
* Elegir un cliente de la lista desplegable
* Dar click en el botón `Get report`

![report](https://raw.githubusercontent.com/miguel-vila/payu-bank/master/doc/date-range-report.gif)
