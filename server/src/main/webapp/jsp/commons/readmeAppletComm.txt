Infos sur port serie : http://www.supelec-rennes.fr/ren/fi/elec/docs/rs232/rs232.htm
page � visiter : http://www.opencard.org/archive/opencard/1374.html merci � Christophe.Muller@research.gemplus.com

Cot� Serveur : 
1) Compilation de la classe AppletImpression.java
	E:\java\j2sdk1.4\bin\javac -classpath comm.jar AppletImpression.java

2) Cr�ation du jar � partir du .class
	E:\java\j2sdk1.4\bin\jar cvf AppletImpression.jar AppletImpression.class
	On peut cr�er le jar en ajoutant l'api comm.jar mais le fichier javax.comm.properties se trouve obligatoirement dans la partie cliente ainsi que win32com.dll.
	Pour �viter que le client ait � t�l�charger l'api comm.jar, on rajoute cette api dans le nouveau jar(ceci permet aussi de faire moins de manip c�t� client).
	On d�sarchive le fichier comm.jar pour le r�-archiver ensuite. J'utilise winzip pour d�sarchiver.
	Puis pour archiver, on tape la commande suivant 
	E:\java\j2sdk1.4\bin\jar cvf AppletImpression.jar AppletImpression.class -C apicom\ .
	o� apicom est un r�pertoire que l'on a cr��e dans lequel on a mis l'api comm.jar d�sarchiv�
(autre chemin J:\j2sdk1.4.1\bin\jar.exe cvfM ImpressionTableApplet.jar  fr\montagnesdor\restaurant\applet\TableApplet.class fr\montagnesdor\restaurant\applet\ImpressionApplet.class -C tempCommAPI\ .)
(autre chemin J:\j2sdk1.4.1\bin\jar.exe cvfM DateTimeImpressionTableApplet.jar  fr\montagnesdor\restaurant\applet\DateTimeApplet.class fr\montagnesdor\restaurant\applet\TableApplet.class fr\montagnesdor\restaurant\applet\ImpressionApplet*.class -C tempCommAPI\ .)


3) Signature de l'applet voir http://developer.java.sun.com/developer/onlineTraining/Programming/JDCBook/signed.html

	E:\java\j2sdk1.4\bin\keytool -genkey -alias mysignfile -keystore mykeystore -keypass mykypwd -dname "cn=myname" -storepass mystpwd
(J:\j2sdk1.4.1\bin\keytool.exe  -genkey -alias mysignfile -keystore mykeystore -keypass mykypwd -dname "cn=myname" -storepass mystpwd)

	E:\java\j2sdk1.4\bin\jarsigner -keystore mykeystore -storepass mystpwd -keypass mykypwd -signedjar SAppletImpression.jar AppletImpression.jar mysignfile
(J:\j2sdk1.4.1\bin\jarsigner.exe  -keystore mykeystore -storepass mystpwd -keypass mykypwd -signedjar SDateTimeImpressionTableApplet.jar DateTimeImpressionTableApplet.jar mysignfile)

	E:\java\j2sdk1.4\bin\keytool -export -keystore mykeystore -storepass mystpwd -alias mysignfile -file mycompanycer.cer
(J:\j2sdk1.4.1\bin\keytool.exe -export -keystore mykeystore -storepass mystpwd -alias mysignfile -file mycompanycer.cer)


Cot� client :
--->SOUS WINDOWS
1) Installer le java plug-in (c'est le java runtime environnement).

2) Configurer le fichier java.policy qui se trouve g�n�ralement dans le r�pertoire dans F:\montagnesdor\j2sdk1.4.2_08\jre\lib\security
	Ajouter les lignes suivantes : 	
	permission java.util.PropertyPermission "javax.comm.properties", "read";
	permission java.io.FilePermission "C:\\Program Files\\Java\\j2re1.4.0_01\\lib\\javax.comm.properties", "read";
	permission java.util.PropertyPermission "java.home", "read";
	Peut-�tre inutile ? Pour Win2000, c'est inutile.		

3) Dans la console plug-in(elle se trouve dans Panneau de configuration), cliquer sur l'onglet Propri�t�s avanc�es, 
	et changer le java runtime environnement par d�faut pour celui qui se trouve dans le r�pertoire d'installation(F:\montagnesdor\j2sdk1.4.2_08).
	Peut-�tre inutile ? Pour Win2000, c'est inutile.
	
4) Copier le fichier javax.comm.properties dans le r�pertoire lib o� est install� le java-plug-in(g�n�ralement dans F:\montagnesdor\j2sdk1.4.2_08\jre\lib).

