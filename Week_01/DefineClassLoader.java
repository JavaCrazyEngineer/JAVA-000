import java.io.*;
import java.lang.reflect.InvocationTargetException;
/**
 * ClassName: DefineClassLoader
 * Description:
 *
 * @author limubai@geekbang!
 * @version week-01
 * @date 2020/10/21
 */
public class DefineClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String fileName = "C:\\Users\\YCKJ0837\\Documents\\Hello\\Hello.xlass";
        byte[] bytes = null;
        try (InputStream input = new FileInputStream(new File(fileName))) {
            int length = input.available();
            bytes = new byte[length];
            input.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, bytes, 0, bytes.length);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> hClass = new DefineClassLoader().findClass("Hello");
        Object object = hClass.newInstance();
        hClass.getMethod("hello").invoke(object);
    }
}
