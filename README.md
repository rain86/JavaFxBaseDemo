# JavaFxBaseDemo
JavaFx界面基础类，一些实现不同模块随意组合的基础类

刚学JavaFx的时候会遇见如何实现界面跳转，
还有很多界面的模块都有重用，怎么实现不同模块在界面的随意组合。下面直接上代码来解决问题。

下面demo中其实只有两个界面，
一个是登陆界面LoginController.java，
一个是主界面MainController.java。(其他模块随意组合拼成不同的主界面)

Hospital.java 是程序的入口他继承了Application并且实现了main方法
继承Application就会实现其中的start方法就是新建打开界面


