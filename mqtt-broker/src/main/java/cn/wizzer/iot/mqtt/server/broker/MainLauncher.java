package cn.wizzer.iot.mqtt.server.broker;

import cn.wizzer.iot.mqtt.server.broker.server.BrokerServer;
import cn.wizzer.iot.mqtt.server.broker.service.KafkaService;
import org.nutz.boot.NbApp;
import org.nutz.integration.jedis.RedisService;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

/**
 * Created by wizzer on 2018
 */
@IocBean(create = "init")
@Modules(packages = "cn.wizzer.iot")
public class MainLauncher {
    private static final Log log = Logs.get();
    @Inject("refer:$ioc")
    private Ioc ioc;
    @Inject
    private PropertiesProxy conf;
    @Inject
    private RedisService redisService;
    @Inject
    private BrokerServer brokerServer;
    @Inject
    private KafkaService kafkaService;

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.setMainPackage("cn.wizzer.iot");
        nb.run();
    }

    public void init(){
        kafkaService.consumerMessage();
    }
}
