# ITEM 8 finalizer와 cleaner 사용을 피하라

### try-with-resource, AutoCloseable
AutoCloseable 인터페이스를 상속받은 클래스만 사용 가능
try문에서 객체를 생성해주고 try문이 종료시 자동으로 AutoCloseable의 close() 메소드 호출(이때, 자원 해제)

* try-catch-finally
    ```java
    import java.io.BufferedInputStream;
    import java.io.FileInputStream;
    import java.io.IOException;
    
    public class TraditionalResourceCloseable {
    
        public static void main(String[] args) throws IOException {
    
            FileInputStream is = null;
            BufferedInputStream bis = null;
    
            try{
                is = new FileInputStream("/Users/test.txt");
                bis = new BufferedInputStream(is);
    
                int n = -1;
                while ((n = bis.read()) != -1) {
                    System.out.println((char) n);
                }
    
            }finally {
               if (is != null) {
                   is.close();
                }
               
               if (is != null) {
                   bis.close();
                }
            }
        }
    }
    ```
  * try-with-resources
    ```java
    import java.io.BufferedInputStream;
    import java.io.FileInputStream;
    import java.io.IOException;
    
    public class TraditionalResourceCloseable {
    
        public static void main(String[] args) {
    
            // try-with-resources로 자원 해제
            try (
                    FileInputStream is = new FileInputStream("/Users/test.txt");
                    BufferedInputStream bis = new BufferedInputStream(is);
            ) {
                int n = -1;
                while ((n = bis.read()) != -1) {
                    System.out.println((char) n);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

### 네이티브 피어
일반 자바 객체가 네이티브 메서드를 통해 C나 C++로 구현된 네이티브 객체에게 기능 수행을 위임하는것
일반 객체가 아니기때문에 가비지 컬렉터를 통해 해제할 수 없으므로 finalizer를 사용하여 네이티브 객체의 자원 해제 작업을 할 수 있다.
