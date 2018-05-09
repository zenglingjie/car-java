package qfx.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/** spring boot启动开始时执行的事件 */
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger=LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        SpringApplication app=event.getSpringApplication();
        logger.info("==" + this.getClass().getName() + "==");
    }

}
