<img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300" alt="Logo Duoc UC"/>

# 🧠 SpeedFast

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.

El proyecto está basado en el caso de estudio **SpeedFast**, una empresa de reparto a domicilio que ofrece servicios de comida, encomiendas y compras express.

<!-- Si el proyecto continúa en las siguientes semanas, modificar esta sección para mantener la línea de tiempo del proyecto. -->

El objetivo de esta actividad es aplicar conceptos de **Programación Orientada a Objetos**, principalmente clases abstractas, herencia, sobrescritura y polimorfismo.

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
                            └── model/
                                ├── Pedido.java
                                ├── PedidoComida.java
                                ├── PedidoEncomienda.java
                                └── PedidoExpress.java
```

---

## 📦 Clases Principales

### `Pedido`

Clase abstracta que representa un pedido genérico dentro del sistema.

Contiene los atributos comunes:

* `idPedido`
* `direccionEntrega`
* `distanciaKm`

Además, define:

* `mostrarResumen()`: muestra los datos básicos del pedido.
* `calcularTiempoEntrega()`: método abstracto que debe ser implementado por cada tipo de pedido.

### `PedidoComida`

Representa pedidos provenientes de restaurantes.

Implementa `calcularTiempoEntrega()` utilizando un tiempo base de **15 minutos**, más **2 minutos por cada kilómetro**.

### `PedidoEncomienda`

Representa documentos o paquetes.

Implementa `calcularTiempoEntrega()` utilizando un tiempo base de **20 minutos**, más **1,5 minutos por cada kilómetro**, ajustando el resultado a un número entero.

### `PedidoExpress`

Representa compras realizadas mediante el servicio express.

Implementa `calcularTiempoEntrega()` utilizando un tiempo base de **10 minutos**. Si la distancia es superior a **5 km**, se agregan **5 minutos adicionales**.

---

## 🔄 Conceptos de POO Aplicados

El proyecto implementa:

* **Clase abstracta:** `Pedido` define atributos y comportamientos comunes para los diferentes tipos de pedido.
* **Herencia:** `PedidoComida`, `PedidoEncomienda` y `PedidoExpress` heredan de `Pedido`.
* **Sobreescritura:** cada subclase implementa `calcularTiempoEntrega()` según sus propios requerimientos.
* **Polimorfismo:** se utilizan objetos de las clases derivadas mediante referencias de tipo `Pedido`.

---

## ☕ Requisitos

<!-- Verificar la versión de JDK utilizada en el proyecto antes de modificar este requisito. -->

* Java JDK 21 o superior.
* IntelliJ IDEA Community Edition.
* Maven.

---

## ▶️ Ejecución

La ejecución y prueba del sistema se realiza desde la clase `Main`.

Se crean objetos de los diferentes tipos de pedido y se utilizan los métodos `mostrarResumen()` y `calcularTiempoEntrega()` para mostrar de forma clara y comparativa la información y el tiempo estimado de entrega de cada pedido.

---

## 👨‍💻 Autor

**Michael Salgado**

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.
