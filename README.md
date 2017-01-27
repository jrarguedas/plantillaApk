# Plantilla Generadora de Apk
  El proyecto consiste en un generador de apk's que permite a los oyentes escuchar el streaming de radio que más les gusta, más facilmente; ya que este proyecto pretente que a los radioescuchas les sea más sencillo el poder escuchar su radio a través de una aplicación móvil, siendo esta una oportunidad para que las radios puedan atraer mayor cantidad de oyentes.


## Herramientas
* Android Studio
* AVD (Android virtual Device)
* Git

## Instalación
1.Clonar el proyecto:
```
git clone git@github.com:josetito/plantillaApk.git
```
2.Permisos al script:
```
chmod +x scriptGenerador.sh
```

## Dependencias
* Java 8
* Python 2.7

## Uso

### Api
Usamos una Api brindada por CódigoSur que esta en http://radio.codigosur.org/admin/rappdio/radio/ 
Se encuentra protegido por el sistema de autenticación de Django, por lo que es necesario ingresar con un usuario y contraseña.

#### Agregar Radio

Para agregar una radio se debe ingresar a este link:  http://radio.codigosur.org/admin/rappdio/radio/ y rellenar el fórmulario con la información solicitada.
Es estrictamente necesario los campos: 
* Nombre
* Punto de montaje

#### Consultar Radio
Para consultar una radio se debe ingresar a este link: http://radio.codigosur.org/radio/  + el punto de montaje de la radio que quiero consultar.
El mismo devuelve un Json con la información que se tiene de la radio.

### Compilación (Generación del Apk)
Para la generación del Apk se debe de correr el scriptGenerador.sh que lo que hace es procesar el Json de la radio que se pasa por parámetro para construir la aplicación con los datos suministrados a través del formulario.
Una vez que la aplicación se inicializa con los datos se procede a generar la Apk.

#### Ejemplo de ejecución
```
./scriptGenerador.sh http://radio.codigosur.org/radio/lavecindaria
```
