package cn.auction.util;


import cn.exception.UnsupportedTypeException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class SimpleBeanConvertor {
    public static <T> T convert(HttpServletRequest request, Class<T> targetType) throws Exception, UnsupportedTypeException {
        Map<String,String[]> parames = request.getParameterMap();
        if(parames.size() == 0) {
            return null;
        }
        T target = targetType.newInstance();
        Method[] allMethods = targetType.getMethods();
        if (allMethods != null && allMethods.length > 0){
            for(Method method : allMethods){
                if(method.getName().startsWith("set")){
                    Class[] args = method.getParameterTypes();
                    if (args.length == 1){
                        String paramName = method.getName().substring(3,4).toLowerCase()+method.getName().substring(4);
                        if (parames.containsKey(paramName)){
//                            try {
                                Object value = parseValus(parames.get(paramName),args[0]);
                                method.invoke(target,value);
//                            }catch (ParseException e){
//                                logger.debug("参数转换错误",e);
//                                e.printStackTrace();
//                                throw e;
//                            }
                        }else if(Boolean.class == args[0] || boolean.class == args[0]){
                            method.invoke(target,false);
                        }
                    }
                }
            }
        }
        return target;
    }

    private static Object parseValus(String[] value, Class type) throws ParseException, UnsupportedOperationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, UnsupportedTypeException {
        if (String.class == type){
            return value[0];
        }
        if (java.util.Date.class == type){
            return new SimpleDateFormat("yyyy-MM-dd").parse(value[0]);
        }
        if (boolean.class == type || Boolean.class == type){
            return true;
        }
        if (char.class == type || Character.class == type){
            return value[0].charAt(0);
        }
        if (int.class == type || Integer.class == type){
            return Integer.valueOf(value[0]);
        }
        if (short.class == type || byte.class == type || long.class == type || float.class == type || double.class == type){
            type = Class.forName("java.lang."+type.getName().substring(0,1).toLowerCase()+type.getName().substring(1));
        }
        if (type.getName().startsWith("java.lang.") || type.getName().startsWith("java.math.")) {
            return type.getDeclaredConstructor(String.class).newInstance(value[0]);
        }
        throw new UnsupportedTypeException();
    }
}
