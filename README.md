# projectHelper
集成大量java项目开发中所需要的基础组件，基础方法等。并且持续扩充

maven引入下列依赖即可快速使用

    <dependency>
        <groupId>com.github.shanananana</groupId>
        <artifactId>projectHelper</artifactId>
        <version>2.2.7</version>
    </dependency>
各工具使用方式可参考：https://github.com/shanananana/demoForMybatisPlus    
    
项目结构

 - businessHelper:通用方法 md5,对象转换等
 
 - exceptionHelper:异常包装 支持抛出枚举 有通用枚举 亦可自定义枚举（实现CommonError接口即可）   

 - redisHelper:redis工具类，基于redisTemlate实现，提供更丰富的接口如set(String key,T model) model不必要序列化.
           
     
 - responseHelper:通用响应包装 提供标准的code,message,data 响应体返回。
 
 - permissionhelper: 非常易上手的简易权限框架。
 
 
    
      redishelper使用方式： 需要在项目启动类增加扫描  
                
           @ComponentScan({"com.helper.redishelper","其他包"})
        
 
     - 使用示例
               
               测试类对象
               @Data
               @Accessors(chain = true)
               public class TestEntiy {
                   private String account;
               
                   private String password;
               }
               
               @Autowired
               RedisHelper redisHelper;
               
               public void redisTest(){
                          TestEntiy testEntiy=new TestEntiy().setAccount("123").setPassword("456");
                          redisHelper.setModel("entity",testEntiy);
                          TestEntiy result= (TestEntiy) redisHelper.getModel("entity",TestEntiy.class);
                          System.out.println(result.toString());
                      }
               控制台打印：TestEntiy(account=123, password=456)
     
     
 -permissionhelper使用方式
 启动类@ComponentScan中添加"com.helper.permissionhelper.*"  
 
 需要鉴权的方法新增@PermissionAnnotation("自定义的权限如"getUserById"")
 
      @GetMapping("getUserById")
      @ApiOperation("根据id查询用户")
      @PermissionAnnotation("getUserById")
      public BaseResponse getUserById(@RequestParam Long id) {
          return BaseResponse.success(true);
      }       
     
 登录代码示例
 
     @PostMapping("/login")
     @ApiOperation("登录")
     public Boolean login(@RequestBody UserVo uservo,HttpServletRequest request) {
         /*检验账号密码部分省略，得到user后获得其角色*/
         String userType=user.getRoleType;
         /*从数据库中查出权限列表注解的，返回的是@PermissionAnnotation注解包含的值*/
         List<String> permissionList= permissionMapper.selectListByType(userType);
         HttpSession session = request.getSession();
         session.setAttribute("permissionList",permissionList);
         return true;
     }
 可以看出此框架无需任何配置，即可快速开启鉴权功能
 
 后续会继续扩充，尽量覆盖到大多数普通java项目的常用工具，简化开发