|-- java
    |-- .gitignore
    |-- README.md
    |-- pom.xml
    |-- admin-apis
    |   |-- pom.xml
    |   |-- src
    |   |   |-- main
    |   |   |   |-- java
    |   |   |   |   |-- com
    |   |   |   |       |-- chengxiaoxiao
    |   |   |   |           |-- api
    |   |   |   |               |-- config
    |   |   |   |               |-- user
    |-- admin-common
    |   |-- pom.xml
    |   |-- src
    |   |   |-- main
    |   |   |   |-- java
    |   |   |   |   |-- com
    |   |   |   |       |-- .DS_Store
    |   |   |   |       |-- chengxiaoxiao
    |   |   |   |           |-- .DS_Store
    |   |   |   |           |-- common
    |   |   |   |               |-- .DS_Store
    |   |   |   |               |-- jwt
    |   |   |   |               |   |-- JwtUtil.java
    |   |   |   |               |-- utils
    |   |   |   |                   |-- DateUtils.java
    |   |   |   |                   |-- EncryptUtil.java
    |   |   |   |                   |-- IdWorker.java
    |   |   |   |                   |-- IpUtils.java
    |   |   |   |-- resources
    |   |   |-- test
    |   |       |-- java
    |   |-- target
    |       |-- classes
    |       |   |-- META-INF
    |       |       |-- admin-common.kotlin_module
    |       |-- generated-sources
    |           |-- annotations
    |-- admin-model
    |   |-- pom.xml
    |   |-- src
    |   |   |-- .DS_Store
    |   |   |-- main
    |   |   |   |-- .DS_Store
    |   |   |   |-- java
    |   |   |   |   |-- .DS_Store
    |   |   |   |   |-- com
    |   |   |   |       |-- .DS_Store
    |   |   |   |       |-- chengxiaoxiao
    |   |   |   |           |-- .DS_Store
    |   |   |   |           |-- model
    |   |   |   |               |-- annotation
    |   |   |   |               |   |-- validator
    |   |   |   |               |-- common
    |   |   |   |               |   |-- constants
    |   |   |   |               |   |-- dtos
    |   |   |   |               |   |   |-- CodeMsg.java
    |   |   |   |               |   |   |-- PageResult.java
    |   |   |   |               |   |   |-- Result.java
    |   |   |   |               |   |-- enums
    |   |   |   |               |-- mappers
    |   |   |   |               |   |-- web
    |   |   |   |               |-- redis
    |   |   |   |               |-- repository
    |   |   |   |               |   |-- UserRepository.java
    |   |   |   |               |   |-- impl
    |   |   |   |               |-- web
    |   |   |   |                   |-- dtos
    |   |   |   |                   |-- enums
    |   |   |   |                   |-- pojos
    |   |   |   |                       |-- User.java
    |   |   |   |-- resources
    |   |   |-- test
    |   |       |-- java
    |   |-- target
    |       |-- admin-model-1.0.0.jar
    |       |-- classes
    |       |   |-- com
    |       |       |-- chengxiaoxiao
    |       |           |-- model
    |       |               |-- User.class
    |       |               |-- common
    |       |               |   |-- dtos
    |       |               |       |-- CodeMsg.class
    |       |               |       |-- PageResult.class
    |       |               |       |-- Result.class
    |       |               |-- web
    |       |                   |-- pojos
    |       |                       |-- User.class
    |       |-- generated-sources
    |       |   |-- annotations
    |       |-- generated-test-sources
    |       |   |-- test-annotations
    |       |-- maven-archiver
    |       |   |-- pom.properties
    |       |-- maven-status
    |       |   |-- maven-compiler-plugin
    |       |       |-- compile
    |       |       |   |-- default-compile
    |       |       |       |-- createdFiles.lst
    |       |       |       |-- inputFiles.lst
    |       |       |-- testCompile
    |       |           |-- default-testCompile
    |       |               |-- createdFiles.lst
    |       |               |-- inputFiles.lst
    |       |-- test-classes
    |-- admin-server
    |   |-- pom.xml
    |   |-- src
    |   |   |-- main
    |   |   |   |-- java
    |   |   |   |   |-- com
    |   |   |   |       |-- chengxiaoxiao
    |   |   |   |           |-- server
    |   |   |   |               |-- AdminServerApplication.java
    |   |   |   |-- resources
    |   |   |       |-- application.yml
    |   |   |-- test
    |   |       |-- java
    |   |-- target
    |       |-- classes
    |       |   |-- application.yml
    |       |   |-- com
    |       |       |-- chengxiaoxiao
    |       |           |-- server
    |       |               |-- AdminServerApplication.class
    |       |-- generated-sources
    |           |-- annotations
    |-- admin-web
        |-- pom.xml
        |-- src
        |   |-- main
        |   |   |-- java
        |   |   |   |-- com
        |   |   |       |-- chengxiaoxiao
        |   |   |           |-- web
        |   |   |               |-- WebApplication.java
        |   |   |               |-- config
        |   |   |               |   |-- DruidConfiguration.java
        |   |   |               |-- controller
        |   |   |               |   |-- UserController.java
        |   |   |               |-- exception
        |   |   |               |   |-- GlobleException.java
        |   |   |               |   |-- GlobleExceptionHandler.java
        |   |   |               |-- interceptor
        |   |   |               |-- service
        |   |   |                   |-- UserService.java
        |   |   |                   |-- impl
        |   |   |                       |-- UserServiceImpl.java
        |   |   |-- resources
        |   |       |-- application.yml
        |   |-- test
        |       |-- java
        |-- target
            |-- admin-web-1.0.0.jar
            |-- classes
            |   |-- application.yml
            |   |-- META-INF
            |   |   |-- admin-web.kotlin_module
            |   |-- com
            |       |-- chengxiaoxiao
            |           |-- web
            |               |-- MainApplication.class
            |               |-- WebApplication.class
            |               |-- config
            |               |   |-- DruidConfiguration.class
            |               |-- controller
            |               |   |-- UserController.class
            |               |-- repository
            |               |   |-- UserRepository.class
            |               |-- service
            |                   |-- UserService.class
            |                   |-- impl
            |                       |-- UserServiceImpl.class
            |-- generated-sources
            |   |-- annotations
            |-- generated-test-sources
            |   |-- test-annotations
            |-- maven-archiver
            |   |-- pom.properties
            |-- maven-status
            |   |-- maven-compiler-plugin
            |       |-- compile
            |       |   |-- default-compile
            |       |       |-- createdFiles.lst
            |       |       |-- inputFiles.lst
            |       |-- testCompile
            |           |-- default-testCompile
            |               |-- createdFiles.lst
            |               |-- inputFiles.lst
            |-- test-classes
