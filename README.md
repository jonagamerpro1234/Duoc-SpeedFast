<img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300" alt="Logo Duoc UC"/>

# 🧠 SpeedFast

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.

El proyecto está basado en el caso de estudio **SpeedFast**, una empresa de reparto a domicilio que ofrece servicios de comida, encomiendas y compras express.

El objetivo del proyecto es aplicar conceptos de **Programación Orientada a Objetos y programación concurrente**, utilizando clases abstractas, herencia, polimorfismo, interfaces, enumeraciones, colecciones, sincronización y ejecución de tareas mediante múltiples hilos.

---

## 📁 Estructura del Proyecto

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
                            │   │
                            │   └── enums/
                            │       ├── EstadoPedido.java
                            │       ├── PrioridadPedido.java
                            │       └── TipoPedido.java
                            │
                            └── service/
                                ├── ControladorDeEnvios.java
                                └── ZonaDeCarga.java
```

---

## 📦 Clases Principales

### `Pedido`

Clase abstracta que representa un pedido genérico dentro del sistema.

Contiene información común como:

* `id`
* `direccionEntrega`
* `distanciaKm`
* `tipoPedido`
* `estado`
* `prioridad`
* `repartidorAsignado`

El estado inicial de cada pedido es `PENDIENTE`.

Además, define métodos comunes como:

* `mostrarResumen()`
* `estaDisponible()`
* `toString()`
* `calcularTiempoEntrega()`

---

### `PedidoComida`

Representa pedidos de comida.

Calcula el tiempo de entrega utilizando un tiempo base de **15 minutos**, más **2 minutos por cada kilómetro**.

---

### `PedidoEncomienda`

Representa pedidos correspondientes a documentos o paquetes.

Calcula el tiempo de entrega utilizando un tiempo base de **20 minutos**, más **1,5 minutos por cada kilómetro**.

El resultado del cálculo es redondeado a un número entero.

---

### `PedidoExpress`

Representa pedidos correspondientes al servicio express.

El tiempo estimado es de **10 minutos** para distancias de hasta 5 km y de **15 minutos** para distancias superiores a 5 km.

---

### `Repartidor`

Representa a un repartidor encargado de realizar las entregas.

Implementa `Runnable`, permitiendo que cada repartidor pueda ejecutarse como una tarea independiente.

Cada repartidor:

* Posee un nombre.
* Utiliza una referencia a la `ZonaDeCarga` compartida.
* Retira pedidos disponibles desde la zona de carga.
* Cambia el estado del pedido a `EN_REPARTO`.
* Registra el repartidor asignado.
* Simula el tiempo de entrega utilizando `Thread.sleep()`.
* Cambia el estado del pedido a `ENTREGADO`.

Los repartidores procesan los pedidos de forma secuencial, mientras los tres repartidores pueden ejecutarse de forma concurrente.

---

## 🔢 Enumeraciones

### `EstadoPedido`

Representa los estados posibles de un pedido:

* `PENDIENTE`
* `EN_REPARTO`
* `ENTREGADO`
* `CANCELADO`

El flujo normal de un pedido es:

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

### `TipoPedido`

Representa los tipos de pedidos disponibles:

* `COMIDA`
* `ENCOMIENDA`
* `EXPRESS`

---

### `PrioridadPedido`

Representa la prioridad asignada a un pedido:

* `ALTA`
* `MEDIA`
* `BAJA`

Por defecto, los pedidos se crean con prioridad `MEDIA`.

---

## 📦 Zona de Carga

### `ZonaDeCarga`

Representa el recurso compartido donde se almacenan los pedidos antes de ser retirados por los repartidores.

La clase utiliza una lista de pedidos protegida mediante métodos sincronizados.

Sus principales operaciones son:

* `agregarPedido(Pedido pedido)`
* `retirarPedido()`
* `obtenerCantidadPedido()`

Los métodos que modifican o consultan el recurso compartido utilizan `synchronized` para controlar el acceso concurrente.

Esto permite evitar que dos repartidores retiren simultáneamente el mismo pedido.

---

## ⚙️ Controlador de Envíos

### `ControladorDeEnvios`

Clase encargada de gestionar operaciones relacionadas con los pedidos.

Implementa las interfaces:

* `Cancelable`
* `Despachable`
* `Rastreable`

Además, utiliza un `ArrayList<Pedido>` para almacenar el historial de pedidos despachados.

Sus principales funciones son:

* Cancelar pedidos.
* Evitar el despacho de pedidos cancelados.
* Despachar pedidos.
* Registrar los pedidos despachados en el historial.
* Mostrar el historial junto con el repartidor asignado.

---

## 🧵 Concurrencia

La simulación utiliza programación concurrente para representar el trabajo simultáneo de varios repartidores.

Cada objeto `Repartidor` implementa `Runnable` y es ejecutado mediante un `ExecutorService`.

El sistema utiliza:

* `Runnable` para definir las tareas de los repartidores.
* `ExecutorService` para administrar la ejecución de los hilos.
* `Executors.newFixedThreadPool(3)` para ejecutar tres repartidores.
* `Thread.sleep()` para simular el tiempo de entrega.
* `synchronized` para proteger el acceso a la zona de carga compartida.

Los tres repartidores utilizan la misma `ZonaDeCarga`:

```text
                 ZonaDeCarga
                      │
          ┌───────────┼───────────┐
          │           │           │
          ↓           ↓           ↓
        Luis        Camila      Daniela
       Hilo 1       Hilo 2       Hilo 3
          │           │           │
          └──── Retiran pedidos ──┘
