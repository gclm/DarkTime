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
public class Singleton4 {

    /**
     *  饿汉式单例模式
     *
     *  1. 私有化构造函数
     *  2. 直接实例化类
     *
     *  特点： 丢失延迟实例化代码的节约资源的优势
     */

    private Singleton4() {
    }
    
    private static Singleton4 instance = new Singleton4();

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
    public static Singleton4 getInstance() {
        return instance;
    }



}
