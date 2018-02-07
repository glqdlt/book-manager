package com.glqdlt.bookmanager.configuration;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;


// 이 구성은 잘 되지 않는 데 나중에 참고를 위해 남겨 둔다. 현재 mixed mode 로 application.properties 에서 사용하게끔 해놓았다.
//@Configuration
public class H2ServerConfiguration {
    @Value("${h2.tcp.port:9092}")
    private String h2TcpPort;

    // Web port, default 8082
    @Value("${h2.web.port:8082}")
    private String h2WebPort;

    @Bean
    @ConditionalOnExpression("${h2.tcp.enabled:true}")
    public Server h2TcpServer() throws SQLException {
        return Server.createPgServer("-tcp","-tcpAllowOthers","-tcpPort",h2TcpPort).start();
    }
}
