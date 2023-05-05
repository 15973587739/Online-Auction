package cn.exception;

/**
 * @author Siyu
 */
public class UnsupportedTypeException extends Exception {
    public UnsupportedTypeException(){
        super("不支持的类型转换");
    }
    public UnsupportedTypeException(Throwable e){
        super("不支持的类型转换",e);
    }
}
