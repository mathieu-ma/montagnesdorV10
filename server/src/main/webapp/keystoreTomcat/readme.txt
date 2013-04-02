#Ce fichier permet de crée�r un keystore JAVA pour faire tourner Tomcat en mode https
#Il faut que le password du certificat(dont l'alias est tomcat) soit le m�ême que celui du keystore(dont le nom est tomcatSSLkeystore)
#Dans notre cas c'est : "powermax"
J:\j2sdk1.4.1\bin\keytool -genkey -keystore tomcatSSLkeystore -alias tomcat -keyalg RSA
(/usr/java/j2sdk1.4.2/bin/keytool -genkey -keystore tomcatSSLkeystore -alias tomcat -keyalg RSA)


#Attention !!! Il faut ensuite changer le fichier de server.xml de Tomcat :
#Le fichier tomcatSSLkeystore ���doit être placé �dans TOMCAT_HOME

#	Pour TOMCAT 4 
# <!-- Define an SSL HTTP/1.1 Connector on port 8443 -->
#    <Connector className="org.apache.catalina.connector.http.HttpConnector"
#               port="8443" minProcessors="5" maxProcessors="75"
#               enableLookups="true"
#	       acceptCount="10" debug="0" scheme="https" secure="true">
#      <Factory className="org.apache.catalina.net.SSLServerSocketFactory"
#               clientAuth="false" keystoreFile="tomcatSSLkeystore" keystorePass="powermax" protocol="TLS"/>
#    </Connector>

#	Pour TOMCAT 5
	 <Connector port="8443" 
               maxThreads="150" minSpareThreads="25" maxSpareThreads="75"
               enableLookups="false" disableUploadTimeout="true"
               acceptCount="100" debug="0" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" keystoreFile="/usr/local/jakarta-tomcat-5.0.19/tomcatSSLkeystore" keystorePass="powermax"/>

Voir aussi http://httpunit.sourceforge.net/doc/sslfaq.html

# Pour TOMCAT 5 on peut compresser les pages en utilisant les attributs suivants dans l'éléments Connector
		   compression="on" 
		   compressionMinSize="2048" 
		   noCompressionUserAgents="gozilla, traviata" 
		   compressableMimeType="text/html,text/xml,text/css,application/x-javascript,application/java-archive"
Exemple:
   <Connector 
port="8080"               maxThreads="150" minSpareThreads="25" maxSpareThreads="75"
               enableLookups="false" redirectPort="8443" acceptCount="100"
               debug="0" connectionTimeout="20000" 
               disableUploadTimeout="true" 
		   compression="on" 
		   compressionMinSize="2048" 
		   noCompressionUserAgents="gozilla, traviata" 
			compressableMimeType="text/html,text/xml,text/css,application/x-javascript,application/java-archive"
	/>
