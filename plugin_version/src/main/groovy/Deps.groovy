/**
 * 版本信息
 */
interface Versions {
    def applicationId = 'com.android.debugmvp'
    def buildTool = '30.0.2'
    def minSdk = 23
    def targetSdk = 30
    def compileSdk = 30
    def appVersionCode = 1
    def appVersionName = '1.0.0'
}

/**
 * 依赖库路径
 */
interface Deps {
    /*
     * test
     */
    //junit
    String junit = 'junit:junit:4.12'
    //runner
    String runner = 'androidx.test:runner:1.2.0'
    //espresso
    String espresso = 'androidx.test.espresso:espresso-core:3.2.0'

    /*
     * support
     */
    //appcompat
    String appcompat = 'androidx.appcompat:appcompat:1.2.0'
    //supportV4
    String supportV4 = 'androidx.legacy:legacy-support-v4:1.0.0'
    //fragment
    String fragment = 'androidx.fragment:fragment:1.2.5'
    //supportCoreUtils
    String supportCoreUtils = 'androidx.legacy:legacy-support-core-utils:1.0.0'
    //annotations
    String annotations = 'androidx.annotation:annotation:1.1.0'
    //design
    String design = 'com.google.android.material:material:1.3.0-alpha04'
    //cardView
    String cardView = 'androidx.cardview:cardview:1.0.0'
    //recyclerView
    String recyclerView = 'androidx.recyclerview:recyclerview:1.1.0'
    //transition
    String transition = 'androidx.transition:transition:1.3.1'
    //constraintLayout
    String constraintLayout = 'androidx.constraintlayout:constraintlayout:2.0.4'
    //multiDex
    String multiDex = 'androidx.multidex:multidex:2.0.1'
    //coreKtx
    String coreKtx = 'androidx.core:core-ktx:1.3.2'
    //core
    String core = 'androidx.core:core:1.3.2'

    /*
     * room
     */
    //roomRuntime
    String roomRuntime = 'androidx.room:room-runtime:2.2.5'
    //roomCompiler
    String roomCompiler = 'androidx.room:room-compiler:2.2.5'
    //roomRxJava2
    String roomRxJava2 = 'androidx.room:room-rxjava2:2.2.5'
    //roomTesting
    String roomTesting = 'androidx.room:room-testing:2.2.5'

    /*
     * lifecycle
     */
    //viewModel
    String viewModel = 'androidx.lifecycle:lifecycle-viewmodel:2.2.0'
    //liveData
    String liveData = 'androidx.lifecycle:lifecycle-livedata:2.2.0'
    //lifecycleRuntime : no view model or live data
    String lifecycleRuntime = 'androidx.lifecycle:lifecycle-runtime:2.2.0'
    //lifecycleCommonJava8 : if using Java8, use the following instead of compiler
    String lifecycleCommonJava8 = 'androidx.lifecycle:lifecycle-common-java8:2.2.0'
    //lifecycleReactiveStreams : ReactiveStreams support for LiveData
    String lifecycleReactiveStreams = 'androidx.lifecycle:lifecycle-reactivestreams:2.2.0'


    //lifecycleViewModelSavedState :ViewModel新出的状态保存库
    String lifecycleViewModelSavedState = 'androidx.lifecycle:lifecycle-viewmodel-savedstate:2.2.0'
    //viewModelKtx :alternatively - just ViewModel
    String viewModelKtx = 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'
    //liveDataKtx :alternatively - just LiveData
    String liveDataKtx = 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
    //lifecycleRunntimeKtx : alternatively - Lifecycles only (no ViewModel or LiveData)
    String lifecycleRunntimeKtx = 'androidx.lifecycle:lifecycle-runtime-ktx:2.2.0'
    //lifecycleReactivestreamsKtx : optional - ReactiveStreams support for LiveData
    String lifecycleReactivestreamsKtx = 'androidx.lifecycle:lifecycle-reactivestreams-ktx:2.2.0'


    /*
    * navigation
    * */
    //navigationSafeArgsGradlePlugin : classpath
    String navigationSafeArgsGradlePlugin = 'androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0'
    //navigationFragmentKtx
    String navigationFragmentKtx = 'androidx.navigation:navigation-fragment-ktx:2.3.0'
    //navigationUIKtx
    String navigationUIKtx = 'androidx.navigation:navigation-ui-ktx:2.3.0'

    /*
     * google
     */
    //pagingRuntime
    String pagingRuntime = 'androidx.paging:paging-runtime:2.1.2'

