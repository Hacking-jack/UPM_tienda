# UPM Tienda - Documentación de Diagramas UML

Este directorio contiene diagramas UML completos para el proyecto UPM Tienda (Sistema de Gestión de Tienda). Todos los diagramas están creados usando PlantUML y siguen las mejores prácticas de UML 2.5.

## 📋 Diagramas Disponibles

### 1. **Diagrama de Clases** (`class-diagram.puml`)
**Propósito:** Muestra la estructura estática del sistema con todas las clases, sus atributos, métodos y relaciones.

**Características Principales:**
- Visualización completa de la arquitectura MVC (Modelo-Vista-Controlador)
- Todas las clases con modificadores de visibilidad (público +, privado -)
- Relaciones: Composición (*--), Agregación (o--), Asociación (-->), Dependencia (..)
- Notas detalladas explicando reglas de negocio y restricciones
- Paquetes con código de colores para fácil identificación
- Multiplicidades mostrando tamaños de colecciones (0..200 productos, 0..100 items por ticket)

**Mejores Prácticas Utilizadas:**
- Estereotipos para patrones de diseño (<<manages>>, <<controls>>, <<uses>>)
- Leyenda completa explicando símbolos
- Agrupación organizada de métodos (Constructores, CRUD, Consultas, Validación)
- Notas documentando restricciones de negocio

---

### 2. **Diagramas de Secuencia**

#### a. **Agregar al Ticket** (`sequence-diagram-add-to-ticket.puml`)
**Propósito:** Flujo de interacción detallado al agregar productos a un ticket con cálculo de descuentos.

**Características Principales:**
- Flujo completo de mensajes desde entrada del usuario hasta impresión del recibo
- Fragmentos Alt/Opt para lógica condicional
- Fragmentos Loop para iteraciones
- Barras de activación mostrando tiempo de vida de objetos
- Auto-numeración para seguimiento de pasos
- Reglas de negocio documentadas en notas

**Muestra:**
- Validación de productos
- Procesamiento de cantidad en bucle
- Algoritmo de conteo de categorías
- Lógica de cálculo de descuentos
- Proceso de generación de recibo

#### b. **Gestión de Productos** (`sequence-diagram-product-management.puml`)
**Propósito:** Muestra todas las operaciones CRUD para la gestión del catálogo de productos.

**Características Principales:**
- Cuatro escenarios separados: Agregar, Actualizar, Listar, Eliminar
- Rutas completas de manejo de errores
- Flujo de validación de categorías
- Verificación de unicidad de IDs
- Aplicación de restricciones de capacidad

**Mejores Prácticas:**
- Secciones separadas para claridad (usando `|||`)
- Activación/desactivación consistente
- Auto-llamadas para métodos internos
- Notas explicando formatos de comandos

---

### 3. **Diagrama de Actividades** (`activity-diagram.puml`)
**Propósito:** Flujo completo de la aplicación desde inicio hasta salida.

**Características Principales:**
- Flujo de trabajo completo de procesamiento de comandos
- Sentencias switch anidadas para comandos/sub-comandos
- Rombos de decisión para todas las ramas condicionales
- Construcciones de bucle (repeat/repeat while)
- Particiones para agrupación lógica
- Elementos con código de colores (inicio: verde, fin: rojo, actividades: amarillo)

**Cubre:**
- Inicialización de la aplicación
- Bucle principal de comandos
- Todos los comandos de productos (add, list, update, remove)
- Todos los comandos de ticket (new, add, remove, print)
- Comandos del sistema (help, echo, exit)
- Rutas de manejo de errores

**Mejores Prácticas:**
- Flujos hacia atrás para bucles
- Notas flotantes para lógica compleja
- Leyenda explicando colores y restricciones

---

### 4. **Diagrama de Estados** (`state-diagram.puml`)
**Propósito:** Modela los estados del ciclo de vida de Productos y Tickets.

**Características Principales:**
- Máquinas de estados paralelas (ciclos de vida de Producto y Ticket)
- Estados compuestos con sub-estados
- Anotaciones de estado UML (entry/, do/, exit/)
- Etiquetas de transición con triggers y guardas
- Estados de advertencia para operaciones peligrosas

**Estados de Producto:**
- Created → Active → Updating → InTicket → Removed
- Muestra pasos de validación
- Demuestra persistencia de estado

**Estados de Ticket:**
- Empty → WithProducts → Full → Printing
- Transiciones de capacidad
- Cálculo de recibo como comportamiento de estado

**Mejores Prácticas:**
- Acciones de entrada/salida documentadas
- Condiciones de guarda en transiciones
- Auto-transiciones para acciones repetidas
- Estados paralelos separados

---

### 5. **Diagrama de Componentes** (`component-diagram.puml`)
**Propósito:** Muestra la arquitectura física del sistema y las dependencias entre componentes.

**Características Principales:**
- Arquitectura en capas de tres niveles
- Notación de componentes UML 2.0
- Puertos e interfaces
- Estructura interna de componentes
- Dependencias de librerías externas

