package com.mine.project.content.util

object OperatorUse {
    lateinit var list1: List<Int>
    val list2 = listOf(22,121,434) /*list是不可变、有序、可重复*/
    var list3 = mutableListOf(21,323,4)/*mutableList是可变集合*/
    val map1 = mapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
    var map2 = mutableMapOf<String,Int>()
    val set1 = setOf(2,4,5,54,6,1,0,12,15,13,24,21)/*set不可变、无序、不可重复*/
    var set2 = mutableSetOf<Int>()


    var name: String? = null


    fun useNull(): Int {/*isInitialized判断是否为空*/
        var size = 0
        if (::list1.isInitialized) {
            size = list1.size
        }
        return size
    }

    fun useLength(): Int {/*?:符号表示前面为空返回后面的值*/
        return name?.length ?: -1
    }

    /**
     * 一个参数
     * message：String类型
     */
    private fun say(message: String,msg:String) {
        println(message)
    }

    /**
     * 两个参数
     * msg: String类型
     * dosay: (String) -> Unit 一个参数为String不需要返回值的方法体
     */
    fun peopleDo(msg: String, doSay: (String,String) -> Unit) {
        //doSay(msg)调用的就是传入的say方法,即say(msg),只不过参数名是doSay本质是say方法
        //此处打印出来 I say !  日志
        doSay(msg,msg)
    }

    fun initMap(){
        list1 = list3 as List<Int>
        set2.add(23)
        list3.add(3)
        map2.put("a",1)
        map2.put("b",2)
        map2.put("c",3)
        map2.put("d",4)
        map2.put("e",5)
        //遍历Map的key-value对，entris元素返回key-value对组成的Set
        for (en in map1.entries) {
            println("${en.key}->${en.value}")
        }
        //先遍历Map的key，再通过key获取value
        for (key in map2.keys) {
            println("${key}->${map2[key]}")
        }
        //直接用for-in循环遍历Map
        for ((key, value) in map2) {
            println("${key}->${value}")
        }
    }

    /*object的使用*/

    //抽象类和抽象方法
    abstract class Person{
        abstract  fun isAdult()
    }
    //接口
    interface AListener {
        fun getA()
    }
    //接口
    interface BListener {
        fun getB()
    }

    //继承一个抽象类的同时，来实现多个接口
    private val  item = object :Person(),AListener,BListener{
        override fun isAdult() {
            //do something
        }

        override fun getA() {
            //do something
        }

        override fun getB() {
            //do something
        }
    }
}