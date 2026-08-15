<img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300" alt="Logo Duoc UC"/>

# 🧠 SpeedFast

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.

El proyecto está basado en el caso de estudio **SpeedFast**, una empresa de reparto a domicilio que ofrece servicios de comida, encomiendas y compras express.

<!-- Modificar Si proyecto continua en sigiente semana para mantener line del tiempo del proyecto  -_- -->
El objetivo de esta actividad es aplicar conceptos de **Programación Orientada a Objetos**, principalmente herencia, sobreescritura, sobrecarga y polimorfismo.

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

Clase base que representa un pedido genérico y contiene los atributos:

* `idPedido`
* `direccionEntrega`
* `tipoPedido`

Además, define el método `asignarRepartidor()` para ser utilizado por las clases derivadas.

### `PedidoComida`

Representa pedidos provenientes de restaurantes y considera la validación de una mochila térmica para el repartidor.

### `PedidoEncomienda`

Representa documentos o paquetes y considera la validación del peso y embalaje.

### `PedidoExpress`

Representa compras realizadas mediante el servicio express y considera la asignación del repartidor más cercano con disponibilidad inmediata.

---

## 🔄 Conceptos de POO Aplicados

El proyecto implementa:

* **Herencia:** las clases especializadas heredan de `Pedido`.
* **Sobreescritura:** cada tipo de pedido redefine `asignarRepartidor()` según sus propios requerimientos.
* **Sobrecarga:** se implementa `asignarRepartidor(String nombreRepartidor)`.
* **Polimorfismo:** se utilizan objetos de las clases derivadas mediante la referencia de la clase base.

---

## ☕ Requisitos
<!-- Tal vez deba bajar el jdk de 21 a 17 -->
* Java JDK 21 o superior.
* IntelliJ IDEA Community Edition.
* Maven.

---

## ▶️ Ejecución

La ejecución y prueba del sistema se realiza desde la clase `Main`, donde se crean objetos de los diferentes tipos de pedido y se prueban los métodos de asignación de repartidores.

---

## 👨‍💻 Autor

**Michael Salgado**

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II (PRY2203)** de Duoc UC.
