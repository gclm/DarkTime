package club.gclmit.groud.interviews.singleton;

/**
 * <p>
 *   题目描述：
 *   设计一个类，我们只能生成该类的一个实例
 *
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/15 10:15
 * @version: V1.0
 * @since 1.8
 */
public class Singleton3 {

    /**
     *  懒汉式线程双重效验锁单例模式
     *
     *  1. 私有化构造函数
     *  2. 创建静态方法，加锁
     *  3. 加同步锁前后两次判断对象是否为空，缩小同步块
     */

    private Singleton3() {
    }
    
    private static Singleton3 instance = null;

    /**
     * <p>
     *  创建静态常量
     * </p>
     *
     * @author gclm
     * @date 2019/11/15 10:28
     * @return: club.gclmit.groud.interviews.singleton.Singleton1
     * @throws
     */
    public static synchronized Singleton3 getInstance() {
        if (instance == null ){
            synchronized (Singleton3.class){
                if (instance == null ){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
