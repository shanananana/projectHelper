# projectHelper
集成大量java项目开发中所需要的基础组件，基础方法等。并且持续扩充

maven引入下列依赖即可快速使用

    <dependency>
        <groupId>com.github.shanananana</groupId>
        <artifactId>projectHelper</artifactId>
        <version>2.2.2</version>
    </dependency>
    
项目结构

 - businessHelper:通用方法 md5,对象转换等
 
 - exceptionHelper:异常包装 支持抛出枚举 有通用枚举 亦可自定义枚举（实现CommonError接口即可）   

 - redisHelper:redis工具类，基于redisTemlate实现，提供更丰富的接口如set(String key,T model) model不必要序列化.
           
      - 使用方式： 需要在项目启动时注入redisTemplate。参考代码
    
            @Component
            public class Listener implements ApplicationListener<ApplicationReadyEvent> {
            
                @Autowired
                RedisTemplate redisTemplate;
            
                @Override
                public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
                    RedisHelper.getInstance().init(redisTemplate);
                }
            
            }

      业务代码注入
           
           private RedisHelper redisHelper=RedisHelper.getInstance();
 
 - responseHelper:通用响应包装 提供标准的code,message,data 响应体返回。
 
 后续会继续扩充，尽量覆盖到大多数普通java项目的常用工具，简化开发