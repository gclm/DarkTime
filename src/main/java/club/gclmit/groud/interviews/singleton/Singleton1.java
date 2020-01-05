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
public class Singleton1 {

    /**
     *  懒汉式线程不安全单例模式
     *
     *  实现方法：
     *
     *  1. 私有化构造函数
     *  2. 设置静态实例,判断私有的 Singleton 是否为空，为空则进行 new Singleton
     *
     *  特点： 只能在单线程使用
     */

    private Singleton1() {
    }

    private static Singleton1 instance = null;

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
    public static  Singleton1 getInstance() {

        if (instance == null) {
            instance = new Singleton1();
        }

        return instance;
    }



}