5) Copier le fichier win32com.dll dans le r�pertoire %WINNT%\System32.

6) Copier le fichier comm.jar dans le r�pertoire F:\montagnesdor\j2sdk1.4.2_08\jre\lib\ext.

7) Pour accepter l'applet sans confirmation de la part du client, copier le fichier mycompanycer.cer dans le r�pertoire que l'on voudra.
	Puis dans la console plug-in(elle se trouve dans Panneau de configuration), cliquer sur l'onglet Certificat, 
	et cliquer sur Importer pour importer le fichier certificat g�n�rer c�t� serveur(mycompanycer.cer).

--->SOUS LINUX (Voir http://wass.homelinux.net/howtos/Comm_How-To.shtml et http://jmri.sourceforge.net/install/Linux.html)
Installing RXTX
1) obtain the RXTX bins package from: http://www.linux.org.uk/~taj/rxtx-bins.1.tar.gz
	Decompress and Untar this package:
	/bin/gzip --decompress rxtx-bins.1.tar.gz
	/bin/tar xf rxtx-bins.1.tar

	At this point, you'll have an rxtx-bins.1 directory.  
	Next, you'll need to copy the shared objects into your java installation:

	cp rxtx-bins.1/1.4/i386-pc-linux/libSerial.so /usr/java/j2sdk1.4.0/jre/lib/i386/

	If you are installing on an architecture other than an x86, you'll need to adjust both the /i386-pc-linux/ and the /i386/ accordingly.

2) You'll need to install the jcl.jar file:

	cp rxtx-bins.1/1.4/jcl.jar /usr/java/j2sdk1.4.0/jre/lib/ext/

	At this point, the RXTX installation is complete.
	Installing Comm

	The final step to getting the Java Comm API working under Linux, is to install the Comm API itself.  At this point, you have all of the necessary kernel-level drivers installed.  Because Linux is a form of Unix, the authors of RXTX and JCL have wisely decided to reuse sun's solaris (unix) comm library.  At this point, you must download and install this library:

	http://java.sun.com/products/javacomm/  

	Make sure you choose the Solaris/SparcTM version.  Next, you must Decompress and Untar this package:

	/bin/gzip --decompress javax_comm-2_0_2-solsparc.tar.Z
	/bin/tar xf javax_comm-2_0_2-solsparc.tar

3) You'll need to install the comm.jar file:

	cp commapi/comm.jar /usr/java/j2sdk1.4.0/jre/lib/ext/

	At this point, we are almost finished.  We just need to create the properties file that the Comm API will use to load the drivers (.so files).  To create this file, type the following command:

4) /bin/echo Driver=gnu.io.RXTXCommDriver > /usr/java/j2sdk1.4.0/jre/lib/javax.comm.properties

5) Tips

	While windows uses COM and LPT designators for port identifiers, Linux is a bit different.  Use the following table to identify your ports:
	Port 	Windows port identifier 	What you use in Linux
	Serial Port 1 	COM1 	/dev/ttyS0
	Serial Port 2 	COM2 	/dev/ttyS1
	Parallel Port 1 	LPT1 	/dev/lp0

6) Troubleshooting

	Several people have emailed me and reported that they get the following error:
	
	Exception in thread "main" java.lang.VerifyError: (class:
	gnu/io/RXTXPort$SerialOutputStream, method: write signature: ([BII)V) Illegal
	use of nonvirtual function call
	at gnu.io.RXTXPort.<init>(RXTXPort.java)
	at gnu.io.RXTXCommDriver.getCommPort(RXTXCommDriver.java)
	at javax.comm.CommPortIdentifier.open(CommPortIdentifier.java:547)
	
	I personally have not experienced this problem, but if you encounter this error, you can work around it by adding -noverify to your command line:

	java -classpath your classpath -noverify your class (see file:///usr/java/j2sdk1.4.2_08/jre/ControlPanel.html with firefox sample -noverify fr.montagnesdor.restaurant.applets.PrinterApplet.class)

	Also, it has been suggested that recompiling the jcl with the java compiler you have chosen will resolve this issue.  As I have never experienced this verification problem, I have no way to test this.  It should also be noted that the purpose of this paper is to be a quick and easy way to get serial and parallel port access in Java on Linux.  Most of the instructions on this page as well as the VerifyError are irrelevant if you chose to download the RXTX source and do a manual compile and installation.
	
7) Give user access to lock files (required on Red Hat). 
	Edit /etc/group & /etc/gshadow to add user 
	--->to 'uucp' and 'lock' groups.	