# Getting Started

Bienvenido al documento de ayuda para empezar a utilizar la prueba técnica. Aquí encontrarás información sobre cómo configurar y probar las funcionalidades principales utilizando la base de datos H2.

## Configuración Básica

### Base de Datos H2

Para interactuar con la base de datos durante el desarrollo, puedes acceder a la consola de H2:

- [Ver consola de H2](http://localhost:8080/PruebaTecnicaIPS-app/h2-console)

Asegúrate de que tu aplicación esté ejecutándose para poder acceder a esta dirección.

- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:PruebaTecnicaIPS-app`
- User: `root`
- Contraseña:

## Funcionalidades requeridas

### Primera Funcionalidad: Obtener Saldo por Caja y Fecha

Puedes probar esta funcionalidad utilizando el siguiente enlace:

- [obtenerSaldoPorCajaYFecha](http://localhost:8080/PruebaTecnicaIPS-app/api/cajas/saldo?numeroCaja=1&fecha=2023-01-01)

#### Consideraciones Especiales:

He implementado una excepción de "caja duplicada" para prevenir registros con el mismo número de caja en la misma fecha. Esto es importante para evitar duplicidades en los registros.

**Ejemplo**: Si intentas consultar el saldo de una caja con número `1` en la fecha `2023-01-01`, recibirás el saldo correspondiente, siempre y cuando no se haya registrado más de una caja con esos mismos datos para esa fecha.

### Segunda Funcionalidad: Obtener Saldo Total en una Fecha Específica

Esta funcionalidad permite ver el saldo total acumulado en todas las cajas para una fecha específica:

- [obtenerSaldoTotalEnFecha](http://localhost:8080/PruebaTecnicaIPS-app/api/cajas/saldo-total?fecha=2023-01-01)

No he implementado excepciones específicas aquí, aunque debería darse la misma situacion que en la anterior.

# Tests

En esta sección se explican brevemente los tests implementados tanto a nivel del controlador como del servicio para asegurar la funcionalidad correcta de la aplicación.

## Tests del Controlador

### 1. Test Obtener Saldo por Caja y Fecha
Este test verifica que se pueda obtener correctamente el saldo de una caja específica en una fecha determinada. Se simula una solicitud HTTP y se espera que la respuesta tenga un estado `200 OK` con el saldo esperado.

### 2. Test de Fecha Inválida
Verifica la gestión de fechas inválidas en las solicitudes. Si la fecha no es válida, el servidor debe retornar un estado `400 Bad Request`.

### 3. Test Obtener Saldo Total en Fecha
Este test comprueba que se pueda obtener el saldo total acumulado en todas las cajas en una fecha específica. Se espera que la respuesta devuelva el saldo total esperado con un estado `200 OK`.

## Tests del Servicio

### 1. Test Obtener Saldo por Caja y Fecha
Confirma que el servicio puede calcular correctamente el saldo de una caja en una fecha dada. Si hay registros, retorna el saldo; si no, retorna `0`.

### 2. Test Obtener Saldo por Caja y Fecha Sin Registros
Verifica que, en ausencia de registros para una caja y fecha específicas, el saldo retornado sea `0`.

### 3. Test Obtener Saldo por Caja y Fecha con Registros Duplicados
Prueba la situación donde múltiples registros para la misma caja y fecha pueden causar una excepción, asegurando que el sistema maneje correctamente tales inconsistencias.

### 4. Test Obtener Saldo Total en Fecha
Evalúa si el servicio puede sumar correctamente los saldos de todas las cajas en una fecha específica, incluso cuando hay múltiples registros.

Estos tests aseguran que tanto la lógica del negocio como la interfaz de usuario funcionen como se espera, manejando adecuadamente tanto los datos correctos como los casos de error.
