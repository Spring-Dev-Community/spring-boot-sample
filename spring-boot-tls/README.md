## 生成服务器端证书
keytool -genkeypair -alias server -keyalg RSA -keysize 4096 \
-validity 3650 -dname "CN=localhost" -keypass changeit -keystore truststore.p12 \
-storeType PKCS12 -storepass changeit

## 生成客户端证书
keytool -genkeypair -alias relive -keyalg RSA -keysize 4096 \
-validity 3650 -dname "CN=localhost" -keypass changeit -keystore keystore.p12 \
-storeType PKCS12 -storepass changeit

## 导出客户端证书公钥cer文件
keytool -export -alias relive -keystore keystore.p12 -storetype PKCS12 -rfc -file client.cer

## 将客户端公钥导入服务器证书
keytool -import -v -file client.cer -keystore truststore.p12 