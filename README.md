# NestedScrollDemo嵌套滑动demo

![流程图](https://upload-images.jianshu.io/upload_images/1458573-8de7ad21ecfa0e82.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800/format/webp)

### 1.嵌套滑动的发起者：子View

> 主要思路：（用scroll来举例）
1. 实现NestedScrollingChild接口。
2. 定义NestedScrollingChildHelper变量。
3. 在实现的NestedScrollingChild每个接口中调用。NestedScrollingChildHelper对应的函数。
4. setNestedScrollingEnabled(true); 一般在初始化里面调用设置可以嵌套滑动。
5. onTouchEvent 或者 dispatchTouchEvent 方法里面case ACTION_DOWN 调用startNestedScroll函数 告诉父View开始嵌套滑动。
6. onTouchEvent 或者 dispatchTouchEvent 方法里面case ACTION_MOVE 调用dispatchNestedPreScroll或者dispatchNestedScroll 这个就视情况而定了告诉父View滑动的情况。
7. onTouchEvent 或者 dispatchTouchEvent 方法里面case ACTION_UP 调用stopNestedScroll 告诉父View结束嵌套滑动。
8. 重写onDetachedFromWindow方法，调用NestedScrollingChildHelper的onDetachedFromWindow方法
### 2.嵌套滑动的处理者：父View
> 主要思路：
1. 实现NestedScrollingParent接口。
2. 定义NestedScrollingParentHelper变量。
3. 在实现的NestedScrollingParent几个接口中(onNestedScrollAccepted, onStopNestedScroll, getNestedScrollAxes)调用NestedScrollingParentHelper对应的函数。
4. 视情况而定onNestedScroll onNestedPreScroll onNestedFling onNestedPreFling 做相应的处理。

**参考地址：** [嵌套机制](https://www.jianshu.com/p/e333f11f29aa)