    //playServicesAd
    String playServicesAd = 'com.google.android.gms:play-services-ads:19.3.0'
    //googleService
    String googleService = 'com.google.gms:google-services:4.3.3'
    //flexBox : https://github.com/google/flexbox-layout
    String flexBox = 'com.google.android:flexbox:2.0.0'
    //gson : https://github.com/google/gson
    String gson = 'com.google.code.gson:gson:2.8.6'

    //kotlinAndroidExtensions :插件
    String kotlinAndroidExtensions = 'org.jetbrains.kotlin:kotlin-android-extensions:1.4.21'
    //kotlinStdlib
    String kotlinStdlib = 'org.jetbrains.kotlin:kotlin-stdlib:1.4.10'
    //kotlinStdlibJdk7
    String kotlinStdlibJdk7 = 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.10'
    //kotlinStdlibJdk8
    String kotlinStdlibJdk8 = 'org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10'
    //kotlinXCoroutinesAndroid
    String kotlinXCoroutinesAndroid = 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9'
    //kotlinXCoroutinesCore
    String kotlinXCoroutinesCore = 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1'
    //kotlinReflect
    String kotlinReflect = 'org.jetbrains.kotlin:kotlin-reflect:1.4.21'

    /*
     * 模块化框架
     */
    //appJoint : https://github.com/PrototypeZ/AppJoint/blob/master/README_zh.md
    String appJoint = 'io.github.prototypez:app-joint:1.7'
    //appJointCore
    String appJointCore = 'io.github.prototypez:app-joint-core:1.7'

    /*
     * 网络
     */
    //okHttp3 : https://github.com/square/okhttp
    String okHttp3 = 'com.squareup.okhttp3:okhttp:4.7.2'
    //okHttp3LoggingInterceptor
    String okHttp3LoggingInterceptor = 'com.squareup.okhttp3:logging-interceptor:4.0.0'

    /*
     * retrofit2
     */
    //retrofit2 : https://github.com/square/retrofit
    String retrofit2 = 'com.squareup.retrofit2:retrofit:2.9.0'
    //retrofit2ConverterGson
    String retrofit2ConverterGson = 'com.squareup.retrofit2:converter-gson:2.6.0'
    //retrofit2ConverterScalars
    String retrofit2ConverterScalars = 'com.squareup.retrofit2:converter-scalars:2.6.0'
    //retrofit2AdapterRxJava2
    String retrofit2AdapterRxJava2 = 'com.squareup.retrofit2:adapter-rxjava2:2.6.0'

    //retrofitUrlManager : https://github.com/JessYanCoding/RetrofitUrlManager
    String retrofitUrlManager = 'me.jessyan:retrofit-url-manager:1.4.0'

    /*
     * 路由
     */
    //aRouterApi : https://github.com/alibaba/ARouter
    String aRouterApi = 'com.alibaba:arouter-api:1.4.1'
    //aRouterCompiler
    String aRouterCompiler = 'com.alibaba:arouter-compiler:1.2.2'

    /*
     * 图片加载
     */
    //picasso : https://github.com/square/picasso
    String picasso = 'com.squareup.picasso:picasso:2.71828'

    //glideV4 : https://github.com/bumptech/glide
    String glideV4 = 'com.github.bumptech.glide:glide:4.11.0'
    //glideV4Compiler
    String glideV4Compiler = 'com.github.bumptech.glide:compiler:4.11.0'

    /*
     * 事件总线
     */
    //eventBus : https://github.com/greenrobot/EventBus
    String eventBus = 'org.greenrobot:eventbus:3.1.1'
    //liveEventBus : https://github.com/JeremyLiao/LiveEventBus
    String liveEventBus = 'com.jeremyliao:live-event-bus:1.4.0'

    /*
     * bug反馈
     */
    //bugly
    String bugly = 'com.tencent.bugly:crashreport:3.1.0'
    //buglyUpgrade : https://bugly.qq.com/docs/user-guide/instruction-manual-android-upgrade/?v=20200203205953
    String buglyUpgrade = 'com.tencent.bugly:crashreport_upgrade:1.4.2'

    /*
     * 自定义view
     */
    //discreteScrollview : https://github.com/yarolegovich/DiscreteScrollView
    String discreteScrollview = 'com.yarolegovich:discrete-scrollview:1.4.9'
    //easySwipeMenuLayout : https://github.com/anzaizai/EasySwipeMenuLayout
    String easySwipeMenuLayout = 'com.github.anzaizai:EasySwipeMenuLayout:1.1.4'
    //swipeDelMenuLayout :侧滑删除 https://github.com/mcxtzhang/SwipeDelMenuLayout/blob/master/README-cn.md
    String swipeDelMenuLayout = 'com.github.mcxtzhang:SwipeDelMenuLayout:V1.3.0'

