package qfx.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/** spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。*/
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

    private Logger logger=LoggerFactory.getLogger(ApplicationPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext cac=event.getApplicationContext();
        passContextInfo(cac);
    }
    private void passContextInfo(ApplicationContext cac) {
        /** todo */
    }
}
