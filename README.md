# iron-door

Spring Security Demo - single/microserver

## 项目结构

```text
iron-door                                                                               父工程 
├── pom.xml                                                                             项目整体依赖文件
├── README.md                                                                           项目说明
├── multi-door                                                                          微服务项目演示模块
│   ├── pom.xml                                                                         微服务项目整体依赖文件
│   ├── microserver-common                                                              微服务项目核心依赖模块
│   │   ├── pom.xml                                                                     微服务项目核心依赖模块依赖文件
│   │   ├── common-core                                                                 微服务项目核心依赖模块公用核心依赖子模块（不涉及security）
│   │   │   ├── pom.xml                                                                 微服务项目核心依赖模块公用核心依赖子模块依赖文件
│   │   │   └── src                                                                     ----
│   │   │       └── main                                                                ----
│   │   │           └── java                                                            ----
│   │   │               └── com                                                         ----
│   │   │                   └── sheep                                                   ----
│   │   │                       └── microserver                                         ----
│   │   │                           └── common                                          ----
│   │   │                               └── core                                        ----
│   │   │                                   ├── config                                  配置文件
│   │   │                                   │   └── redis                               ----
│   │   │                                   │       └── RedisTemplateConfig.java        redis配置  用于存取Token [当创建了Bean之后其他所有模块引入此依赖必须要在启动类加上@ComponentScan("com.sheep.*"),防止出现可存不可取的问题]
│   │   │                                   ├── constant                                常量文件
│   │   │                                   │   ├── AuthConstant.java                   ----
│   │   │                                   │   ├── Constant.java                       ----
│   │   │                                   │   ├── JwtConstant.java                    ----
│   │   │                                   │   └── RedisCst.java                       ----
│   │   │                                   ├── dto                                     实体传输类
│   │   │                                   │   └── security                            ----
│   │   │                                   │       └── UserDTO.java                    ----
│   │   │                                   ├── enums                                   枚举
│   │   │                                   │   └── ErrorCodeEnum.java                  错误码
│   │   │                                   ├── exception                               自定义异常
│   │   │                                   │   ├── BaseException.java                  ----
│   │   │                                   │   └── GlobalExceptionHandler.java         全局异常捕获
│   │   │                                   ├── result                                  ----
│   │   │                                   │   └── Result.java                         结果集
│   │   │                                   └── utils                                   ----
│   │   │                                       ├── Md5Util.java                        Md5加密工具
│   │   │                                       └── ResponseUtil.java                   返回工具类
│   │   └──  common-security                                                            微服务项目核心依赖模块security核心依赖子模块
│   │       ├── pom.xml                                                                 微服务项目核心依赖模块security核心依赖子模块依赖文件
│   │       └── src                                                                     ----
│   │           └── main                                                                ----
│   │               └── java                                                            ----
│   │                   └── com                                                         ----
│   │                       └── sheep                                                   ----
│   │                           └── microserver                                         ----
│   │                               └── common                                          ----
│   │                                   └── security                                    ----
│   │                                       ├── controller                              ----
│   │                                       │   └── BaseController.java                 公共Controller [所有的Controller可以继承此Controller,以便于获取当前登录人的信息]
│   │                                       ├── filter                                  ----
│   │                                       │   └── AuthFilter.java                     微服务校验登录信息前置过滤器
│   │                                       ├── response                                ----
│   │                                       │   ├── AccessDenied.java                   未授权统一处理类
│   │                                       │   └── AuthEntryPoint.java                 未登录处理配置类
│   │                                       ├── utils                                   ----
│   │                                       │   ├── DefaultPasswordEncoder.java         默认的自定义密码处理器(使用security接受的BCryptPasswordEncoder密码处理器)
│   │                                       │   └── JwtUtil.java                        JWT处理工具类
│   │                                       └── vo                                      ----
│   │                                           └── SecurityUser.java                   security用户信息
│   ├── microserver-gateway                                                             网关服务
│   │   ├── pom.xml                                                                     ----
│   │   └── src                                                                         ----
│   │       └── main                                                                    ----
│   │           ├── java                                                                ----
│   │           │   └── com                                                             ----
│   │           │       └── sheep                                                       ----
│   │           │           └── microserver                                             ----
│   │           │               └── gateway                                             ----
│   │           │                   ├── configuration                                   ----
│   │           │                   │   └── GatewayCorsConfiguration.java               跨域配置
│   │           │                   ├── filter                                          ----
│   │           │                   │   └── AuthGlobalFilter.java                       认证统一校验,拦截非法Token
│   │           │                   └── MicroServerGatewayApplication.java              ----
│   │           └── resources                                                           ----
│   │               └── application.yml                                                 ----
│   ├── microserver-otherserver                                                         其他微服务,用于演示security认证信息传递和鉴权
│   │   ├── pom.xml                                                                     ----
│   │   └──  src                                                                        ----
│   │       └── main                                                                    ----
│   │           ├── java                                                                ----
│   │           │   └── com                                                             ----
│   │           │       └── sheep                                                       ----
│   │           │           └── microserver                                             ----
│   │           │               └── otherserver                                         ----
│   │           │                   ├── config                                          ----
│   │           │                   │   └── WebSecurityConfig.java                      SpringSecurity配置
│   │           │                   ├── controller                                      ----
│   │           │                   │   └── OtherServerController.java                  测试在其他服务中获取security认证信息的Controller
│   │           │                   └── MicroServerOtherServerApplication.java          ----
│   │           └── resources                                                           ----
│   │               └── application.yml                                                 ----
│   └── microserver-security                                                            ----
│       ├── pom.xml                                                                     ----
│       └── src                                                                         ----
│           └── main                                                                    ----
│               ├── java                                                                ----
│               │   └── com                                                             ----
│               │       └── sheep                                                       ----
│               │           └── microserver                                             ----
│               │               └── security                                            ----
│               │                   ├── auth                                            security认证配置类
│               │                   │   ├── config                                      ----
│               │                   │   │   └── WebSecurityConfig.java                  SpringSecurity配置
│               │                   │   ├── response                                    ----
│               │                   │   │   ├── AuthenticationFail.java                 自定义登录失败后的json返回
│               │                   │   │   └── AuthenticationSuccess.java              自定义登录成功后的json返回
│               │                   │   └── service                                     ----
│               │                   │       └── UserDetailServiceImpl.java              security登录自定义配置类
│               │                   ├── controller                                      ----
│               │                   │   └── AccessController.java                       默认的公用接口列表
│               │                   └── MicroServerSecurityApplication.java             ----
│               └── resources                                                           ----
│                   └── application.yml                                                 ----
└── single-door                                                                         单体项目演示模块
    ├── pom.xml                                                                         ----
    ├── quick-start                                                                     单体项目快速开始 -- 用于演示Spring Security的特性
    │   ├── pom.xml                                                                     ----
    │   └── src                                                                         ----
    │       └── main                                                                    ----
    │           ├── java                                                                ----
    │           │   └── com                                                             ----
    │           │       └── sheep                                                       ----
    │           │           └── quickstart                                              ----
    │           │               ├── config                                              ----
    │           │               │   ├── SecurityConfigurationInMemory.java              ----
    │           │               │   └── SecurityConfigurationJdbc.java                  ----
    │           │               ├── controller                                          ----
    │           │               │   └── FirstController.java                            ----
    │           │               ├── entity                                              ----
    │           │               │   └── MyUser.java                                     ----
    │           │               ├── mapper                                              ----
    │           │               │   └── MyUserMapper.java                               ----
    │           │               ├── QuickStartApplication.java                          ----
    │           │               ├── service                                             ----
    │           │               │   └── UserDetailServiceImpl.java                      ----
    │           │               └── utils                                               ----
    │           │                   └── PasswordEncoderUtil.java                        ----
    │           └── resources                                                           ----
    │               └── application.yml                                                 ----
    ├── advanced-monomer                                                                单体项目进阶演示
    │   ├── pom.xml                                                                     ----
    │   └── src                                                                         ----
    │       └── main                                                                    ----
    │           ├── java                                                                ----
    │           │   └── com                                                             ----
    │           │       └── sheep                                                       ----
    │           │           └── advanced                                                ----
    │           │               └── monomer                                             ----
    │           │                   ├── AdvancedMonomerApplication.java                 ----
    │           │                   ├── config                                          ----
    │           │                   │   └── SecurityConfigurationJdbc.java              ----
    │           │                   ├── controller                                      ----
    │           │                   │   ├── AuthorityController.java                    ----
    │           │                   │   └── BaseController.java                         ----
    │           │                   ├── entity                                          ----
    │           │                   │   └── MyUser.java                                 数据库自定义用户实体类
    │           │                   ├── mapper                                          ----
    │           │                   │   └── MyUserMapper.java                           ----
    │           │                   ├── service                                         ----
    │           │                   │   └── UserDetailServiceImpl.java                  UserDetailsService的自定义登录实现类
    │           │                   └── utils                                           ----
    │           │                       └── PasswordEncoderUtil.java                    密码工具类
    │           └── resources                                                           ----
    │               ├── application.yml                                                 ----
    │               └── static                                                          ----
    │                   ├── login.html                                                  自定义登录页面
    │                   ├── loginSuccessful.html                                        自定义登录成功跳转页面
    │                   ├── logout.html                                                 自定义登出成功跳转页面
    │                   └── unauth.html                                                 未授权跳转页面
    └── csrf-test                                                                       ----
        ├── pom.xml                                                                     ----
        └── src                                                                         ----
            └── main                                                                    ----
                ├── java                                                                ----
                │   └── com                                                             ----
                │       └── sheep                                                       ----
                │           └── csrftest                                                ----
                │               ├── config                                              ----
                │               │   └── SecurityConfig.java                             ----
                │               ├── controller                                          ----
                │               │   ├── CsrfController.java                             ----
                │               │   └── LoginController.java                            ----
                │               ├── CsrfTestApplication.java                            ----
                │               ├── service                                             ----
                │               │   └── UserDetailServiceImpl.java                      ----
                │               └── utils                                               ----
                │                   └── PasswordEncoderUtil.java                        ----
                └── resources                                                           ----
                    ├── application.yml                                                 ----
                    └── templates                                                       ----
                        ├── csrf                                                        ----
                        │   ├── csrfTest.html                                           ----
                        │   └── csrf_token.html                                         ----
                        └── login                                                       ----
                            └── login.html                                              ----
```