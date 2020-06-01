package test.tomcat.ldap.jndi;

import javax.naming.spi.ObjectFactory;
import javax.naming.RefAddr;
import javax.naming.Name;
import javax.naming.Context;
import javax.naming.Reference;
import javax.naming.ldap.InitialLdapContext;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.logging.Logger;

public class LdapContextFactory implements ObjectFactory {

	private static final Logger LOGGER = Logger.getLogger( LdapContextFactory.class.getName() );

  public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
      throws Exception {

      LOGGER.info("starting custom LDAP external JNDI");    

      Hashtable<Object, Object> env = new Hashtable<>();

      Reference reference = (Reference) obj;

      Enumeration<RefAddr> references = reference.getAll();

      String providerLookup = null;

      while (references.hasMoreElements()) {

          RefAddr address = references.nextElement();

          String type = address.getType();

          String content = (String) address.getContent();

          if (type.equals("providerLookup")) {

            providerLookup = content; 

            LOGGER.info("setting providerLookup " + content);

          } 

          //LOGGER.info("type: " + type + " content: " + content);

          env.put(type, content);
      }

      InitialLdapContext ctx = new InitialLdapContext(env, null);

		  Object LdapFactoryobj = (Object) ctx.lookup(providerLookup);
	   
	    //LOGGER.info("Object: " + LdapFactoryobj);
      LOGGER.info("ending custom LDAP external JNDI");  

      return LdapFactoryobj;

  }

}
