<img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300" alt="Logo Duoc UC"/>

# 🧠 SpeedFast

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.

El proyecto está basado en el caso de estudio **SpeedFast**, una empresa de reparto a domicilio que ofrece servicios de comida, encomiendas y compras express.

El objetivo del proyecto es aplicar conceptos de **Programación Orientada a Objetos y programación concurrente**, utilizando clases abstractas, herencia, polimorfismo, interfaces, colecciones y ejecución de tareas mediante múltiples hilos.

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
                            │   └── Repartidor.java
                            │
                            └── service/
                                └── ControladorDeEnvios.java
```

---

## 📦 Clases Principales

### `Pedido`

Clase abstracta que representa un pedido genérico dentro del sistema.

Contiene información común como:

* `idPedido`
* `direccionEntrega`
* `distanciaKm`
* `tipoPedido`
* `cancelado`
* `repartidorAsignado`

Además, define métodos comunes como:

* `mostrarResumen()`
* `calcularTiempoEntrega()`
* `asignarRepartidor()`

### `PedidoComida`

Representa pedidos provenientes de restaurantes.

Calcula el tiempo de entrega utilizando un tiempo base de **15 minutos**, más **2 minutos por cada kilómetro**.

### `PedidoEncomienda`

Representa documentos o paquetes.

Calcula el tiempo de entrega utilizando un tiempo base de **20 minutos**, más **1,5 minutos por cada kilómetro**.

El resultado del cálculo es redondeado a un número entero.

### `PedidoExpress`

Representa compras realizadas mediante el servicio express.

El tiempo estimado es de **10 minutos** para distancias de hasta 5 km y de **15 minutos** para distancias superiores.

### `Repartidor`

Representa a un repartidor encargado de realizar las entregas de los pedidos asignados.

Implementa la interfaz `Runnable`, permitiendo que cada repartidor pueda ejecutarse como una tarea independiente.

Cada repartidor:

* Posee un nombre.
* Mantiene una lista de pedidos asignados.
* Procesa sus pedidos de forma secuencial.
* Simula el tiempo de entrega utilizando `Thread.sleep()`.
* Puede ejecutarse de forma concurrente junto con otros repartidores.

---

## 🔌 Interfaces

### `Cancelable`

Define el comportamiento necesario para cancelar un pedido.

### `Despachable`

Define el comportamiento necesario para despachar un pedido.

### `Rastreable`

Define el comportamiento necesario para visualizar el historial de pedidos despachados.

---

## ⚙️ Controlador de Envíos

### `ControladorDeEnvios`

Clase encargada de gestionar las operaciones relacionadas con los pedidos.

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

La simulación de entregas utiliza programación concurrente para representar el trabajo simultáneo de varios repartidores.

Cada objeto `Repartidor` implementa `Runnable` y es ejecutado mediante un `ExecutorService`.

El sistema utiliza:

* `Runnable` para definir las tareas de los repartidores.
* `ExecutorService` para administrar la ejecución de los hilos.
* `Executors.newFixedThreadPool(3)` para ejecutar tres repartidores.
* `Thread.sleep()` para simular el tiempo de entrega.

Los repartidores trabajan de forma concurrente, mientras que los pedidos de cada repartidor son procesados de forma secuencial.

```text
ExecutorService
        │
        ├── Repartidor Luis
        │      ├── Pedido 101
        │      └── Pedido 102
        │
        ├── Repartidor Camila
        │      ├── Pedido 103
        │      └── Pedido 104
        │
        └── Repartidor Daniela
               ├── Pedido 105
               └── Pedido 106
```

---

## 🔄 Conceptos Aplicados

El proyecto implementa:

* **Clase abstracta:** `Pedido` concentra los atributos y comportamientos comunes.
* **Herencia:** los diferentes tipos de pedidos heredan de `Pedido`.
* **Sobreescritura:** cada tipo de pedido implementa su propia lógica para calcular el tiempo de entrega.
* **Sobrecarga:** se utilizan versiones de `asignarRepartidor()` con y sin parámetros.
* **Polimorfismo:** los diferentes tipos de pedidos pueden utilizarse mediante referencias de tipo `Pedido`.
* **Interfaces:** definen comportamientos relacionados con cancelar, despachar y rastrear pedidos.
* **Colecciones:** se utilizan listas para almacenar pedidos e historial.
* **Concurrencia:** varios repartidores pueden ejecutar sus entregas simultáneamente.
* **Runnable:** permite definir el comportamiento ejecutable de cada repartidor.
* **ExecutorService:** administra la ejecución concurrente de los repartidores.

---

## ☕ Requisitos

* Java JDK 21 o superior.
* IntelliJ IDEA Community Edition.
* Maven.

---

## ▶️ Ejecución

La ejecución y prueba del sistema se realiza desde la clase `Main`.

Durante la ejecución se demuestra:

1. Creación de diferentes tipos de pedidos.
2. Creación de repartidores.
3. Asignación de pedidos a cada repartidor.
4. Procesamiento secuencial de los pedidos por cada repartidor.
5. Ejecución concurrente de múltiples repartidores mediante `ExecutorService`.
6. Simulación del tiempo de entrega utilizando `Thread.sleep()`.

Debido a la ejecución concurrente, el orden de los mensajes en consola puede variar entre cada ejecución.

---

## 👨‍💻 Autor

**Michael Salgado**

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.