    //pageIndicatorView : https://github.com/romandanylyk/PageIndicatorView
    String pageIndicatorView = 'com.romandanylyk:pageindicatorview:1.0.3'
    //swipeBack
    String swipeBack = 'n.simonlee.widget:swipeback:1.0.15'
    //banner
    String banner = 'com.youth.banner:banner:1.4.10'
    //viewPagerTransforms
    String viewPagerTransforms = 'com.ToxicBakery.viewpager.transforms:view-pager-transforms:1.2.32@aar'
    //circleImageView : https://github.com/hdodenhof/CircleImageView
    String circleImageView = 'de.hdodenhof:circleimageview:3.1.0'
    //roundedImageView :圆角图片 https://github.com/vinc3m1/RoundedImageView
    String roundedImageView = 'com.makeramen:roundedimageview:2.3.0'
    //lRecyclerView
    String lRecyclerView = 'com.github.jdsjlzx:LRecyclerView:1.5.4.3'
    //photoView : https://github.com/chrisbanes/PhotoView
    String photoView = 'com.github.chrisbanes:PhotoView:2.2.0'
    //gifDrawable : https://github.com/koral--/android-gif-drawable
    String gifDrawable = 'pl.droidsonroids.gif:android-gif-drawable:1.2.15'
    //androidPickerView : 时间,地址选择器 https://github.com/Bigkoo/Android-PickerView
    String androidPickerView = 'com.contrarywind:Android-PickerView:4.1.9'
    //baseRecyclerViewAdapterHelper : recycleView相关 https://github.com/CymChad/BaseRecyclerViewAdapterHelper
    String baseRecyclerViewAdapterHelper = 'com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4'
    //numberPickerView : https://github.com/Carbs0126/NumberPickerView
    String numberPickerView = 'n.carbswang.android:NumberPickerView:1.1.1'
    //swipeBackLayout :侧滑退出 https://github.com/ikew0ng/SwipeBackLayout
    String swipeBackLayout = 'me.imid.swipebacklayout.lib:library:1.1.0'
    //switchButton : https://github.com/kyleduo/SwitchButton
    String switchButton = 'com.kyleduo.switchbutton:library:2.0.0'
    //NiceImageView
    String NiceImageView = 'com.github.SheHuan:NiceImageView:1.0.5'
    //indicatorSeekBar :进度条 带顶部气泡 https://github.com/warkiz/IndicatorSeekBar
    String indicatorSeekBar = 'com.github.warkiz.widget:indicatorseekbar:2.1.0'
    //SideMenu : https://github.com/Yalantis/Side-Menu.Android
    String SideMenu = 'com.github.yalantis:Side-Menu.Android:1.0.2'
    //CircularReveal : https://github.com/ozodrukh/CircularReveal
    String CircularReveal = 'com.github.ozodrukh:CircularReveal:2.1.0@aar'
    //FloatMenu : https://github.com/crosg/FloatMenuSample
    String FloatMenu = 'com.yw.game.floatmenu:FloatMenu:2.0.1'

    /*
     * 动画
     */
    //transitionsEverywhere : https://github.com/andkulikov/Transitions-Everywhere
    String transitionsEverywhere = 'com.andkulikov:transitionseverywhere:1.8.0'

    /*
     * 权限
     */
    //andPermission : https://github.com/yanzhenjie/AndPermission
    String andPermission = 'com.yanzhenjie:permission:2.0.0-rc5'

    /*
     * 实用
     */
    //rxJava2 : https://github.com/ReactiveX/RxJava
    String rxJava2 = 'io.reactivex.rxjava2:rxjava:2.2.19'
    //rxAndroid
    String rxAndroid = 'io.reactivex:rxandroid:1.0.1'
    //rxAndroid2
    String rxAndroid2 = 'io.reactivex.rxjava2:rxandroid:2.1.1'

    //rxLifecycle : https://github.com/trello/RxLifecycle
    String rxLifecycle = 'com.trello.rxlifecycle2:rxlifecycle:2.2.2'
    //rxLifecycleComponents
    String rxLifecycleComponents = 'com.trello.rxlifecycle2:rxlifecycle-components:2.2.2'
    //rxLifecycleNavi
    String rxLifecycleNavi = 'com.trello.rxlifecycle2:rxlifecycle-navi:2.2.2'
    //lifecycleExtensions
    String lifecycleExtensions = 'androidx.lifecycle:lifecycle-extensions:2.2.0'

    //oneDrawable : https://github.com/maoruibin/OneDrawable
    String oneDrawable = 'com.github.maoruibin:OneDrawable:1.1.0'

