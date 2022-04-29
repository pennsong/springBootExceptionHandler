# springBootExceptionHandler
global exception handler for springBoot

## 解决的问题:
* http请求统一返回json格式(正常返回, 出错返回)
: GlobalResponseAdvice
* json返回中的code和httpStatus code保持一致, 方便http请求的framework自动识别
: GlobalResponseAdvice
* 对于指定类型的错误(包括未专门处理的错误)可以记录log(包含时间戳), 方便排查和改进程序
: ExceptionAdvice
