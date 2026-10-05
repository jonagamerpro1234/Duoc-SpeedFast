<img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300" alt="Logo Duoc UC"/>

# 🧠 SpeedFast

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.

El proyecto está basado en el caso de estudio **SpeedFast**, una empresa de reparto a domicilio que ofrece servicios de comida, encomiendas y entregas express.

El sistema fue desarrollado utilizando **Programación Orientada a Objetos**, incorporando conceptos como clases abstractas, herencia, polimorfismo, interfaces, enumeraciones, colecciones, concurrencia, sincronización y persistencia de datos mediante una base de datos MySQL.

En la **Semana 8 (S8)** se completa el ciclo funcional de la aplicación mediante la implementación de operaciones **CRUD**, utilizando **JDBC, DAO y una interfaz gráfica desarrollada con Java Swing**.

---

## 🎯 Objetivo del proyecto

El objetivo es desarrollar una aplicación que permita gestionar la información principal de SpeedFast:

- Repartidores.
- Pedidos.
- Entregas.

La información es almacenada de forma persistente en una base de datos MySQL y administrada mediante operaciones CRUD desde una interfaz gráfica.

---

## 🛠️ Tecnologías utilizadas

- **Java JDK 21**
- **Maven**
- **MySQL**
- **JDBC**
- **Java Swing**
- **IntelliJ IDEA Community Edition**
- **Git / GitHub**

---

# 📁 Estructura del Proyecto

```text
SpeedFast/
├── README.md
├── pom.xml
└── src/
    └── main/
        └── java/
            └── cl/
                └── duocuc/
                    └── salgado/
                        └── mich/
                            ├── app/
                            │   └── Main.java
                            │
                            ├── dao/
                            │   ├── ConexionDB.java
                            │   ├── PedidoDAO.java
                            │   ├── PedidoDAOImpl.java
                            │   ├── RepartidorDAO.java
                            │   ├── RepartidorDAOImpl.java
                            │   ├── EntregaDAO.java
                            │   └── EntregaDAOImpl.java
                            │
                            ├── interfaces/
                            │   ├── Cancelable.java
                            │   ├── Despachable.java
                            │   └── Rastreable.java
                            │
                            ├── model/
                            │   ├── Pedido.java
                            │   ├── PedidoComida.java
                            │   ├── PedidoEncomienda.java
                            │   ├── PedidoExpress.java
                            │   ├── Repartidor.java
                            │   ├── Entrega.java
                            │   │
                            │   └── enums/
                            │       ├── EstadoPedido.java
                            │       ├── PrioridadPedido.java
                            │       └── TipoPedido.java
                            │
                            ├── service/
                            │   ├── ControladorDeEnvios.java
                            │   └── ZonaDeCarga.java
                            │
                            └── ui/
                                ├── Menu.java
                                │
                                └── panels/
                                    ├── RegistroPedido.java
                                    ├── ListaPedidos.java
                                    ├── RegistroRepartidor.java
                                    ├── ListaRepartidores.java
                                    ├── RegistroEntrega.java
                                    └── ListaEntregas.java
```

---

# 📦 Gestión de Pedidos

## `Pedido`

Clase abstracta que representa un pedido genérico dentro del sistema.

Contiene información común como:

- `id`
- `direccionEntrega`
- `distanciaKm`
- `tipoPedido`
- `estado`
- `prioridad`
- `repartidorAsignado`

El estado inicial de cada pedido es `PENDIENTE`.

Además, define comportamientos comunes como:

- `mostrarResumen()`
- `estaDisponible()`
- `toString()`
- `calcularTiempoEntrega()`

---

## `PedidoComida`

Representa pedidos de comida.

Calcula el tiempo de entrega utilizando un tiempo base de **15 minutos**, más **2 minutos por cada kilómetro**.

---

## `PedidoEncomienda`

Representa pedidos correspondientes a documentos o paquetes.

Calcula el tiempo de entrega utilizando un tiempo base de **20 minutos**, más **1,5 minutos por cada kilómetro**.

