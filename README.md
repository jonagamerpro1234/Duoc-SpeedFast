<img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300" alt="Logo Duoc UC"/>

# 🧠 SpeedFast

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.

El proyecto está basado en el caso de estudio **SpeedFast**, una empresa de reparto a domicilio que ofrece servicios de comida, encomiendas y compras express.

<!-- Si el proyecto continúa en las siguientes semanas, modificar esta sección para mantener la línea de tiempo del proyecto. -->

El objetivo de esta actividad es aplicar conceptos de **Programación Orientada a Objetos**, utilizando clases abstractas, herencia, polimorfismo, interfaces y colecciones.

---

## 📁 Estructura del Proyecto

```text
SpeedFast/
├── README.md
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
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
                            │   └── PedidoExpress.java
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

### `PedidoExpress`

Representa compras realizadas mediante el servicio express.

El tiempo estimado es de **10 minutos** para distancias de hasta 5 km y de **15 minutos** para distancias superiores.

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

## 🔄 Conceptos de POO Aplicados

El proyecto implementa:

* **Clase abstracta:** `Pedido` concentra los atributos y comportamientos comunes.
* **Herencia:** los diferentes tipos de pedidos heredan de `Pedido`.
* **Sobreescritura:** cada tipo de pedido implementa su propia lógica para calcular el tiempo de entrega.
* **Sobrecarga:** se utilizan versiones de `asignarRepartidor()` con y sin parámetros.
* **Polimorfismo:** los diferentes tipos de pedidos pueden utilizarse mediante referencias de tipo `Pedido`.
* **Interfaces:** definen comportamientos relacionados con cancelar, despachar y rastrear pedidos.
* **Colecciones:** se utiliza `ArrayList` para almacenar el historial de pedidos despachados.

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
2. Cálculo del tiempo estimado de entrega.
3. Asignación de repartidores.
4. Despacho de pedidos.
5. Cancelación de pedidos.
6. Registro y visualización del historial.

---

## 👨‍💻 Autor

**Michael Salgado**

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.