**Capas:**
- **Capa de Presentación:** Aplicación CLI con parseo de comandos
- **Capa de Lógica de Negocio:** Controladores de gestión de Productos y Tickets
- **Capa de Datos:** Modelo de dominio y persistencia

**Mejores Prácticas:**
- Notación de puertos para interfaces de componentes
- Definiciones de interfaces (IProductController, ITicketController)
- Clara separación de responsabilidades
- Documentación de dirección de dependencias

---

## 🎨 Convenciones de Diseño

Todos los diagramas siguen estas convenciones consistentes:

### Esquema de Colores
- **Capa Vista/Presentación:** Verde Claro (#E8F5E9)
- **Capa Controlador/Lógica de Negocio:** Azul Claro (#E3F2FD)
- **Capa Modelo/Datos:** Rosa Claro (#FCE4EC)
- **Utilidades/Externos:** Amarillo Claro (#FFF9C4)
- **Fondo:** Blanco Apagado (#FEFEFE)

### Tipografía
- **Fuente:** Arial (profesional, legible)
- **Títulos:** Negrita con subtítulo
- **Notas:** Estructuradas con encabezados en negrita

### Símbolos UML
- `+` Visibilidad pública
- `-` Visibilidad privada
- `*--` Composición (propiedad fuerte)
- `o--` Agregación (propiedad débil)
- `-->` Asociación
- `..>` Dependencia
- `<<estereotipo>>` Estereotipos para clarificación

## 📊 Relaciones entre Diagramas

```
Diagrama de Clases (Estructura)
    ↓ implementa
Diagramas de Secuencia (Comportamiento)
    ↓ traza a través de
Diagrama de Actividades (Flujo de Proceso)
    ↓ afecta
Diagrama de Estados (Ciclo de Vida)

Diagrama de Componentes (Arquitectura)
    ↓ implementado por
Diagrama de Clases (Estructura)
```

## 🛠️ Cómo Usar Estos Diagramas

### Visualización
1. **Plugin PlantUML:** Instala el plugin PlantUML en IntelliJ IDEA
2. **Visor Online:** Usa http://www.plantuml.com/plantuml/uml/
3. **VS Code:** Instala la extensión PlantUML
4. **Línea de Comandos:** `java -jar plantuml.jar *.puml`

### Generar Imágenes
```bash
# Generar todos los diagramas como PNG
plantuml -tpng *.puml

# Generar como SVG (escalable)
plantuml -tsvg *.puml

# Generar como PDF
plantuml -tpdf *.puml
```

## 📚 Cobertura de Documentación

Estos diagramas proporcionan documentación completa de:

✅ **Aspectos Estructurales:**
- Relaciones y jerarquías de clases
- Arquitectura de componentes
- Organización del sistema

✅ **Aspectos Comportamentales:**
- Interacciones de objetos (secuencias)
- Flujos de trabajo de procesos (actividades)
- Ciclos de vida de objetos (estados)

✅ **Aspectos de Implementación:**
- Estructura MVC
- Separación de responsabilidades
- Dependencias entre componentes

## 🎯 Mejores Prácticas Demostradas

1. **Separación de Responsabilidades:** Arquitectura MVC clara
2. **Principio DRY:** Componentes reutilizables
3. **Principios SOLID:** Responsabilidad única por clase
4. **Documentación:** Notas y leyendas completas
5. **Consistencia:** Estilo uniforme en todos los diagramas
6. **Completitud:** Todos los aspectos del sistema documentados
7. **Claridad:** Código de colores y estereotipos para comprensión
8. **Conformidad con Estándares:** Notación UML 2.5 en todo momento

## 🔍 Referencia Rápida

| Tipo de Diagrama | Uso Principal | Audiencia |
|------------------|---------------|-----------|
| Clases | Entender estructura | Desarrolladores |
| Secuencia | Entender interacciones | Desarrolladores, Arquitectos |
| Actividades | Entender procesos | Todos los interesados |
| Estados | Entender ciclos de vida | Desarrolladores, Testers |
| Componentes | Entender arquitectura | Arquitectos, DevOps |

## 💡 Reglas de Negocio del Sistema

### Restricciones de Productos
- **Máximo:** 200 productos en el catálogo
- **IDs:** Deben ser únicos
- **Categorías válidas:** MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS

### Restricciones de Tickets
- **Máximo:** 100 productos por ticket
- **Descuentos:** Se aplican cuando hay ≥2 productos de la misma categoría
- **Tasas de descuento:**
  - MERCH: 0%
  - STATIONERY: 5%
  - CLOTHES: 7%
  - BOOK: 10%
  - ELECTRONICS: 3%

### Comandos Disponibles
```
Gestión de Productos:
  prod add <id> "<nombre>" <categoría> <precio>
  prod list
  prod update <id> NAME|CATEGORY|PRICE <valor>
  prod remove <id>

Gestión de Tickets:
  ticket new
  ticket add <prodId> <cantidad>
  ticket remove <prodId>
  ticket print

Sistema:
  help
  echo "<texto>"
  exit
```

Puedes visualizar los diagramas ONLINE en [PlantUML Online Server](http://www.plantuml.com/plantuml/uml/).