// 
//package org.platform.common.utils;
//
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
///** 
// * Description: TODO[描述该类概要功能介绍]
// *
// * @author zhangbo
// * @date 2013-2-25
// * @version 1.0 
// */
//public class BeanUtil extends BeanUtils
//{
//
//    private BeanUtil()
//    {
//    	
//    }
//
//    public static void copyPropertiesWithoutNull(Object orig, Object desc)
//        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
//    {
//        PropertyDescriptor pds[] = PropertyUtils.getPropertyDescriptors(orig.getClass());
//        for(int i = 0; i < pds.length; i++)
//            if(PropertyUtils.isReadable(orig, pds[i].getName()) && PropertyUtils.isWriteable(orig, pds[i].getName()))
//            {
//                Object value = PropertyUtils.getProperty(orig, pds[i].getName());
//                if(value != null)
//                    PropertyUtils.setProperty(desc, pds[i].getName(), value);
//            }
//
//    }
//
//    public static Object getDeclaredProperty(Object object, String propertyName)
//        throws IllegalAccessException, NoSuchFieldException
//    {
//        Assert.notNull(object);
//        Assert.hasText(propertyName);
//        Field field = object.getClass().getDeclaredField(propertyName);
//        return getDeclaredProperty(object, field);
//    }
//
//    public static Object getDeclaredProperty(Object object, Field field)
//        throws IllegalAccessException
//    {
//        Assert.notNull(object);
//        Assert.notNull(field);
//        boolean accessible = field.isAccessible();
//        field.setAccessible(true);
//        Object result = field.get(object);
//        field.setAccessible(accessible);
//        return result;
//    }
//
//    public static void setDeclaredProperty(Object object, String propertyName, Object newValue)
//        throws IllegalAccessException, NoSuchFieldException
//    {
//        Assert.notNull(object);
//        Assert.hasText(propertyName);
//        Field field = object.getClass().getDeclaredField(propertyName);
//        setDeclaredProperty(object, field, newValue);
//    }
//
//    public static void setDeclaredProperty(Object object, Field field, Object newValue)
//        throws IllegalAccessException
//    {
//        boolean accessible = field.isAccessible();
//        field.setAccessible(true);
//        field.set(object, newValue);
//        field.setAccessible(accessible);
//    }
//
//    public static Object invokePrivateMethod(Object object, String methodName, Object params[])
//        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
//    {
//        Assert.notNull(object);
//        Assert.hasText(methodName);
//        Class types[] = new Class[params.length];
//        for(int i = 0; i < params.length; i++)
//            types[i] = params[i].getClass();
//
//        Method method = object.getClass().getDeclaredMethod(methodName, types);
//        boolean accessible = method.isAccessible();
//        method.setAccessible(true);
//        Object result = method.invoke(object, params);
//        method.setAccessible(accessible);
//        return result;
//    }
//
//    public static List getFieldsByType(Object object, Class type)
//    {
//        ArrayList list = new ArrayList();
//        Field fields[] = object.getClass().getDeclaredFields();
//        Field afield[];
//        int j = (afield = fields).length;
//        for(int i = 0; i < j; i++)
//        {
//            Field field = afield[i];
//            if(field.getType().isAssignableFrom(type))
//                list.add(field);
//        }
//
//        return list;
//    }
//
//    public static String getAccessorName(Class type, String fieldName)
//    {
//        Assert.hasText(fieldName, "FieldName required");
//        Assert.notNull(type, "Type required");
//        if(type.getName().equals("boolean"))
//            return (new StringBuilder("is")).append(StringUtils.capitalize(fieldName)).toString();
//        else
//            return (new StringBuilder("get")).append(StringUtils.capitalize(fieldName)).toString();
//    }
//
//    public static Method getAccessor(Class type, String fieldName)
//    {
//        try
//        {
//            return type.getMethod(getAccessorName(type, fieldName), new Class[0]);
//        }
//        catch(NoSuchMethodException e)
//        {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    public static Object getPrivateProperty(Object object, String propertyName)
//        throws IllegalAccessException, NoSuchFieldException
//    {
//        Assert.notNull(object);
//        Assert.hasText(propertyName);
//        Field field = object.getClass().getDeclaredField(propertyName);
//        field.setAccessible(true);
//        return field.get(object);
//    }
//
//    public static void setPrivateProperty(Object object, String propertyName, Object newValue)
//        throws IllegalAccessException, NoSuchFieldException
//    {
//        Assert.notNull(object);
//        Assert.hasText(propertyName);
//        Field field = object.getClass().getDeclaredField(propertyName);
//        field.setAccessible(true);
//        field.set(object, newValue);
//    }
//
//    public static void instantiateNullProperty(Object bean, String property)
//        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException
//    {
//        String props[] = StringUtils.split(property, '.');
//        Object target = bean;
//        for(int i = 0; i < props.length; i++)
//        {
//            Object propValue = PropertyUtils.getProperty(target, props[i]);
//            if(propValue == null)
//            {
//                Class clazz = PropertyUtils.getPropertyType(target, props[i]);
//                propValue = clazz.newInstance();
//                PropertyUtils.setProperty(target, props[i], propValue);
//            }
//            target = propValue;
//        }
//
//    }
//
//    protected static final Log logger = LogFactory.getLog("com.core.util.BeanUtil");
//
//}
