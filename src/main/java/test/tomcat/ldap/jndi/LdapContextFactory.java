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

    // public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
    //     throws Exception {

    //     Hashtable<Object, Object> env = new Hashtable<>();

    //     Reference reference = (Reference) obj;

    //     Enumeration<RefAddr> references = reference.getAll();

    //     while (references.hasMoreElements()) {

    //         RefAddr address = references.nextElement();

    //         String type = address.getType();

    //         String content = (String) address.getContent();

    //         env.put(type, content);
    //     }

    //     return new InitialLdapContext(env, null);

    // }

	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
		throws Exception {

		Hashtable<Object, Object> env = new Hashtable<>();

		Reference reference = (Reference) obj;

		Enumeration<RefAddr> references = reference.getAll();

		String providerUrl = "no valid URL";

		while (references.hasMoreElements()) {

			RefAddr address = references.nextElement();

			String type = address.getType();

			String content = (String) address.getContent();

			switch (type) {

				case Context.PROVIDER_URL:

					env.put(Context.PROVIDER_URL, content);

					providerUrl = content;

					break;

				default:

					env.put(type, content);

					break;
			}
		}

		InitialLdapContext context = null;

		Object result = null;

			try {

				context = new InitialLdapContext(env, null);

				LOGGER.info("looking up for " + providerUrl);

				result = context.lookup(providerUrl);
			
			} finally {
				
				if (context != null) {
					context.close();	
				}
			}

		LOGGER.info("Created new LDAP Context");

		return result;

	}
}
