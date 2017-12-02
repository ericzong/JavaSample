package com.ericzong.java.sample.generic;

/**
 * 与泛型相关的方法覆盖
 * 
 * @author Eric Zong
 */
public class GenericOverride {
    /* 1. 父子类型 - 非泛型类型 */
    /* 1.1 子类型非泛型方法覆盖父类型泛型方法 */
    class SuperClass1 {
        public <T> void method(T obj) {}
    }

    class SubClass1 extends SuperClass1 {
        @Override
        public void method(Object obj) {}
    }

    /* 1.2 子类型泛型方法覆盖父类型泛型方法 */
    class SuperClass2 {
        public <T> void method(T obj) {}
    }

    class SubClass2 extends SuperClass2 {
        public <S> void method(S obj) {}
    }

    /* 2. 父类型 - 非泛型类型；子类型 - 泛型类型 */
    /* 在泛型类型的方法声明中使用形式类型参数造成无法覆盖 */
    class SuperClass3 {
        public void method(Object obj) {}
    }

    class GenericeSubClass<S> extends SuperClass3 {
        // 无法覆盖
        // public void method(S obj) {
        // }
    }

    /* 3. 父类型 - 泛型类型；子类型 - 非泛型类型 */
    class GenericeSuperClass4<T> {
        public void method(T obj) {}
    }

    class SubClass4 extends GenericeSuperClass4<Number> {
        public void method(Number obj) {}
    }

    /* 4. 父子类型 - 泛型类型 */
    /* 4.1 父子类型泛型方法均使用形式类型参数 */
    class GenericeSuperClass5<T> {
        public void method(T Obj) {}
    }

    class GenericeSubClass5<S> extends GenericeSuperClass5<S> {
        public void method(S obj) {}
    }

    /* 4.2 形式类型参数包含上界 */
    class GenericeSuperClass6<T> {
        public void method(T obj) {}
    }

    class GenericeSubClass6<S extends Number> extends GenericeSuperClass6<S> {
        public void method(Number obj) {}
    }
}
