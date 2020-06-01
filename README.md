# tomcat-ldap-ext-jndi

Purpose:

	Testing external JNDI via LDAP for Tomcat.


Prerequisites:

	- Java 8 JDK
	- Maven


Environment:

	- Pega 8.2
	- Linux(Centos 7)
	- Docker 19.03.8
	- Tomcat 9
	- Oracle 19C
	- IBM MQ 9.1.5.0
	- OpenLDAP 1.3.0


Build:
	
	- Clone/fork repo and execute "mvn package" in dir.
	- "mvn clean" to cleanup previous built.
	- Make packaged jar file available to JVM.


Example Tomcat resource (returns a connection and a queue factory object):

	<Resource
                name="jms/MyQCFLdap"
                auth="Container"
                type="com.ibm.mq.jms.MQQueueConnectionFactory"
                factory="test.tomcat.ldap.jndi.LdapContextFactory"
                singleton="false"
                java.naming.referral="follow"
                java.naming.factory.initial="com.sun.jndi.ldap.LdapCtxFactory"
                java.naming.provider.url="ldap://172.17.0.6:389"
                java.naming.security.authentication="simple"
                java.naming.security.principal="cn=admin,dc=example,dc=org"
                java.naming.security.credentials="admin"
                com.sun.jndi.ldap.connect.pool="true"
                com.sun.jndi.ldap.connect.pool.maxsize="10"
                com.sun.jndi.ldap.connect.pool.prefsize="4"
                com.sun.jndi.ldap.connect.pool.timeout="30000"
                providerLookup="cn=myq,dc=example,dc=org"
    />

    <Resource
                name="jms/MyQLdap"
                auth="Container"
                type="com.ibm.mq.jms.MQQueue"
                factory="test.tomcat.ldap.jndi.LdapContextFactory"
                description="JMS Queue for receiving messages"
                singleton="false"
                java.naming.referral="follow"
                java.naming.factory.initial="com.sun.jndi.ldap.LdapCtxFactory"
                java.naming.provider.url="ldap://172.17.0.6:389"
                java.naming.security.authentication="simple"
                java.naming.security.principal="cn=admin,dc=example,dc=org"
                java.naming.security.credentials="admin"
                com.sun.jndi.ldap.connect.pool="true"
                com.sun.jndi.ldap.connect.pool.maxsize="10"
                com.sun.jndi.ldap.connect.pool.prefsize="4"
                com.sun.jndi.ldap.connect.pool.timeout="30000"
                providerLookup="cn=bpm,dc=example,dc=org"
    />