package com.kanjia.basic;

import java.util.Vector;

/**
 * 被观察者接口，实现的时候需要用一个列表来存观察者，因为java中提供的被观察者为抽象类，而我业务中已经有一个父类了，所以只能自己实现观察者模式<br/>
 * fan 2018/8/21 20:24
 */
public interface Observable<T extends Observer> {
    /**
     * 添加观察者
     * @param observer
     */
    boolean addObserver(T observer);

    /**
     * 移除观察者
     */
    boolean removeObserver(T observer);

    /**
     * 通知列表中的观察者更新
     */
    void notifyObserver();
}