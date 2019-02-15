# PHomologacionesApp
## PASOS PARA CLONAR EL REPOSITORIO QUE CONTIENE LA BASE DEL PROYECTO
_**1. Forkeamos el repositorio presionando click en el boton fork**_

![github Logo](/tuto/forkear.PNG)

_**2. Presionamos en el boton Clone or download y copiamos la url que aparece alli**_

![github logo](/tuto/clonar.PNG)

_**3. Nos ubicamos en la carpeta en la que queremos que se clone el repositorio y preisonamos click derecho y abrimos la terminal Git Bash Here. Una vez alli escribimos el siguiente comando y presionamos enter**_ 
* git clone _url_que_copiamos_

![github logo](/tuto/gitclone.PNG)

_**4. Seguido de esto creamos nuestra rama en la que vamos a trabajar, y se puede crear de las 2 siguientes maneras**_
* git branch _nombre_rama_
* git checkout -b _nombre_rama_

_**5. Nos paramos en la rama que creamos. Para ver en que rama estamos situados utilizamos el siguiente comando**_
* git branch -l 
* _ó tambien_ git branch -v   _(para ver mas informacion)_
* _Con este comando te paras en la rama que quieres_  git checkout _nombre_rama_


## Comandos utiles
* Renombrar ramas
    * git branch -m [_nombre_rama_]  [_nuevo_nombre_]   (sin los corchetes)
    
* Renombrar un commit anterior
    * git commit --amend "se agrega el texto"

* Se devulve un paso es como hacer Ctrl +z
    * git rm --cached _nombre_archivo_
    
* Eliminacion forsoza de un archivo antes de hacer commit 
    * git rm -f _nombre_archivo_
    
 