El resultado del cálculo es redondeado a un número entero.

---

## `PedidoExpress`

Representa pedidos correspondientes al servicio express.

El tiempo estimado es:

- **10 minutos** para distancias de hasta 5 km.
- **15 minutos** para distancias superiores a 5 km.

---

# 🚴 Gestión de Repartidores

## `Repartidor`

Representa a un repartidor encargado de realizar las entregas.

Implementa `Runnable`, permitiendo que cada repartidor pueda ejecutarse como una tarea independiente.

Cada repartidor:

- Posee un identificador.
- Posee un nombre.
- Utiliza una referencia a la `ZonaDeCarga` compartida.
- Puede retirar pedidos desde la zona de carga.
- Cambia el estado del pedido a `EN_REPARTO`.
- Registra el repartidor asignado.
- Simula el tiempo de entrega mediante `Thread.sleep()`.
- Cambia el estado del pedido a `ENTREGADO`.

Además, la información de los repartidores se almacena en la base de datos mediante `RepartidorDAO`.

---

# 📦 Gestión de Entregas

## `Entrega`

Representa una entrega asociada a un pedido y un repartidor.

Contiene:

- `id`
- `idPedido`
- `idRepartidor`
- `fecha`
- `hora`

La entrega mantiene las relaciones con el pedido y el repartidor mediante sus respectivos identificadores.

---

# 🗄️ Persistencia y Base de Datos

En la Semana 8 se incorpora persistencia mediante **JDBC** y el patrón **DAO (Data Access Object)**.

La aplicación utiliza MySQL para almacenar la información de:

- Pedidos.
- Repartidores.
- Entregas.

La conexión se gestiona mediante la clase:

```text
ConexionDB
```

La conexión utiliza JDBC y permite acceder a la base de datos:

```text
speedfast_db
```

---

# 🧩 Clases DAO

Cada entidad principal posee su propio DAO para separar la lógica de acceso a datos de la lógica de la interfaz.

## `PedidoDAO`

Permite:

- Registrar pedidos.
- Listar pedidos.
- Actualizar pedidos.
- Eliminar pedidos.

Implementación:

```text
PedidoDAOImpl
```

---

## `RepartidorDAO`

Permite:

- Registrar repartidores.
- Listar repartidores.
- Actualizar repartidores.
- Eliminar repartidores.

Implementación:

```text
RepartidorDAOImpl
```

---

## `EntregaDAO`

Permite:

- Registrar entregas.
- Listar entregas.
- Actualizar entregas.
- Eliminar entregas.

Implementación:

```text
EntregaDAOImpl
```

Las operaciones SQL utilizan `PreparedStatement` y `ResultSet`.

---

# 🖥️ Interfaz Gráfica

La aplicación utiliza **Java Swing** para proporcionar una interfaz gráfica para la gestión de la información.

El menú principal permite acceder a:

- Registrar Pedido.
- Listar Pedidos.
- Registrar Repartidor.
- Listar Repartidores.
- Registrar Entrega.
- Listar Entregas.
- Salir.

---

## 📋 Gestión de Pedidos

La interfaz permite:

- Registrar un pedido.
- Seleccionar el tipo de pedido.
- Seleccionar el estado.
- Listar pedidos mediante `JTable`.
- Actualizar información.
- Eliminar pedidos.
- Validar los datos ingresados.

Los tipos disponibles son:

```text
COMIDA
ENCOMIENDA
EXPRESS
```

Los estados disponibles son:

```text
PENDIENTE
EN_REPARTO
ENTREGADO
```

---

## 🚴 Gestión de Repartidores

La interfaz permite:

- Registrar repartidores.
- Listar repartidores mediante `JTable`.
- Editar nombres.
- Eliminar repartidores.

---

## 📦 Gestión de Entregas

La interfaz permite:

- Registrar una entrega.
- Seleccionar un pedido existente.
- Seleccionar un repartidor existente.
- Registrar fecha y hora.
- Listar entregas mediante `JTable`.
- Editar entregas.
- Eliminar entregas.