    //xutils
    String xutils = 'org.xutils:xutils:3.5.0'
    //utilCode
    String utilCode = 'com.blankj:utilcodex:1.23.7'
    //httpCore
    String httpCore = 'org.apache.httpcomponents:httpcore:4.4.10'
    //zxing
    String zxing = 'com.google.zxing:core:3.3.3'
    //logger
    String logger = 'com.orhanobut:logger:1.15'

    //rxBinding
    String rxBinding = 'com.jakewharton.rxbinding:rxbinding:0.3.0'
    //rxBindingAppcompatV7
    String rxBindingAppcompatV7 = 'com.jakewharton.rxbinding:rxbinding-appcompat-v7:0.3.0'
    //rxBindingDesign
    String rxBindingDesign = 'com.jakewharton.rxbinding:rxbinding-design:0.3.0'

    //greenDao
    String greenDao = 'de.greenrobot:greendao:2.0.0'
    //mmkv : 基于mmap的高性能通用key-value组件 https://github.com/Tencent/MMKV/blob/master/readme_cn.md
    String mmkv = 'com.tencent:mmkv-static:1.2.6'

    //javaxAnnotation
    String javaxAnnotation = 'org.glassfish:javax.annotation:10.0-b28'

    //dagger
    String dagger = 'com.google.dagger:dagger:2.23.2'
    //daggerCompiler
    String daggerCompiler = 'com.google.dagger:dagger-compiler:2.23.2'

    // fgchecker
    String fgchecker = 'com.rvalerio:fgchecker:1.1.0'

    //BroadcastReceiver : https://github.com/cbfg5210/BroadcastReceiver
    String BroadcastReceiver = 'com.github.cbfg5210:BroadcastReceiver:0.6'
    //BRecyclerAdapter : https://github.com/cbfg5210/BRecyclerAdapter
    String BRecyclerAdapter = 'com.github.cbfg5210:BRecyclerAdapter:0.6'
    //BaseDialog : https://github.com/cbfg5210/BaseDialog
    String BaseDialog = 'com.github.cbfg5210:BaseDialog:0.1'
    //BUtil : https://github.com/cbfg5210/BUtil
    String BUtil = 'com.github.cbfg5210:BUtil:0.4'

    //AutoSize : https://github.com/JessYanCoding/AndroidAutoSize
    String AutoSize = 'me.jessyan:autosize:1.2.0'

    //CrashX : https://github.com/TutorialsAndroid/crashx
    String CrashX = 'com.github.TutorialsAndroid:crashx:v4.0.19'
    //FitAndroid8 : Android 7/8 FileProvider适配 https://github.com/steven2947/FitAndroid8
    String FitAndroid8 = 'com.github.steven2947:FitAndroid8:0.5.0'

    /*
     * 插件
     */
    //saveState : https://github.com/PrototypeZ/SaveState
    String saveState = 'io.github.prototypez:save-state:0.2.3'

    /*
    * 测试工具
    */
    //stetho : https://github.com/facebook/stetho
    String stetho = 'com.facebook.stetho:stetho:1.5.0'
    //stethoOkHttp3
    String stethoOkHttp3 = 'com.facebook.stetho:stetho-okhttp3:1.5.0'
    //stethoUrlConnection
    String stethoUrlConnection = 'com.facebook.stetho:stetho-urlconnection:1.5.0'

    //leakCanaryAndroid : https://github.com/square/leakcanary
    String leakCanaryAndroid = 'com.squareup.leakcanary:leakcanary-android:1.6.1'
    //leakCanaryAndroidNoOp
    String leakCanaryAndroidNoOp = 'com.squareup.leakcanary:leakcanary-android-no-op:1.6.1'
    //leakCanarySupportFragment
    String leakCanarySupportFragment = 'com.squareup.leakcanary:leakcanary-support-fragment:1.6.1'

    //blockCanaryAndroid :卡顿监控和提示 https://github.com/markzhai/AndroidPerformanceMonitor
    String blockCanaryAndroid = 'com.github.markzhai:blockcanary-android:1.5.0'

    //crashwoodpecker
    String crashwoodpecker = 'me.drakeet.library:crashwoodpecker:2.1.1'

    //debugDb
    String debugDb = 'com.amitshekhar.android:debug-db:1.0.6'

    //rxAndroidBle : https://github.com/Polidea/RxAndroidBle
    String rxAndroidBle = 'com.polidea.rxandroidble2:rxandroidble:1.9.0'
    //ble : https://github.com/xiaoyaoyou1212/BLE
    String ble = 'com.vise.xiaoyaoyou:baseble:2.0.5'
}
