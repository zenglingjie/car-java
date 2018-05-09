package qfx.common.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import qfx.common.service.model.IFactor;
import qfx.common.service.model.IFactor;
import qfx.common.service.model.TargetAno;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class TargetExecutor implements ApplicationContextAware, ApplicationListener {
    private static final Logger logger = LoggerFactory.getLogger(TargetExecutor.class);
    public void start() throws Exception {

    }
    public void destroy(){

    }
    private static ConcurrentHashMap<String, IFactor> factorRepository = new ConcurrentHashMap<String, IFactor>();
    public static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TargetExecutor.applicationContext = applicationContext;
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(TargetAno.class);
        if (serviceBeanMap!=null && serviceBeanMap.size()>0) {
            for (Object serviceBean : serviceBeanMap.values()) {
                if (serviceBean instanceof IFactor){
                    String name = serviceBean.getClass().getAnnotation(TargetAno.class).value();
                    IFactor handler = (IFactor) serviceBean;
                    registFactor(name, handler);
                }
            }
        }
	}
    public static IFactor getFactor(String name){
        return factorRepository.get(name);
    }

    public static IFactor registFactor(String name, IFactor factor){
        logger.info("register factor, name:{}, IFactor:{}", name, factor);
        return factorRepository.put(name, factor);
    }
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ContextClosedEvent){
            // TODO
        }
    }

}
