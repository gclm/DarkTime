## 题目描述：

设计一个类，我们只能生成该类的一个实例

## 考察点

- 对单例模式的理解
- 考察对 Java 基础语法的理解，如静态构造器
- 对多线程的理解

## 概念解读

- 懒汉式

懒汉式是适合单线程，多线程操作需要加锁，关键字（Synchronized）,如果不加会导致对类的访问时线程不安全的，不能保证是单例实现

- 饿汉式

饿汉式是线程安全的，在类创建的本身就已经实例化好一个静态对象供系统调用，不在改变


> 区别

懒汉式是延迟加载，他在需要的时候才创建对象。而饿汉式是类创建的时候就进行加载了

> 优缺点

懒汉式优点是延迟加载，不过需要考虑线程问题
饿汉式优点是用法简单，但是需要考虑效率问题，如果在工厂模式中一下子实例化这么多对象，消耗估计就很大了。


## 代码实现

**懒汉式线程不安全**

```java
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
```
懒汉式线程安全（加锁）
```
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
public class Singleton2 {

    /**
     *  懒汉式线程安全单例模式
     *
     *  1. 私有化构造函数
     *  2. 创建静态方法，加锁
     *
     */

    private Singleton2() {
    }
    
    private static Singleton2 instance = null;

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
    public static synchronized Singleton2 getInstance() {
        if (instance == null ){
            instance = new Singleton2();
        }
        return instance;
    }



}
```

懒汉式双重效验锁
```
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
```
饿汉式线程安全
```
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

```
最佳实现

```
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
```