```

Los pedidos son retirados de forma sincronizada, evitando que un mismo pedido sea procesado por más de un repartidor.

---

## 🔄 Conceptos Aplicados

El proyecto implementa:

* **Clase abstracta:** `Pedido` concentra los atributos y comportamientos comunes.
* **Herencia:** los diferentes tipos de pedidos heredan de `Pedido`.
* **Sobreescritura:** cada tipo de pedido implementa su propia lógica para calcular el tiempo de entrega.
* **Polimorfismo:** los diferentes tipos de pedidos pueden utilizarse mediante referencias de tipo `Pedido`.
* **Interfaces:** definen comportamientos relacionados con cancelar, despachar y rastrear pedidos.
* **Enumeraciones:** se utilizan para representar estados, tipos y prioridades.
* **Colecciones:** se utilizan listas para almacenar pedidos e historial.
* **Concurrencia:** varios repartidores pueden ejecutar sus entregas simultáneamente.
* **Runnable:** permite definir el comportamiento ejecutable de cada repartidor.
* **ExecutorService:** administra la ejecución concurrente de los repartidores.
* **Sincronización:** `synchronized` controla el acceso a la zona de carga compartida.
* **Encapsulamiento:** los atributos de las clases se mantienen privados y se acceden mediante métodos.

---

## ▶️ Ejecución

La ejecución y prueba del sistema se realiza desde la clase `Main`.

Durante la ejecución se demuestra:

1. Creación de diferentes tipos de pedidos.
2. Creación de una zona de carga compartida.
3. Agregación de pedidos a la zona de carga.
4. Cancelación de un pedido.
5. Creación de tres repartidores.
6. Ejecución concurrente de los repartidores mediante `ExecutorService`.
7. Retiro sincronizado de pedidos desde la zona de carga.
8. Cambio de estado a `EN_REPARTO`.
9. Simulación del tiempo de entrega.
10. Cambio de estado a `ENTREGADO`.
11. Finalización de la simulación una vez procesados los pedidos disponibles.

Debido a la ejecución concurrente, el orden de los mensajes de los repartidores en consola puede variar entre cada ejecución.

---

## 🧪 Simulación

La aplicación utiliza seis pedidos disponibles para entrega y un pedido adicional que es cancelado antes de iniciar la ejecución de los repartidores.

El pedido cancelado no es retirado por ningún repartidor debido a que la `ZonaDeCarga` solo permite retirar pedidos que se encuentran en estado `PENDIENTE`.

Ejemplo de estados:

```text
Pedidos 101 - 106

PENDIENTE
    ↓
EN_REPARTO
    ↓
ENTREGADO


Pedido 107

PENDIENTE
    ↓
CANCELADO
```

Durante la ejecución se puede observar que los tres repartidores trabajan de forma concurrente y que cada pedido disponible es procesado una sola vez.

---

## ☕ Requisitos

* Java JDK 21 o superior.
* IntelliJ IDEA Community Edition.
* Maven.

---

## 🛠️ Compilación

El proyecto utiliza Maven para la gestión y compilación.

Para limpiar y compilar el proyecto:

```bash
mvn clean package
```

Si la compilación finaliza correctamente, el proyecto puede ejecutarse desde la clase `Main`.

---

## 👨‍💻 Autor

**Michael Salgado**

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.