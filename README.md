# InitMVP
github：https://github.com/wuhuihui2016/InitMVP.git

====================================================================================================================================

一、mvp+okhttp3+retrofit2+rxjava2

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

框架实现整理文章：https://www.jianshu.com/p/3625061c98ad

====================================================================================================================================

二、EventBus
EventBus是一种用于Android的事件发布-订阅总线，由GreenRobot开发。
Gihub地址 :https://github.com/greenrobot/EventBus。
EventBus能够简化各组件间的通信，代码书写变得简单，能有效的分离事件发送方和接收方(也就是解耦的意思)，能避免复杂和容易出错的依赖性和生命周期问题。
EventBus可以代替Android传统的Intent，Handler，Broadcast或接口函数，在Fragment、Activity、Service线程之间传递数据，执行方法。
特点：代码简洁，是一种发布订阅设计模式(观察者设计模式)。

EventBus整理文章：https://www.jianshu.com/p/5ad5ea7180a2

====================================================================================================================================

三、Glide
图片加载框架。Glide和Picasso 有90%相似度，而Glide在Picasso基础上进行的二次开发，可以其优势显而易见。UniversalImageLoader已停止服务。
使用Glide在大多数情况下我们不需要指定图片大小，因为Glide会自动根据ImageView的大小来决定图片的大小，以此保证图片不会占用过多的内存从而引发OOM。
Glide可以加载GIF图片，Picasso是不支持这个功能的。使用Glide加载GIF图不需要编写额外代码，Glide内部会自动判断图片格式。同时可以加载本地视频。

====================================================================================================================================

四、RecyclerView简单/复杂item加载
1.简单加载：ListAdapter extents BaseQuickAdapter<EventMessage, BaseViewHolder>
构造方法、convert()
2.复杂加载：ListAdapter2 extents RecyclerView.Adapter<RecyclerView.ViewHolder>
onCreateViewHolder()、onBindViewHolder()、getItemCount()、getItemViewType()

====================================================================================================================================

五、IntentService的使用 (2019/6/10)
NetworkReceiver
IntentService会创建独立的worker线程来处理所有的Intent请求；
会创建独立的worker线程来处理onHandleIntent()方法实现的代码，无需处理多线程的问题；
所有请求处理完成后，IntentService会自动停止，无需调用stopSelf()方法停止Service；
为Service的onBind()提供默认实现，返回null；
为Service的onStartCommand提供默认实现，将请求Intent添加到队列中

====================================================================================================================================

六、module模块化（2019/6/13）
1.baselib BaseActivity， 公共工具类，自定义dialog；
2.eventbus3 测试跨module发送接收事件

====================================================================================================================================







