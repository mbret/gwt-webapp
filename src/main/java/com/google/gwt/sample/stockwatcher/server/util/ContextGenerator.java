package com.google.gwt.sample.stockwatcher.server.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ContextGenerator {
    
    public static InitialContext getContext(){
        InitialContext ctx = null;
        Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        try {
            ctx = new InitialContext(props);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ctx;
    }
    
    public static String getPath(String ob1, Class obj){
        String path = "java:global/gwt_webapp_ejb_exploded/" + ob1 + "!com.google.gwt.sample.stockwatcher.shared.remote." + obj.getSimpleName();
        System.out.println(path);
        return path;
        
    }
}
