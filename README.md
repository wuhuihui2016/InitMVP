# InitMVP
mvp+okhttp3+retrofit2+rxjava2

利用mvp+okhttp3+retrofit2+rxjava2搭建框架实现网络请求及刷新UI数据。

MVP(Model-View-Presenter) :从经典的模式MVC演变而来，Presenter负责逻辑的处理，Model提供数据，View负责显示。
MVP的优点：(1)降低耦合度；(2)模块职责划分明显；(3)利于测试驱动开发；(4)代码复用；(5)隐藏数据；(6)代码灵活性

OkHttp3：Square公司的一款开源的网络请求库。
Retrofit：Square公司开发的一款针对Android 网络请求的框架（底层默认是基于OkHttp 实现）。
RxJava2：一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库），RxJava使异步操作变得非常简单。

HttpLoggingInterceptor：日志拦截器，okHttp记录拦截器，用于记录应用中的网络请求的信息。

三个网络请求例子：
由于三个请求API的base_url都不同，需要初始化不同的retrofit，初始化方法在调用时切换
private RetrofitHelper(Context context) {
        this.context = context;
//        initRetrofit(); //版本信息
//        initWeatherRetrofit(); //天气信息
        initGitUserRetrofit();  //https gitUser信息
    }
建立不同Activity，在manifest.xml切换不同的activity为启动页
1.版本信息：post请求，retrofit初始化方法initRetrofit，activity为AppVersionActivity。目的：框架的初实现
2.天气信息：get请求，retrofit初始化方法initWeatherRetrofit，activity为WeatherActivity。目的：熟练框架的使用
3.gitUser信息：get请求，retrofit初始化方法initGitUserRetrofit，activity为GitUserActivity。目的：尝试https在框架的实现，实现方法并无差别。

EventBus能够简化各组件间的通信，让我们的代码书写变得简单，能有效的分离事件发送方和接收方(也就是解耦的意思)，能避免复杂和容易出错的依赖性和生命周期问题。
EventBus可以代替Android传统的Intent,Handler,Broadcast或接口函数,在Fragment,Activity,Service线程之间传递数据，执行方法
特点:代码简洁，是一种发布订阅设计模式(观察者设计模式)。
https://baike.baidu.com/item/EventBus/20461274










