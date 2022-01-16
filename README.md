# spring-cloud-alibaba-template
spring-cloud-alibaba-template

# 启动Nacos DB
# /stock/src/main/resources/application.yml配置文件打开注释导入Nacos配置中心
# /order/src/main/resources/application.yml配置文件打开注释导入Nacos配置中心
# 注意两个模块的bootstrap.yml文件读取的配置中心的设置
docker run --name nacosDB -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0.19

# 启动Seata DB，并使用数据库连接工具创建一个数据库，数据库的名字叫seata，
# 执行/seata-server-1.3.0/seata/script/目录下的mysql.sql文件
docker run --name seataDB -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0.19

# 启动Order DB，并使用数据库连接工具创建一个数据库，数据库的名字叫seata_order，
# 执行/order/src/main/resources/db/mysql目录下的order_tbl.sql文件
# 执行/seata-server-1.3.0/seata/script/client/at/db目录下的mysql.sql文件
docker run --name orderDB -d -p 3308:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0.19

# 启动Stock DB，并使用数据库连接工具创建一个数据库，数据库的名字叫seata_stock，
# 执行/stock/src/main/resources/db/mysql目录下的stock_tbl.sql文件
# 执行/seata-server-1.3.0/seata/script/client/at/db目录下的mysql.sql文件
docker run --name stockDB -d -p 3309:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0.19

# 启动Skywalking DB
docker run --name skywalkingDB -d -p 3310:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0.19

# windows启动Nacos集群占用8849、8850、8851端口，在nacos8849/bin、nacos8850/bin、nacos8851/bin目录下执行
startup.bat

# 启动sentinel服务端：在当前目录执行sentinel-server.sh 或者运行如下命令
java -Dserver.port=8088 -Dsentinel.dashboard.auth.username=sentinel -Dsentinel.dashboard.auth.password=123456 -jar sentinel-dashboard-1.8.0.jar

# 启动seata服务端：
# 1、在/seata-server-1.3.0/seata/script/config-center/nacos目录下执行命令,
#    将/seata-server-1.3.0/seata/script/config-center/config.txt文件上传到Nacos配置中心，启动命令参数详解如下
bash nacos-config.sh -h 127.0.0.1 -p 8849 -g seata -t 00811662-3dea-4999-a45b-b87dfdd78118 -u nacos -w nacos
# -h 指定Nacos服务启动主机IP地址，默认是localhost
# -p 指定Nacos服务启动的端口号，默认是8848
# -g 配置分组组名，默认是SEATA-GROUP
# -t Nacos中不同命名空间ID，默认值是空
# -u 连接Nacos配置中心地址登录用户名
# -w 连接Nacos配置中心地址登录密码
# 2、在/seata-server-1.3.0/seata/bin目录下执行如下命令
seata-server.bat -p 8092 -h 192.168.0.101 
# -h 指定在注册中心注册的 IP，不指定时获取当前的 IP可能不是IP V4的地址，外部访问部署在云环境和容器中的 server 建议指定
# -p 指定Seata server启动的端口号，默认是8091
# -m 事务日志存储方式，支持file,db,redis，默认为 file 注:redis需seata-server 1.3版本及以上
# -n 用于指定seata-server节点ID，如 1,2,3..., 默认为 1
# -e 指定 seata-server 运行环境，如 dev, test 等, 服务启动时会使用 registry-dev.conf 这样的配置

# windows启动Skywalking服务端：在/apache-skywalking-apm-es7-8.5.0/apache-skywalking-apm-bin-es7/bin目录下执行如下命令，占用的端口是8098、11800和12800
startup.bat

# 需要链路追踪的服务在启动时需要添加如下JVM参数
# -javaagent 指添加agent.jar的绝对路径，agent.jar在下载Skywalking压缩包: https://skywalking.apache.org/downloads/ 解压缩后的agent目录下
# -DSW_AGENT_NAME 在Skywalking的UI界面显示的服务名字，一般直接写当前服务的名字
# -DSW_AGENT_COLLECTOR_BACKENT_SERVICE 指定Skywalking服务的IP和其中OapService服务收集监控数据的端口
-javaagent:E:\java-project\spring-cloud-alibaba-template\apache-skywalking-apm-es7-8.5.0\apache-skywalking-apm-bin-es7\agent\skywalking-agent.jar
-DSW_AGENT_NAME=gateway-server
-DSW_AGENT_COLLECTOR_BACKENT_SERVICE=127.0.0.1:11800


