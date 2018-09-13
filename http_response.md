#Telegraf-HTTP Response Input Plugin

---
## 第一章 介绍
1. 	Telegraf介绍
	Telegraf是一款Go语言编写的metrics收集、处理、聚合的代理，其设计目标是较小的内存使用，通过插件来构建各种服务和第三方组件的metrics收集，Telegraf由4个独立的插件驱动：
	1.	Input Plugins
	输入插件，收集系统、服务、第三方组件的数据
	2.	Processor Plugins
	处理插件，转换、处理、过滤数据
	3.	Aggregator Plugins
	聚合插件，数据特征聚合
	4.	Output Plugins
	输出插件，写metrics数据
2. http_response介绍
  http_response是telegraf input plugins下的一个插件，这个输入插件用来检查HTTP/HTTPS的连接情况。
3. Influxdb介绍
InfluxDB是一个开源的没有外部依赖的时间序列数据库。适用于记录度量，事件及执行分析

---
## 第二章 安装
使用Telegraf的插件之前，需要安装Telegraf和InfluxDB
1.  安装Telegraf
```
[root@node1 ~]# wget http://get.influxdb.org/telegraf/telegraf-1.7.4-1.x86_64.rpm
[root@node1 ~]# rpm -ivh telegraf-1.7.4-1.x86_64.rpm
[root@node1 ~]# systemctl start telegra
```
webget方式下载较慢，可以先去官网下载，然后本地上传。
查看Telegraf版本型号：
```
[root@node1 ~]# telegraf --version
```
2.安装InfluxDB

```
[root@node1 ~]# wget http://dl.influxdata.com/influxdb/releases/influxdb-0.13.0.x86_64.rpm 
[root@node1 ~]# rpm -ivh influxdb-0.13.0.x86_64.rpm
[root@node1 ~]# systemctl start influxd
```

---
##第三章 配置
1. 配置Influxdb
打开Influxdb的配置文件
```
[gavin@master influxdb]$ vim /etc/influxdb/influxdb.conf 

```
![这里写图片描述](https://img-blog.csdn.net/20180912211623994?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3p1bndhbmd0aWFucWlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
查看端口号为8086

2.配置Telegraf
 打开Telegraf的配置文件，建立与Influxdb的联系，由Telegraf收集到的数据存储到Influxdb中。
 这里配置outputs.influxdb：
```
[gavin@master influxdb]$ vim /etc/telegraf/telegraf.conf

```
![这里写图片描述](https://img-blog.csdn.net/20180912212228559?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3p1bndhbmd0aWFucWlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

3.配置http_response
同样在Telegraf的配置文件下，找到inputs.http_response
```
[gavin@master influxdb]$ vim /etc/telegraf/telegraf.conf

```
![这里写图片描述](https://img-blog.csdn.net/20180912213014270?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3p1bndhbmd0aWFucWlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

配置好后，保证Telegraf和Influxdb正常运行，在WEB中访问IP:8083地址即可访问到Influxdb的图形化查询界面中：

![这里写图片描述](https://img-blog.csdn.net/20180912213545422?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3p1bndhbmd0aWFucWlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

选择telegraf数据库，输入查询语句，即可查询。查询语句与MySQL相似。

![这里写图片描述](https://img-blog.csdn.net/20180912213835114?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3p1bndhbmd0aWFucWlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

---
## 第四章 http_response details
有关[http_response的官方文档](https://github.com/influxdata/telegraf/blob/9bd14b283fc013790fad77693da008e9cc1ea773/plugins/inputs/http_response/README.md)地址
### 配置：
HTTP/HTTPs 请求只要给一个地址、一个请求的方法和一个时延参数即可获得响应。主要配置的三个参数如下：
```
# HTTP/HTTPS request given an address a method and a timeout
[[inputs.http_response]]
  ## Server address (default http://localhost)
  # address = "http://localhost"

  ## Set response_timeout (default 5 seconds)
  # response_timeout = "5s"

  ## HTTP Request Method
  # method = "GET"
```
其他可选参数如下：
```
 ## Set http_proxy (telegraf uses the system wide proxy settings if it's is not set)
  # http_proxy = "http://localhost:8888"
  
##Whether to follow redirects from the server (defaults to false)
  # follow_redirects = false

##Optional HTTP Request Body
  # body = '''
  # {'fake':'data'}
  # '''

  ## Optional substring or regex match in body of the response
  # response_string_match = "\"service_status\": \"up\""
  # response_string_match = "ok"
  # response_string_match = "\".*_status\".?:.?\"up\""

  ## Optional TLS Config
  # tls_ca = "/etc/telegraf/ca.pem"
  # tls_cert = "/etc/telegraf/cert.pem"
  # tls_key = "/etc/telegraf/key.pem"
  ## Use TLS but skip chain & host verification
  # insecure_skip_verify = false

  ## HTTP Request Headers (all values must be strings)
  # [inputs.http_response.headers]
  #   Host = "github.com"
```
###Metrecs 

-  标签 
    -  目标地址
    - 请求方法
    - 响应状态码
    - 结果
- 字段
     - 响应时间
     - 响应状态码
     - 结果类型
     - 结果码
     
  标签被用来暴露网络和插件的问题。HTTP错误也被认为是一次成功的连接。
  |Tag value                |Corresponding field value|Description|
--------------------------|-------------------------|-----------|
|success                  | 0                       |The HTTP request completed, even if the HTTP code represents an error|
|response_string_mismatch | 1                       |The option `response_string_match` was used, and the body of the response didn't match the regex|
|body_read_error          | 2                       |The option `response_string_match` was used, but the plugin wans't able to read the body of the response. Responses with empty bodies (like 3xx, HEAD, etc) will trigger this error|
|connection_failed        | 3                       |Catch all for any network error not specifically handled by the plugin|
|timeout                  | 4                       |The plugin timed out while awaiting the HTTP connection to complete|
|dns_error                | 5                       |There was a DNS error while attempting to connect to the host|
### 目标结果
最终应该采集到如下数据：

```
http_response,method=GET,server=http://www.github.com,status_code=200,result=success http_response_code=200i,response_time=6.223266528,result_type="success",result_code=0i 1459419354977857955
```