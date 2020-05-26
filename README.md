# tomcat-ldap-ext-jndi

Testing external JNDI via LDAP for Tomcat.

Prerequisites:

	- Java 8 JDK
	- Maven

Build:
	
	- Clone/fork repo and execute "mvn package" in dir.
	- "mvn clean" to cleanup previous built.


Example Tomcat resource:

	<Resource name="ldapConnection" 
	        auth="Container"
	        type="javax.naming.ldap.LdapContext"
	        factory="test.tomcat.ldap.jndi.LdapContextFactory"
	        singleton="false"
	        java.naming.referral="follow"
	        java.naming.factory.initial="com.sun.jndi.ldap.LdapCtxFactory"
	        java.naming.provider.url="ldap://some.host:389"
	        java.naming.security.authentication="simple"
	        java.naming.security.principal="CN=some,OU=some,OU=some,DC=some,DC=a,DC=b"
	        java.naming.security.credentials="password"
	        com.sun.jndi.ldap.connect.pool="true"
	        com.sun.jndi.ldap.connect.pool.maxsize="10"
	        com.sun.jndi.ldap.connect.pool.prefsize="4"
	        com.sun.jndi.ldap.connect.pool.timeout="30000" />
