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
public class Singleton5 {

    /**
     *  懒汉式优化方案
     *
     *  1. 创建静态方法
     *
     *  优点： SingletonHolder中可以使用静态方法替换静态域, 实现比较复杂的逻辑,而不仅仅是new Singleton()这样简单地调用构造方法
     *
     */
    private static class SingletonHandler {
        static  Singleton5 instance = new Singleton5();
    }

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
    public static Singleton5 getInstance() {
        return SingletonHandler.instance;
    }

}
