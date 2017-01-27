 #! /bin/bash

#
: Script de generacion de apk
: Realizado por:
: Denis Quesada
: Jean Umaña
: Jose Arguedas
#



echo "_______________________________________________________________________________"


echo "
  ________                                       .___            
 /  _____/  ____   ____   ________________     __| _/___________ 
/   \  ____/ __ \ /    \_/ __ \_  __ \__  \   / __ |/  _ \_  __ \_
\    \_\  \  ___/|   |  \  ___/|  | \// __ \_/ /_/ (  <_> )  | \/
 \______  /\___  >___|  /\___  >__|  (____  /\____ |\____/|__|   
        \/     \/     \/     \/           \/      \/ "

echo "
               __            
_____  ______ |  | __  ______
\__  \ \____ \|  |/ / /  ___/
 / __ \|  |_> >    <  \___ \ 
(____  /   __/|__|_ \/____  >
     \/|__|        \/     \/ 
"
echo "_______________________________________________________________________________"




#
: Definicion de las rutas  
#
 rutaRes=PlantillaAPK/app/src/main/res/raw/
 rutaProyecto=PlantillaAPK
 rutaApk=app/build/outputs/apk/
 rutaManifest=PlantillaAPK/app/src/main
 rutaString=PlantillaAPK/app/src/main/res/values
 rutaLogo=PlantillaAPK/app/src/main/res/drawable
 rutaColor=PlantillaAPK/app/src/main/res/values
 rutaLogoPorDefecto=logo
 archivoStrings=strings.xml
 archivoColores=colors.xml
 archivo=datos_app.txt
 rutaHome=`pwd` 


#
: Valores por Defecto 
#
colorPorDefecto=3F51B5
imagenPorDefecto=logo.jpg


if [ ! $1 ]; then

	echo "    
    ***************************************************   
	  __________________  ___________ 
	_/ __ \_  __ \_  __ \/  _ \_  __ \_
	\  ___/|  | \/|  | \(  <_> )  | \/
	 \___  >__|   |__|   \____/|__|   
	     \/ 

    ************************************************** "

    echo "Nota: "
    echo "Le falta un parámetro"
    echo "Para hacer el apk de la aplicación es necesario el url del json de la radio que desea"
    echo "Como por ejemplo: ./scriptGenerador.sh http://radio.codigosur.org/radio/lavecindaria"
    exit
fi


 #
: Descargar el Json y moverlo a la carpeta de inicialización
#

wget -c --output-document=$archivo $1
mv -f $archivo $rutaRes
echo "Se descargó el json correctamente"




#
: Sacar el nombre de la aplicación 
#
cd $rutaHome
NombreApp=$(python obtenerNombre.py $1)




 #
: Cambiar el nombre de la aplicación
#

cd $rutaString
sed -i 's/name="app_name">.*</name="app_name">'"$NombreApp"'</g' $archivoStrings
echo "Se renombró la aplicación"


 #
: Cambiar el color de la aplicación
#
cd $rutaHome
color=$(python obtenerColor.py $1)


cd $rutaColor
if [ "$color" == "" ]; then
	#caso en el que no tiene color
	sed -i 's/name="colorPrimary">#.*</name="colorPrimary">#'"$colorPorDefecto"'</g' $archivoColores
	sed -i 's/name="colorAccent">#.*</name="colorAccent">#'"$colorPorDefecto"'</g' $archivoColores
    echo "Campo color vacío"
else
	#caso en el que debe cambiar los colores
	sed -i 's/name="colorPrimary">#.*</name="colorPrimary">#'"$color"'</g' $archivoColores
	sed -i 's/name="colorAccent">#.*</name="colorAccent">#'"$color"'</g' $archivoColores
    echo "Se cambió los colores"
fi



#
: Borrar el logo anterior
#
cd $rutaHome
cd $rutaLogo
rm logo.*


#
: Cambiar el logo
#
cd $rutaHome
UrlLogo=$(python obtenerLogo.py $1)


if [ "$UrlLogo" == "" ]; then
	#caso en el que no tenga logo
	cd $rutaLogoPorDefecto
	cp $imagenPorDefecto $rutaHome
	cd $rutaHome
	mv $imagenPorDefecto $rutaLogo
    echo "campo logo vacío"
else
	#caso en el que se debe remplazar el logo
	Logo= wget $UrlLogo 

	nombreBase="${UrlLogo##*/}"   
	echo $nombreBase

	extension="${nombreBase##*.}"
	#echo $extension

	nombreLogo="logo."
	nombreLogo="$nombreLogo$extension"
	echo $nombreLogo

	mv $nombreBase $nombreLogo
	mv $nombreLogo $rutaLogo
	echo "Se cambió el logo"
fi




#
: Compilar el proyecto
#
cd $rutaHome
cd $rutaProyecto
./gradlew assembleDebug




#
: Mover el apk para que el el directorio principal
#
cd $rutaApk
mv -f app-debug.apk $rutaHome
cd $rutaHome
nombreApk="$NombreApp"
nombreApk="$nombreApk.apk"
nombreApk="$(echo -e "${nombreApk}" | tr -d '[:space:]')"
mv app-debug.apk $nombreApk


echo "
_________________________________________________________

.__  .__          __          
|  | |__| _______/  |_  ____  
|  | |  |/  ___/\   __\/  _ \ 
|  |_|  |\___ \  |  | (  <_> )
|____/__/____  > |__|  \____/ 
             \/    
 _________________________________________________________
"