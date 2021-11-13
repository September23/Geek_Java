package com.example.Homework1;

// 导入的包
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XlassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {

        final String className = "Hello";
        final String methodName = "hello";

        // 创建一个类加载器
        ClassLoader classLoader = new XlassLoader();
        Class<?> clazz = classLoader.loadClass(className);
        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用实例方法
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        // 文件后缀
        final String suffix = ".xlass";
        // 获取输入 Hello.xlass
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + suffix);
        try {
            // 从输入读取字节
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            // 调用转换方法
            byte[] classBytes = decode(bytes);
            // 通知底层定义这个类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
    }

    // 自定义一个解码方法
    private static byte[] decode(byte[] inputBytes) {
        // 新建一个数组存放转换后的字节
        byte[] result = new byte[inputBytes.length];
        for (int i = 0; i < inputBytes.length; i++) {
            result[i] = (byte) (255 - inputBytes[i]);
        }
        return result;
    }

    // 关闭
    private static void close(Closeable res) {
        if (null != res) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}