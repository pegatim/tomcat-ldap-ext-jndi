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

public class LdapCtxMQFactory implements ObjectFactory {

	private static final Logger LOGGER = Logger.getLogger( LdapContextFactory.class.getName() );

    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
        throws Exception {

        Hashtable<Object, Object> env = new Hashtable<>();

        Reference reference = (Reference) obj;

        Enumeration<RefAddr> references = reference.getAll();

        String providerLookup = null;

        while (references.hasMoreElements()) {

            RefAddr address = references.nextElement();

            String type = address.getType();

            String content = (String) address.getContent();

            if (type == Context.PROVIDER_LOOKUP) {

            	providerLookup = content; 

            	LOGGER.info("Set providerLookup " + content);

            }

            LOGGER.info("type: " + type + " content: " + content);

            env.put(type, content);
        }

        InitialLdapContext ctx = new InitialLdapContext(env, null);
  
  		Object MQobj = (Object) ctx.lookup(providerLookup);
  	   
  	    LOGGER.info("Object: " + MQobj);

        return MQobj;

    }

}