Los `JComboBox` utilizan objetos `Pedido` y `Repartidor`, mostrando información legible al usuario y conservando internamente sus identificadores.

Ejemplo:

```text
Pedido #101 - Comida - Dirección: Av. Las Condes 123
Repartidor #1 - Luis
```

---

# 🔢 Enumeraciones

## `EstadoPedido`

Representa los estados posibles de un pedido:

- `PENDIENTE`
- `EN_REPARTO`
- `ENTREGADO`
- `CANCELADO`

Flujo normal:

```text
PENDIENTE
    ↓
EN_REPARTO
    ↓
ENTREGADO
```

También se contempla la cancelación:

```text
PENDIENTE
    ↓
CANCELADO
```

---

## `TipoPedido`

Representa los tipos de pedidos disponibles:

- `COMIDA`
- `ENCOMIENDA`
- `EXPRESS`

---

## `PrioridadPedido`

Representa la prioridad asignada a un pedido:

- `ALTA`
- `MEDIA`
- `BAJA`

Por defecto, los pedidos se crean con prioridad `MEDIA`.

---

# 📦 Zona de Carga

## `ZonaDeCarga`

Representa el recurso compartido donde se almacenan los pedidos antes de ser retirados por los repartidores.

La clase utiliza una lista de pedidos protegida mediante métodos sincronizados.

Sus principales operaciones son:

- `agregarPedido(Pedido pedido)`
- `retirarPedido()`
- `obtenerCantidadPedidos()`

Los métodos que modifican el recurso compartido utilizan `synchronized` para controlar el acceso concurrente.

Esta funcionalidad corresponde a la lógica de concurrencia desarrollada en semanas anteriores.

---

# ⚙️ Controlador de Envíos

## `ControladorDeEnvios`

Clase encargada de gestionar operaciones relacionadas con los pedidos.

Implementa las interfaces:

- `Cancelable`
- `Despachable`
- `Rastreable`

Además, utiliza un `ArrayList<Pedido>` para almacenar el historial de pedidos despachados.

Sus principales funciones son:

- Cancelar pedidos.
- Evitar el despacho de pedidos cancelados.
- Despachar pedidos.
- Registrar pedidos en el historial.
- Mostrar el historial junto con el repartidor asignado.

---

# 🧵 Concurrencia

El proyecto mantiene la lógica de programación concurrente desarrollada en semanas anteriores.

Cada objeto `Repartidor` implementa `Runnable` y puede ejecutarse mediante un `ExecutorService`.

Se utilizan conceptos como:

- `Runnable`
- `ExecutorService`
- `Executors.newFixedThreadPool(3)`
- `Thread.sleep()`
- `synchronized`

Los repartidores pueden utilizar una misma `ZonaDeCarga` compartida para procesar pedidos de forma concurrente.

La simulación de concurrencia corresponde a la lógica desarrollada anteriormente, mientras que en la Semana 8 el funcionamiento principal de la aplicación se centra en la gestión CRUD mediante la interfaz gráfica y la base de datos.

---

# 🔄 Conceptos de Programación Orientada a Objetos

El proyecto implementa:

- **Clase abstracta:** `Pedido` concentra atributos y comportamientos comunes.
- **Herencia:** los diferentes tipos de pedidos heredan de `Pedido`.
- **Sobreescritura:** cada tipo de pedido implementa su propia lógica para calcular el tiempo de entrega.
- **Polimorfismo:** los diferentes tipos de pedidos pueden utilizarse mediante referencias de tipo `Pedido`.
- **Interfaces:** definen comportamientos relacionados con cancelar, despachar y rastrear pedidos.
- **Enumeraciones:** representan estados, tipos y prioridades.
- **Colecciones:** se utilizan listas para almacenar pedidos e historial.
- **Concurrencia:** varios repartidores pueden ejecutar tareas simultáneamente.
- **Runnable:** permite definir el comportamiento ejecutable de los repartidores.
- **ExecutorService:** administra la ejecución concurrente.
- **Sincronización:** `synchronized` controla el acceso a la zona de carga compartida.
- **Encapsulamiento:** los atributos de las clases se mantienen privados y se acceden mediante métodos.
- **DAO:** separa el acceso a la base de datos de la lógica de la aplicación.

