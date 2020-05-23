package test.tomcat.ldap.jndi;

import javax.naming.spi.ObjectFactory;
import javax.naming.RefAddr;
import javax.naming.Name;
import javax.naming.Context;
import javax.naming.Reference;
import javax.naming.ldap.InitialLdapContext;
import java.util.Hashtable;
import java.util.Enumeration;

public class LdapContextFactory implements ObjectFactory {

    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
        throws Exception {

        Hashtable<Object, Object> env = new Hashtable<>();

        Reference reference = (Reference) obj;

        Enumeration<RefAddr> references = reference.getAll();

        while (references.hasMoreElements()) {

            RefAddr address = references.nextElement();

            String type = address.getType();

            String content = (String) address.getContent();

            env.put(type, content);
        }

        return new InitialLdapContext(env, null);

    }
}
