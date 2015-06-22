# payu-bank

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

## Manual de usuario

La aplicación se puede usar mediante un cliente REST como CURL o Postman.

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

Se tienen cuatro recursos principales:

* Clientes (ruta `/clients`)
* Cuentas (rutas  `/accounts/{id}` y `/clients/{clientId}/accounts`)
* Movimientos (ruta `/accounts/{accountId}/movements`)
* Reportes (ruta `/reports/client-accounts-by-date-range`)

### Client

#### Crear un cliente

**Parámetros de entrada:**

Ruta: `/clients`

Método: POST

JSON de ejemplo:

```json
{
    "name": "Miguel",
    "address": "cra 123",
    "phone": "123457"
}
```

**Respuesta esperada:**

HTTP Status: Created

Body:

```json
"Client successfully created with id: {clientId}"
```

#### Consultar todos los clientes

**Parámetros de entrada:**

Ruta: `/clients`

Método: GET

**Respuesta esperada:**

HTTP Status: Ok

Body:

```json
[
   {
      "id":6,
      "name":"Miguel",
      "address":"cra 123",
      "phone":"123457",
      "accounts":[
         {
            "number":2,
            "balance":3450000.00,
            "movements":[
               {
                  "id":9,
                  "type":"DEBIT",
                  "date":"22-06-2015 01:08:08",
                  "value":1000000.00
               },
               {
                  "id":10,
                  "type":"DEBIT",
                  "date":"22-06-2015 01:08:19",
                  "value":2500000.00
               },
               {
                  "id":13,
                  "type":"CREDIT",
                  "date":"22-06-2015 01:08:50",
                  "value":50000.00
               }
            ]
         }
      ]
   },
   {
      "id":7,
      "name":"Luisa",
      "address":"cra 456",
      "phone":"987654",
      "accounts":[
         {
            "number":3,
            "balance":4750000.00,
            "movements":[
               {
                  "id":11,
                  "type":"DEBIT",
                  "date":"22-06-2015 01:08:29",
                  "value":5000000.00
               },
               {
                  "id":12,
                  "type":"CREDIT",
                  "date":"22-06-2015 01:08:42",
                  "value":250000.00
               }
            ]
         }
      ]
   }
]
```

#### Eliminar un cliente

**Parámetros de entrada:**

Ruta: `/clients/{id}` dónde `{id}` es el identificador del cliente a eliminar

Método: DELETE

**Respuesta esperada:**

HTTP Status: Ok

Body:

```json
"Client successfully deleted"
```

### Accounts

#### Crear una cuenta vacía para un cliente

**Parámetros de entrada:**

Ruta: `/clients/{clientId}/accounts` dónde `{clientId}` es el identificador del cliente al que se le quiere crear la nueva cuenta

Método: POST

No es necesario mandar nada en el body. La cuenta se creará con un balance de 0.0 y sin movimientos.

**Respuesta esperada:** 

HTTP Status: Created

Body:

```json
"Account added successfully with id {accountId}
```

#### Eliminar una cuenta

**Parámetros de entrada:**

Ruta: `\accounts\{id}` dónde `{id}` es el identificador de la cuenta que se quiere eliminar

Método: DELETE

**Respuesta esperada:**

HTTP Status: Ok

Body:

```json
"Account deleted successfully"
```

### Movimientos

#### Realizar un movimiento en una cuenta

**Parámetros de entrada:**

Ruta: `\accounts\{accountId}\movements` dónde `{accountId}` es el identificador de la cuenta en la que se quiere realizar el movimiento.

Método: POST

**Respuesta esperada:**

HTTP Status: Created

Body:

```json
"Movement created successfully"
```

**Respuesta en caso de que el saldo no sea suficiente:**

HTTP Status: Bad Request

Body:

```json
"The account with id {accountId} has insufficient funds to perform the movement"
```

### Reportes

#### Generar un reporte

**Parámetros de entrada:**

Ruta: `/reports/client-accounts-by-date-range`

Método: POST

Body:

```json
{
    "clientId": 6,
    "start": "21-06-2015 22:00:00",
    "end": "22-06-2015 02:00:00"
}
```

Las fechas deben estar en el formato `dd-MM-yyyy HH:mm:ss`

**Respuesta esperada:**

HTTP Status: Ok

Body:

```json
[
   {
      "accountNumber":2,
      "balance":8500000.00,
      "totalCredit":50000.00,
      "totalDebit":8550000.00,
      "creditMovements":[
         {
            "id":13,
            "type":"CREDIT",
            "date":"22-06-2015 01:08:50",
            "value":50000.00
         }
      ],
      "debitMovements":[
         {
            "id":9,
            "type":"DEBIT",
            "date":"22-06-2015 01:08:08",
            "value":1000000.00
         },
         {
            "id":10,
            "type":"DEBIT",
            "date":"22-06-2015 01:08:19",
            "value":2500000.00
         },
         {
            "id":14,
            "type":"DEBIT",
            "date":"22-06-2015 01:43:29",
            "value":50000.00
         },
         {
            "id":15,
            "type":"DEBIT",
            "date":"22-06-2015 01:43:36",
            "value":5000000.00
         }
      ]
   }
]
```

Dónde `totalCredit` es el cŕedito total en el intervalo de fechas provisto y `totalDebit` es el débito total en el intervalo de fechas provisto.