---

# 🗃️ Base de Datos

La aplicación utiliza una base de datos MySQL llamada:

```text
speedfast_db
```

Las principales tablas utilizadas son:

```text
repartidor
pedido
entrega
```

Relaciones principales:

```text
repartidor
     │
     │
     ↓
  entrega
     ↑
     │
   pedido
```

La tabla `entrega` mantiene las relaciones mediante:

```text
id_pedido
id_repartidor
```

---

# ▶️ Ejecución

La aplicación se inicia desde:

```text
Main.java
```

Al iniciar:

1. Se comprueba la conexión con MySQL.
2. Se inicia la interfaz gráfica.
3. Se muestra el menú principal.
4. El usuario puede acceder a las diferentes operaciones CRUD.

La aplicación no ejecuta automáticamente la simulación de concurrencia al iniciar. La simulación corresponde a la lógica desarrollada en semanas anteriores, mientras que el flujo principal de S8 corresponde a la gestión mediante interfaz gráfica y base de datos.

---

# 🧪 Operaciones CRUD

El sistema permite realizar las siguientes operaciones:

| Entidad | Crear | Listar | Actualizar | Eliminar |
|:---|:---:|:---:|:---:|:---:|
| Repartidor | ✅ | ✅ | ✅ | ✅ |
| Pedido | ✅ | ✅ | ✅ | ✅ |
| Entrega | ✅ | ✅ | ✅ | ✅ |

Las operaciones son realizadas mediante los respectivos DAO y se almacenan directamente en MySQL.

---

# ⚠️ Validaciones y manejo de errores

La aplicación incorpora validaciones básicas antes de realizar operaciones sobre la base de datos.

Entre ellas:

- Campos obligatorios.
- Validación de valores numéricos.
- Validación de identificadores.
- Selección de entidades relacionadas.
- Manejo de excepciones SQL.
- Mensajes de información y error mediante `JOptionPane`.

Las conexiones y recursos utilizados para acceder a la base de datos se gestionan mediante `try-with-resources`.

---

# ☕ Requisitos

Para ejecutar el proyecto se requiere:

- Java JDK 21 o superior.
- IntelliJ IDEA Community Edition.
- Maven.
- MySQL.
- Conector JDBC de MySQL.

---

# 🗄️ Configuración de la Base de Datos

Antes de ejecutar la aplicación se debe contar con la base de datos:

```text
speedfast_db
```

La conexión se configura en:

```text
ConexionDB.java
```

Se deben establecer los datos correspondientes al servidor MySQL:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/speedfast_db?createDatabaseIfNotExist=true";

private static final String USER = "root";

private static final String PASSWORD = "TU_CONTRASEÑA";
```

Las tablas utilizadas por el sistema son creadas o verificadas mediante los DAO correspondientes.

---

# 🛠️ Compilación

El proyecto utiliza Maven para la gestión y compilación.

Para limpiar y compilar:

```bash
mvn clean package
```

También puede ejecutarse directamente desde IntelliJ IDEA utilizando la clase:

```text
Main.java
```

---

# 📚 Resultado de la Semana 8

Con la implementación realizada en S8, el proyecto incorpora:

- Persistencia de datos mediante MySQL.
- Acceso a datos mediante JDBC.
- Patrón DAO.
- Operaciones CRUD completas.
- Interfaz gráfica Swing.
- `JTable` para visualizar información.
- `JComboBox` para seleccionar entidades relacionadas.
- Validaciones de entrada.
- Manejo de excepciones SQL.
- Separación entre modelo, interfaz y acceso a datos.

De esta forma, SpeedFast completa el ciclo funcional de gestión de pedidos, repartidores y entregas.

---

# 👨‍💻 Autor

**Michael Salgado**

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.