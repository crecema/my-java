package crece.ma.design.singleton.preload;

/**
 * 简单的预加载模式，线程安全
 */
public class SingletonV1 {

    private static final SingletonV1 instance = new SingletonV1();

    private SingletonV1() {}

    public static SingletonV1 getSingleton() {
        return instance;
    }

}
